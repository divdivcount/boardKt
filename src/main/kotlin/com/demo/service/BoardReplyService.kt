package com.demo.service

import com.demo.dto.BoardReplyAddDto
import com.demo.entity.BoardReply
import com.demo.repository.BoardReplyCommentRepository
import com.demo.repository.BoardReplyRepository
import com.demo.repository.BoardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardReplyService(
    private val boardRepository: BoardRepository,
    private val boardReplyRepository: BoardReplyRepository,
    private val boardReplyCommentRepository: BoardReplyCommentRepository
) {

    fun findAll(pageable: Pageable):Page<BoardReply>{
        return boardReplyRepository.findAll(pageable)
    }

    fun write(boardReplyAddDto: BoardReplyAddDto):BoardReply{

        println("aaaaaa ::: " + boardRepository.getReferenceById(boardReplyAddDto.boardId))

        val reply = BoardReply(
            board = boardRepository.getReferenceById(boardReplyAddDto.boardId),
            content = boardReplyAddDto.content
        )

        return boardReplyRepository.save(reply)

    }


    @Transactional
    fun update(id:Long, updateReply: BoardReply):BoardReply{
        val boardReply:BoardReply = boardReplyRepository.findById(id).orElseThrow()
        boardReply.content = updateReply.content
        boardReply.updatedAt = updateReply.updatedAt
        return boardReply
    }

    fun findById(id:Long):BoardReply{
        return boardReplyRepository.findById(id).orElseThrow()
    }

    @Transactional
    fun delete(id:Long):String{
        boardReplyRepository.deleteById(id)
        return "deleteSuccess"
    }


}