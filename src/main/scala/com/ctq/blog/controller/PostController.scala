package com.ctq.blog.controller

import com.ctq.blog.dto.PostDto
import com.ctq.blog.repository.PostRepository
import com.ctq.blog.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation._

import scala.jdk.CollectionConverters._

@Controller
@RequestMapping(value = Array("/posts"))
class PostController @Autowired()(val postService: PostService, val postRepository: PostRepository) {

  @GetMapping
  def getFirstPage(model: Model): String = {
    getPosts(model, 1)
    "posts"
  }

  @GetMapping(value = Array("/page/{page}"))
  def getPosts(model: Model, @PathVariable("page") page: Int): String = {
    val postsResponse = postService.getAll(page, 2)
    val posts = postsResponse.content.asJava
    val pageNo = postsResponse.pageNo
    val pageSize = postsResponse.pageSize
    val isLast = postsResponse.isLast
    val totalElements = postsResponse.totalElements
    val totalPages = postsResponse.totalPages

    model.addAttribute("posts", posts)
    model.addAttribute("pageNo", pageNo)
    model.addAttribute("pageSize", pageSize)
    model.addAttribute("isLast", isLast)
    model.addAttribute("totalElements", totalElements)
    model.addAttribute("totalPages", totalPages)

    "posts"
  }

  @GetMapping(value = Array("/create"))
  def createPostForm(model: Model): String = {
    val postDto = new PostDto
    model.addAttribute("postDto", postDto)
    model.addAttribute("isUpdate", false)
    "postForm"
  }

  @PostMapping(value = Array("/create"))
  def createPost(model: Model, @ModelAttribute("postDto") postDto: PostDto): String = {
    val title: String = postDto.title
    val description: String = postDto.description
    val content: String = postDto.content

    if (!title.isEmpty || !content.isEmpty || !description.isEmpty) {
      val newPost = new PostDto
      newPost.title = title
      newPost.content = content
      newPost.description = description
      postService.create(newPost)
      "redirect:/"
    } else {
      val error = "Title, Description and Content are required!"
      model.addAttribute("errorMessage", error)
      "postForm"
    }
  }

  @GetMapping(value = Array("/update/{id}"))
  def updatePostForm(@PathVariable("id") id: Long, model: Model): String = {
    val postDto = postService.getById(id)
    model.addAttribute("postDto", postDto)
    model.addAttribute("isUpdate", true)
    "postForm"
  }

  @PostMapping(value = Array("/update/{id}"))
  def updatePost(model: Model, @PathVariable("id") id: Long, @ModelAttribute("postDto") postDto: PostDto): String = {
    val title: String = postDto.title
    val description: String = postDto.description
    val content: String = postDto.content

    if (!title.isEmpty || !content.isEmpty || !description.isEmpty) {
      postDto.id = id
      postService.update(postDto)
      "redirect:/"
    } else {
      val error = "Title, Description and Content are required!"
      model.addAttribute("errorMessage", error)
      "postForm"
    }
  }

}