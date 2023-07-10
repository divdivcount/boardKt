package com.demo.controller

import com.demo.dto.BoardReplyAddDto
import com.demo.entity.Board
import com.demo.entity.BoardForbiddenWord
import com.demo.entity.BoardReply
import com.demo.service.BoardForbiddenWordService
import com.demo.service.BoardReplyService
import com.demo.service.BoardService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/board")
class BoardReplyController(
    private val boardForbiddenWordService: BoardForbiddenWordService,
    private val boardReplyService: BoardReplyService
) {
    @GetMapping("/reply")
    fun list(@PageableDefault(size = 5)pageable: Pageable):ResponseEntity<Page<BoardReply>>{
        return ResponseEntity(boardReplyService.findAll(pageable), HttpStatus.OK)
    }

    @PostMapping("/reply")
    fun write(@RequestBody boardReplyAddDto: BoardReplyAddDto): ResponseEntity<out Any>{
        if(boardForbiddenWordService.forbiddenCheck(boardReplyAddDto.content, null)){
            return ResponseEntity<String>("금칙어 걸림!", HttpStatus.EXPECTATION_FAILED)
        }
        return ResponseEntity<BoardReply>(boardReplyService.write(boardReplyAddDto), HttpStatus.OK)

    }

    @PutMapping("/reply/{replyId}")
    fun update(@PathVariable replyId: Long, @RequestBody boardReply: BoardReply):ResponseEntity<out Any>{

        if(boardForbiddenWordService.forbiddenCheck(boardReply.content.toString(), null)){
            return ResponseEntity<String>("금칙어 걸림!", HttpStatus.EXPECTATION_FAILED)
        }

        return ResponseEntity<BoardReply>(boardReplyService.update(replyId,boardReply), HttpStatus.OK)
    }

    @DeleteMapping("/reply/{replyId}")
    fun delete(@PathVariable replyId: Long):ResponseEntity<String>{
        return ResponseEntity<String>(boardReplyService.delete(replyId), HttpStatus.OK)
    }



}