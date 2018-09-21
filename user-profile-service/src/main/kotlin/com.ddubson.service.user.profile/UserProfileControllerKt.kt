package com.ddubson.service.user.profile

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserProfileControllerKt(val userProfileRepository: InMemoryUserProfileRepository) {
    @GetMapping("/api/kt/users/{userId}")
    fun findUserById(@PathVariable("userId") userId: String): ResponseEntity<Any> = ok("This is a test")
}