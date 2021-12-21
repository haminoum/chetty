package com.haminou.m.chetty.model

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue
    val id: Long?,
    @Column(name = "username", nullable = false)
    var username: String?,
)