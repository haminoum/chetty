package com.haminou.m.chetty

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChettyApp

fun main(args: Array<String>) {
	runApplication<ChettyApp>(*args)
}
