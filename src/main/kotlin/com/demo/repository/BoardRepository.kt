package com.demo.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.demo.entity.Board


interface BoardRepository : JpaRepository<Board, Long>{

}