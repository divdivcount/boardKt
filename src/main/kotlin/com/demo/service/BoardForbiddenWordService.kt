package com.demo.service

import com.demo.entity.BoardForbiddenWord
import com.demo.repository.BoardForbiddenWordRepository
import com.demo.repository.BoardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class BoardForbiddenWordService(private val boardForbiddenWordRepository: BoardForbiddenWordRepository) {

    fun wordSave(boardForbiddenWord: BoardForbiddenWord):BoardForbiddenWord{
        return boardForbiddenWordRepository.save(boardForbiddenWord)
    }

    fun findAll():List<BoardForbiddenWord>{
        return boardForbiddenWordRepository.findAll()
    }

    fun forbiddenCheck(boardContent:String, boardTitle:String?=null):Boolean{
        val boardForbiddenWord = boardForbiddenWordRepository.findAll()
        val forbiddenWordList = mutableListOf<String>()

        boardForbiddenWord.forEach {
            it.word?.let { word->
                forbiddenWordList.add(word)
            }
        }

        val resultContainsTitle:Boolean? = if(boardTitle != null) forbiddenWordList.contains(boardTitle) else null
        val resultContainsContent:Boolean = forbiddenWordList.contains(boardContent)
        //contains true면 금칙어 걸림
        //title은 null이 올수 있으므로 통과 시켜야됨
        println(resultContainsTitle)
        println(resultContainsContent)
        if(resultContainsTitle == null && resultContainsContent){
            return true
        } else if(resultContainsTitle == true && resultContainsContent){
            return true
        }
        return false
    }

}