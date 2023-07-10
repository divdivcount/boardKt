package com.demo.controller

import com.demo.entity.Board
import com.demo.service.BoardForbiddenWordService
import com.demo.service.BoardService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class BoardController (
    private val boardService: BoardService,
    private val boardForbiddenWordService: BoardForbiddenWordService
) {

    @GetMapping("/board/list")
    /**
     * 게시글 전체 목록
     * @return List
     * */
    fun list(@PageableDefault(size = 5, sort = ["boardId"], direction = Sort.Direction.ASC)pageable:Pageable):ResponseEntity<Page<Board>>{
        return ResponseEntity(boardService.findByAll(pageable), HttpStatus.OK)
    }

    @GetMapping("/board/list/{id}")
    fun boardDetail(@PathVariable id:Long):ResponseEntity<Board>{
        return ResponseEntity(boardService.findById(id), HttpStatus.OK)
    }

    @PostMapping("/board")
    fun write(@RequestBody board:Board): ResponseEntity<out Any>{

        if(boardForbiddenWordService.forbiddenCheck(board.content.toString(),board.title.toString())){
            return ResponseEntity<String>("금칙어 걸림!", HttpStatus.EXPECTATION_FAILED)
        }

        return ResponseEntity<Board>(boardService.write(board), HttpStatus.CREATED)
    }

    @PutMapping("/board/{id}")
    fun update(@PathVariable id:Long, @RequestBody board:Board):ResponseEntity<out Any>{

        if(!boardForbiddenWordService.forbiddenCheck(board.content.toString(),board.title.toString())){
            return ResponseEntity<String>("금칙어 걸림!", HttpStatus.EXPECTATION_FAILED)
        }

        return ResponseEntity<Board>(boardService.update(id, board), HttpStatus.OK)
    }

    @DeleteMapping("/board/{id}")
    fun delete(@PathVariable id:Long):ResponseEntity<String>{
        return ResponseEntity<String>(boardService.delete(id), HttpStatus.OK)
    }
}