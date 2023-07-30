package com.example.springsecuritystudy.controller

import com.example.springsecuritystudy.domain.admin.Admin
import com.example.springsecuritystudy.domain.admin.AdminRole
import com.example.springsecuritystudy.domain.admin.SnsType
import com.example.springsecuritystudy.domain.admin.YesNo
import com.example.springsecuritystudy.service.login.CustomUserDetails
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetailsService

/**
 * @author nespot2
 **/
@TestConfiguration
class UserDetailServiceConfig {

    @Bean
    fun userDetailService(): UserDetailsService {
        return UserDetailsService { username ->
            val admin = Admin(
                email = username,
                type = SnsType.EMAIL,
                role = AdminRole.ADMIN,
                deleted = YesNo.NO,
                password = "1234"
            )
            CustomUserDetails(admin)
        }
    }

}