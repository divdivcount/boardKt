package com.demo.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.demo.entity.Board
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param


interface BoardRepository : JpaRepository<Board, Long>{
}