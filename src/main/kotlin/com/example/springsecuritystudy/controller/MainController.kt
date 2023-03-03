package com.example.springsecuritystudy.controller

import com.example.springsecuritystudy.service.HelloService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @author nespot2
 **/
@Controller
@RequestMapping("/")
class MainController(
    private val helloService: HelloService
) {

    @GetMapping
    fun main(model: Model): String {
        model.addAttribute("name", helloService.hello("lee"))
        return "index"
    }
}