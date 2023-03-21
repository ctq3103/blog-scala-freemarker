package com.ctq.blog.controller

import com.ctq.blog.dto.PostDto
import com.ctq.blog.repository.PostRepository
import com.ctq.blog.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation._

import scala.jdk.CollectionConverters._

@Controller
@RequestMapping(value = Array("/posts"))
class PostController @Autowired()(val postService: PostService, val postRepository: PostRepository) {

  @GetMapping
  def getPosts(model: Model): String = {
    val posts = postService.getAll().asJava
    model.addAttribute("posts", posts)
    "posts"
  }

  @GetMapping(value = Array("/create"))
  def createPostForm(model: Model): String = {
    val postDto = new PostDto
    model.addAttribute("postDto", postDto)
    "createPost"
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
      "redirect:/posts"
    } else {
      val error = "Title, Description and Content are required!"
      model.addAttribute("errorMessage", error)
      "createPost"
    }
  }

  @GetMapping(value = Array("/update/{id}"))
  def updatePostForm(@PathVariable("id") id: Long, model: Model): String = {
    val postDto = postService.getById(id)
    model.addAttribute("postDto", postDto)
    "updatePost"
  }

  @PostMapping(value = Array("/update/{id}"))
  def updatePost(model: Model, @PathVariable("id") id: Long, @ModelAttribute("postDto") postDto: PostDto): String = {
    val title: String = postDto.title
    val description: String = postDto.description
    val content: String = postDto.content

    if (!title.isEmpty || !content.isEmpty || !description.isEmpty) {
      postDto.id = id
      postService.update(postDto)
      "redirect:/posts"
    } else {
      val error = "Title, Description and Content are required!"
      model.addAttribute("errorMessage", error)
      "updatePost"
    }
  }

  //    @GetMapping(Array("/showFormForUpdate/{id}")) def showFormForUpdate(@PathVariable(value = "id") id: Long, model: Model) = { // get employee from the service
  //      val employee = employeeService.getEmployeeById(id)
  //      // set employee as a model attribute to pre-populate the form
  //      model.addAttribute("employee", employee)
  //      "update_employee"
  //    }
  // }


  //  @RequestMapping(value = Array("/posts/{id}"), method = Array(RequestMethod.GET))
  //  @ResponseStatus(HttpStatus.OK)
  //  def getPostById(@PathVariable id: Long): PostDto = postService.getById(id)
  //
  //  @RequestMapping(value = Array("/posts"), method = Array(RequestMethod.POST))
  //  @ResponseStatus(HttpStatus.CREATED)
  //  def createPost(@Valid @RequestBody postDto: PostDto, bindingResult: BindingResult): PostDto = {
  //    if (bindingResult.hasErrors) throw BadRequestException(bindingResult.getFieldError.getDefaultMessage)
  //    postService.create(postDto)
  //  }
  //
  //  @RequestMapping(value = Array("/posts/{id}"), method = Array(RequestMethod.PUT))
  //  @ResponseStatus(HttpStatus.OK)
  //  def updatePost(@PathVariable id: Long, @Valid @RequestBody postDto: PostDto, bindingResult: BindingResult): PostDto = {
  //    if (bindingResult.hasErrors) throw BadRequestException(bindingResult.getFieldError.getDefaultMessage)
  //    postService.update(id, postDto)
  //  }
  //
  //  @RequestMapping(value = Array("/posts/{id}"), method = Array(RequestMethod.DELETE))
  //  @ResponseStatus(HttpStatus.OK)
  //  def deletePost(@PathVariable id: Long): PostDto = postService.delete(id)

}