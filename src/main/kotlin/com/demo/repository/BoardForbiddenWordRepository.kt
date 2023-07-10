package com.demo.repository

import com.demo.entity.BoardForbiddenWord
import org.springframework.data.jpa.repository.JpaRepository

interface BoardForbiddenWordRepository: JpaRepository<BoardForbiddenWord, Long> {
}