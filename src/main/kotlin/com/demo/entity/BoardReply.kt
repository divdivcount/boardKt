package com.demo.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.Formula
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

@Entity
@DynamicInsert
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener::class)
@Table(name="test_board_reply")
class BoardReply(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var replyId: Long? = null,

    var content: String? = null,

    var likeCnt: Int? = 0,
    @CreationTimestamp
    val regDate: LocalDateTime? = null,
    @LastModifiedDate
    var updatedAt:LocalDateTime? = LocalDateTime.now(),


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    var board: Board?

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "reply_id")
//    var boardReplyComment: List<BoardReplyComment> = ArrayList()
) {
    override fun toString(): String {
        return "BoardReply(replyId=$replyId, content=$content, likeCnt=$likeCnt, regDate=$regDate, updatedAt=$updatedAt)"
    }
}