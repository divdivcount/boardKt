package com.demo.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicInsert
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.time.LocalDateTime

@Entity
@DynamicInsert
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener::class)
@Table(name="test_board_reply_comment")
class BoardReplyComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var commentId: Long? = null
//    var replyId:Long? = null
    var content:String? = null

    var likeCnt:Int? = 0
    @CreationTimestamp
    val regDate:LocalDateTime? = null
    @LastModifiedDate
    var updateAt:LocalDateTime? = LocalDateTime.now()

}