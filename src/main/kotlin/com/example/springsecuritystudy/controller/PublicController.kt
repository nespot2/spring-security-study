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
@RequestMapping("/public")
class PublicController(
    private val helloService: HelloService
) {

    @GetMapping
    fun hello(model: Model): String {
        model.addAttribute("name", helloService.hello("lee"))
        return "public/index"
    }
}