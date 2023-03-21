package com.ctq.blog.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

class MainController {
  // Inject via application.properties
  @Value("${welcome.message}")
  var message = null

  @Value("${error.message}")
  var errorMessage = null

  @RequestMapping(Array("/"))
  def index(model: Model): String = {
    model.addAttribute("message", message)
    "index"
  }
}
