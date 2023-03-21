package com.ctq.blog.dto

class PostsResponse {

  var content: List[PostDto] = _
  var pageNo: Int = _
  var pageSize: Int = _
  var totalElements: Long = _
  var totalPages = 0
  var isLast = false
}
