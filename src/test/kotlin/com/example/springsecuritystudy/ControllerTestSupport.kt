package com.example.springsecuritystudy

import com.example.springsecuritystudy.controller.MainController
import com.example.springsecuritystudy.controller.UserDetailServiceConfig
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import

/**
 * @author nespot2
 **/
@WebMvcTest(
    controllers = [
        MainController::class,
    ]
)
@Import(UserDetailServiceConfig::class)
abstract class ControllerTestSupport {

}