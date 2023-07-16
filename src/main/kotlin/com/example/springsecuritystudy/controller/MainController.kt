package com.example.springsecuritystudy.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @author nespot2
 **/
@Controller
class MainController {

    @GetMapping
    @RequestMapping("/")
    fun main(): String {
        return "index"
    }


    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

}