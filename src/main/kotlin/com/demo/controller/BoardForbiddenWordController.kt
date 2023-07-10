package com.demo.controller

import com.demo.entity.BoardForbiddenWord
import com.demo.service.BoardForbiddenWordService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class BoardForbiddenWordController(
    private val boardForbiddenWordService: BoardForbiddenWordService
) {
    /**
     * 금칙어 단어 목록
     * @return List
     * */
    @GetMapping("/boardForbidden")
    fun findAll(): ResponseEntity<List<BoardForbiddenWord>> {
        return ResponseEntity(boardForbiddenWordService.findAll(), HttpStatus.OK)
    }

    /**
     * 금칙어 단어 저장
     *
     * */
    @PostMapping("/boardForbidden")
    fun wordSave(@RequestBody boardForbiddenWord: BoardForbiddenWord): ResponseEntity<BoardForbiddenWord> {
        return ResponseEntity<BoardForbiddenWord>(boardForbiddenWordService.wordSave(boardForbiddenWord), HttpStatus.OK)
    }
}