package com.demo.repository

import com.demo.entity.BoardReply
import com.demo.entity.BoardReplyComment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BoardReplyCommentRepository : JpaRepository<BoardReplyComment, Long>{
}