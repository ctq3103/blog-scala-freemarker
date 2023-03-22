package com.ctq.blog.service

import com.ctq.blog.dto.{PostDto, PostsResponse}
import com.ctq.blog.exception.NotFoundException
import com.ctq.blog.mapper.PostMapper
import com.ctq.blog.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{PageRequest, Pageable}
import org.springframework.stereotype.Service

import scala.jdk.CollectionConverters._
import scala.collection.immutable.List

@Service
class PostService @Autowired()(val postRepository: PostRepository) {

  def getAll(): List[PostDto] = {
    PostMapper.toDtoList(postRepository.findAll().asScala.toList)
  }

  def getAll(pageNo: Int, pageSize: Int): PostsResponse = {
    val pageable: Pageable = PageRequest.of(pageNo - 1, pageSize)

    val posts = postRepository.findAll(pageable)
    val content: List[PostDto] = PostMapper.toDtoList(posts.getContent().asScala.toList)

    val postsResponse = new PostsResponse

    postsResponse.content = content
    postsResponse.pageNo = posts.getNumber
    postsResponse.pageSize = posts.getSize
    postsResponse.totalElements = posts.getTotalElements
    postsResponse.totalPages = posts.getTotalPages
    postsResponse.isLast = posts.isLast

    postsResponse
  }

  def getAll(pageable: Pageable): PostsResponse = {
    val posts = postRepository.findAll(pageable)
    val content: List[PostDto] = PostMapper.toDtoList(posts.getContent().asScala.toList)

    val postsResponse = new PostsResponse

    postsResponse.content = content
    postsResponse.pageNo = posts.getNumber
    postsResponse.pageSize = posts.getSize
    postsResponse.totalElements = posts.getTotalElements
    postsResponse.totalPages = posts.getTotalPages
    postsResponse.isLast = posts.isLast

    postsResponse
  }

  def getById(id: Long): PostDto =
    PostMapper.toDto(Option(postRepository.findById(id)).getOrElse(throw NotFoundException(s"Post with ID $id not found")))

  def create(postDto: PostDto): PostDto =
    PostMapper.toDto(postRepository.save(PostMapper.toEntity(postDto)))

  def update(postDto: PostDto): Unit = {
    postRepository.save(PostMapper.toEntity(postDto))
  }

  def delete(id: Long): PostDto = {
    val post = postRepository.findById(id)
    val deletedPost = PostMapper.toDto(post)
    postRepository.delete(post)
    deletedPost
  }
}
