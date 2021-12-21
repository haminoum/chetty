package com.haminou.m.chetty.controller

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import java.util.*

@Controller
@CrossOrigin
class UserController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    // @Autowired
    //lateinit var repository: UserRepository

    @PutMapping("/users/save/{username}")
    fun register(@PathVariable username: String): ResponseEntity<String> {
        //val newUser = User(username)
        //repository.save(newUser)
        logger.info("New user saved")
        return ResponseEntity.ok().build<String>()
    }


    @GetMapping("/fetchUsers")
    fun getUsers(): MutableList<Any?> {
        //return repository.findAll();
        return Collections.EMPTY_LIST;
    }
}