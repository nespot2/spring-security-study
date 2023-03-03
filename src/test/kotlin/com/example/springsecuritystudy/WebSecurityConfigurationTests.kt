package com.example.springsecuritystudy

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.support.GenericApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.FilterChainProxy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [SecurityFilterChainsOrderConfig::class])
class WebSecurityConfigurationTests @Autowired constructor(
    private val applicationContext: GenericApplicationContext
) {
    @Test
    fun `load config with filter chain list`() {
        val bean = applicationContext.getBean(FilterChainProxy::class.java)
        val filterChains = bean.filterChains
        assertThat(filterChains).hasSize(3)

        val firstFilterChain = filterChains[0]
        val request1 = MockHttpServletRequest("GET", "")
        request1.servletPath = "/role1/**"
        assertThat(firstFilterChain.matches(request1)).isTrue

        val request2 = MockHttpServletRequest("GET", "")
        val secondFilterChain = filterChains[1]
        request2.servletPath = "/role2/**"
        assertThat(secondFilterChain.matches(request2)).isTrue

        val request3 = MockHttpServletRequest("GET", "")
        val thirdFilterChain = filterChains[2]
        request3.servletPath = "/role3/**"
        assertThat(thirdFilterChain.matches(request3)).isTrue
    }

}

@TestConfiguration
@EnableWebSecurity
internal class SecurityFilterChainsOrderConfig {

    @Bean
    @Order(1)
    fun filterChain1(http: HttpSecurity): SecurityFilterChain {
        // @formatter:off
        return http
            .securityMatcher(AntPathRequestMatcher("/role1/**"))
            .authorizeHttpRequests { authorize ->
                authorize
                    .anyRequest().hasRole("1")
            }
            .build()
    }

    @Bean
    @Order(2)
    fun filterChain2(http: HttpSecurity): SecurityFilterChain {
        // @formatter:off
        return http
            .securityMatcher(AntPathRequestMatcher("/role2/**"))
            .authorizeHttpRequests { authorize ->
                authorize
                    .anyRequest().hasRole("2")
            }
            .build()
    }

    @Bean
    @Order(3)
    fun filterChain3(http: HttpSecurity): SecurityFilterChain {
        // @formatter:off
        return http
            .securityMatcher(AntPathRequestMatcher("/role3/**"))
            .authorizeHttpRequests { authorize ->
                authorize
                    .anyRequest().hasRole("3")
            }
            .build()
    }
}





