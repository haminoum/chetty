package com.haminou.m.chetty.repository

import com.haminou.m.chetty.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {

}