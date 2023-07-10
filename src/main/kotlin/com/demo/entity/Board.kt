package com.demo.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.UpdateTimestamp
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
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = [CascadeType.ALL]) var boardReply: List<BoardReply>?
){
    override fun toString(): String {
        return "Board(boardId=$boardId, title=$title, content=$content, hit=$hit, likeCnt=$likeCnt, regdate=$regdate, updatedAt=$updatedAt)"
    }
}
