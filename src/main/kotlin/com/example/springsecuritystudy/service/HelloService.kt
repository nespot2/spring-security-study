package com.example.springsecuritystudy.service

import org.springframework.stereotype.Service

/**
 * @author nespot2
 **/
@Service
class HelloService {
    fun hello(name: String): String {
        return "hello $name"
    }
}