package com.ctq.blog.dto

import javax.validation.constraints._
import scala.beans.BeanProperty

class PostDto {

  var id: Long = _

  @NotEmpty(message = "title is required")
  @BeanProperty
  var title: String = _

  @NotEmpty(message = "description is required")
  @BeanProperty
  var description: String = _

  @NotEmpty(message = "content is required")
  @BeanProperty
  var content: String = _

  @BeanProperty
  var author: AuthorDto = _

}
