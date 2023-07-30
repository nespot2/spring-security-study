package com.example.springsecuritystudy.controller

import com.example.springsecuritystudy.domain.admin.Admin
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
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

    @GetMapping("/specific-authentication")
    fun specificAuthentication(
        @AuthenticationPrincipal(expression = "admin") admin: Admin,
        model: Model
    ): String {
        model.addAttribute("email", admin.email)
        model.addAttribute("role", admin.role.name)
        return "specific_authentication"
    }


}