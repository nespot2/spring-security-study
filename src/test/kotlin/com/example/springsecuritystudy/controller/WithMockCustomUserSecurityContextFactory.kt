package com.example.springsecuritystudy.controller

import com.example.springsecuritystudy.domain.admin.Admin
import com.example.springsecuritystudy.domain.admin.AdminRole
import com.example.springsecuritystudy.domain.admin.SnsType
import com.example.springsecuritystudy.domain.admin.YesNo
import com.example.springsecuritystudy.service.login.CustomUserDetails
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithSecurityContextFactory

class WithMockCustomUserSecurityContextFactory : WithSecurityContextFactory<WithMockCustomUser> {
    override fun createSecurityContext(annotation: WithMockCustomUser): SecurityContext {
        val context = SecurityContextHolder.createEmptyContext()
        val principal = CustomUserDetails(
            admin = Admin(
                email = annotation.username,
                password = annotation.password,
                type = SnsType.EMAIL,
                role = AdminRole.ADMIN,
                deleted = YesNo.NO
            )
        )
        val auth: Authentication =
            UsernamePasswordAuthenticationToken(principal, "password", principal.authorities)
        context.authentication = auth
        return context

    }

}
