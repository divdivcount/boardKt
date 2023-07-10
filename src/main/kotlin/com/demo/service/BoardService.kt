package com.demo.service

import com.demo.entity.Board
import com.demo.repository.BoardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class BoardService(private val boardRepository: BoardRepository) {


    fun findByAll(pageable: Pageable):Page<Board>{
        return boardRepository.findAll(pageable)
    }

    fun write(board : Board):Board{
        return boardRepository.save(board)
    }

    fun findById(id:Long):Board{
        return boardRepository.findById(id).orElseThrow()
    }

    @Transactional
    fun update(id:Long, updateBoard : Board):Board{
        val board:Board = boardRepository.findById(id).orElseThrow()
        board.title = updateBoard.title
        board.content = updateBoard.content
        board.updatedAt = updateBoard.updatedAt
        return board
    }

    @Transactional
    fun delete(id:Long):String{
        boardRepository.deleteById(id)
        return "delete success"
    }


}