package com.demo.service

import com.demo.entity.BoardReplyComment
import com.demo.repository.BoardReplyCommentRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class BoardReplyCommentService (
    private val boardReplyCommentRepository: BoardReplyCommentRepository
){

    fun findAllByReplyId(pageable: Pageable):Page<BoardReplyComment>{
        return boardReplyCommentRepository.findAll(pageable)
    }

    fun write(boardReplyComment:BoardReplyComment): BoardReplyComment {
        return boardReplyCommentRepository.save(boardReplyComment)
    }

    @Transactional
    fun update(id:Long, updateBoardReplyComment: BoardReplyComment):BoardReplyComment{
        val boardReplyComment:BoardReplyComment = boardReplyCommentRepository.findById(id).orElseThrow()
        boardReplyComment.content = updateBoardReplyComment.content
        boardReplyComment.updateAt = updateBoardReplyComment.updateAt
        return boardReplyComment
    }

    @Transactional
    fun delete(id: Long):String{
        boardReplyCommentRepository.deleteById(id)
        return "deleteSuccess"
    }
}