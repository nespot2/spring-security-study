package com.example.springsecuritystudy

import com.example.springsecuritystudy.controller.MainController
import com.example.springsecuritystudy.controller.PublicController
import com.example.springsecuritystudy.service.HelloService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.servlet.config.annotation.EnableWebMvc

/**
 * @author nespot2
 **/
@ExtendWith(SpringExtension::class)
@WebAppConfiguration
@ContextConfiguration(classes = [SecurityFilterChainWithFormLogin::class, MainController::class, HelloService::class, PublicController::class])
class FormLoginTests @Autowired constructor(
    private val context: WebApplicationContext
) {

    private var mvc: MockMvc? = null

    @BeforeEach
    fun beforeSetup() {
        this.mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply<DefaultMockMvcBuilder>(springSecurity())
            .build()
    }

    @Test
    fun `invalid form login`() {
        val invalidForm = formLogin()
            .user("username", "kiki")
            .password("password", "password")

        this.mvc
            ?.perform(invalidForm)
            ?.andExpect(status().isFound)
            ?.andExpect(redirectedUrl("/login?error"))
    }

    @Test
    fun `access private without authentication`() {
        this.mvc
            ?.perform(get("/"))
            ?.andExpect(status().isFound)
            ?.andExpect(redirectedUrl("http://localhost/login"))
    }

    @Test
    fun `access private with authentication`() {
        this.mvc
            ?.get("/") {
                with(user("user"))
            }?.andExpect { status().isOk }
    }

    @Test
    @WithMockUser
    fun `access private with @WithMockUser`() {
        this.mvc
            ?.get("/")
            ?.andExpect { status().isOk }
    }

    @Test
    fun `valid form login`() {
        val validForm = formLogin()
            .user("username", "user")
            .password("password", "password")

        this.mvc
            ?.perform(validForm)
            ?.andExpect(status().isFound)
            ?.andExpect(redirectedUrl("/default"))
    }

    @Test
    fun `access public`() {
        this.mvc
            ?.perform(get("/public"))
            ?.andExpect(status().isOk)
    }

}

@TestConfiguration
@EnableWebSecurity
@EnableWebMvc
internal class SecurityFilterChainWithFormLogin() {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authorize ->
            authorize
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
        }
        http.formLogin().loginPage("/login").defaultSuccessUrl("/default")
        return http.build()
    }

    @Bean
    fun userDetailService(passwordEncoder: PasswordEncoder): UserDetailsService {
        val user = User.builder()
            .username("user")
            .password(passwordEncoder.encode("password"))
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(user)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }


}