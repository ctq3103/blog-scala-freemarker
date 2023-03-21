package com.ctq.blog.entity

import javax.persistence._
import scala.beans.BeanProperty

@Entity
@Table(name = "posts")
class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @BeanProperty
  var id: Long = _

  @Column(name = "title", nullable = false)
  @BeanProperty
  var title: String = _

  @Column(name = "description")
  @BeanProperty
  var description: String = _

  @Column(name = "content")
  @BeanProperty
  var content: String = _

  @ManyToOne
  @JoinColumn(name = "author")
  @BeanProperty
  var author: Author = _

}
