package com.demo.entity

import jakarta.persistence.*
import org.hibernate.annotations.DynamicInsert
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Entity
@DynamicInsert
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener::class)
class BoardForbiddenWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var word: String? = null
}
