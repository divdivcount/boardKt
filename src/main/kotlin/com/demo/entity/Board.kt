package com.demo.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.persistence.CascadeType
import jakarta.persistence.Table
import org.hibernate.annotations.*
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.time.LocalDateTime


@Entity
@DynamicInsert
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener::class)
@Table(name="test_board")
class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) var boardId: Long,
    var title: String?,
    var content: String?,
    @ColumnDefault("0") var hit: Int?,
    @ColumnDefault("0") var likeCnt: Int?,
    @CreationTimestamp var regdate: LocalDateTime?,
    @LastModifiedDate var updatedAt: LocalDateTime?,
    @JsonIgnore
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = [CascadeType.ALL]) var boardReply: List<BoardReply>?,
    @Formula("(SELECT COUNT(*) FROM test_board_reply br WHERE br.board_id = board_id)")
    var replyCount: Long = 0L
){
    override fun toString(): String {
        return "Board(boardId=$boardId, title=$title, content=$content, hit=$hit, likeCnt=$likeCnt, regdate=$regdate, updatedAt=$updatedAt)"
    }
}
