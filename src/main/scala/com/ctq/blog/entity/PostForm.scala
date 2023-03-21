package com.ctq.blog.entity

import javax.validation.constraints.{NotEmpty, Size}
import scala.beans.BeanProperty

class PostForm {
  @NotEmpty(message = "title is required")
  @Size(min = 10)
  @BeanProperty
  var title: String = _

  @NotEmpty(message = "description is required")
  @Size(min = 10)
  @BeanProperty
  var description: String = _

  @NotEmpty(message = "content is required")
  @Size(min = 50)
  @BeanProperty
  var content: String = _
}
