package com.ctq.blog

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BlogMvcApplication {}

object BlogMvcApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[BlogMvcApplication], args: _*)
  }
}

