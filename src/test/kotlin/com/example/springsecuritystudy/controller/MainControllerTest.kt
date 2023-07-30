package com.example.springsecuritystudy.controller

import com.example.springsecuritystudy.ControllerTestSupport
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * @author nespot2
 */
class MainControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) : ControllerTestSupport() {

    @DisplayName("withMockUser 어노테이션을 이용하여 private 페이지에 접근할 수 있는지 테스트한다.")
    @Test
    @WithMockUser
    fun mainWithMockUser() {
        //when, then
        this.mockMvc.perform(get("/"))
            .andExpect(status().isOk)
            .andExpect(view().name("index"))
    }

    @WithUserDetails(value = "nespot2@gmail.com")
    @DisplayName("withUserDetails 어노테이션을 이용하여 private 페이지에 접근할 수 있는지 테스트한다.")
    @Test
    fun specificAuthentication() {
        //when, then
        this.mockMvc.perform(get("/specific-authentication"))
            .andExpect(status().isOk)
            .andExpect(view().name("specific_authentication"))
            .andExpect(model().attribute("email", "nespot2@gmail.com"))
    }

    @WithMockCustomUser
    @DisplayName("withSecurityContext 어노테이션을 이용하여 private 페이지에 접근할 수 있는지 테스트한다.")
    @Test
    fun specificAuthenticationWithSecurityContext() {
        //when, then
        this.mockMvc.perform(get("/specific-authentication"))
            .andExpect(status().isOk)
            .andExpect(view().name("specific_authentication"))
            .andExpect(model().attribute("email", "nespot3@gmail.com"))
    }

}