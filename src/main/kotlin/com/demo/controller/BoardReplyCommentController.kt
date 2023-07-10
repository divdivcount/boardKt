package com.demo.controller

import com.demo.entity.Board
import com.demo.entity.BoardReplyComment
import com.demo.service.BoardForbiddenWordService
import com.demo.service.BoardReplyCommentService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/board/reply")
class BoardReplyCommentController(
    private val boardReplyCommentService: BoardReplyCommentService,
    private val boardForbiddenWordService: BoardForbiddenWordService
){
    @GetMapping("{replyId}/reply-comment/list")
    fun getReplyCommentList(@PageableDefault(size = 5)pageable: Pageable, @PathVariable replyId:Long): ResponseEntity<Page<BoardReplyComment>> {
        return ResponseEntity(boardReplyCommentService.findAllByReplyId(pageable), HttpStatus.OK)
    }

    @PostMapping("/reply-comment")
    fun write(@RequestBody boardReplyComment: BoardReplyComment): ResponseEntity<out Any>{

        if(boardForbiddenWordService.forbiddenCheck(boardReplyComment.content.toString(), null)){
            return ResponseEntity<String>("금칙어 걸림!", HttpStatus.EXPECTATION_FAILED)
        }

        return ResponseEntity<BoardReplyComment>(boardReplyCommentService.write(boardReplyComment), HttpStatus.CREATED)
    }

    @PutMapping("/reply-comment/{id}")
    fun update(@PathVariable id:Long, @RequestBody boardReplyComment: BoardReplyComment):ResponseEntity<out Any>{

        if(boardForbiddenWordService.forbiddenCheck(boardReplyComment.content.toString(), null)){
            return ResponseEntity<String>("금칙어 걸림!", HttpStatus.EXPECTATION_FAILED)
        }

        return ResponseEntity<BoardReplyComment>(boardReplyCommentService.update(id, boardReplyComment), HttpStatus.OK)
    }

    @DeleteMapping("/reply-comment/{id}")
    fun delete(id:Long):String{
        boardReplyCommentService.delete(id)
        return "deleteSuccess"
    }





}