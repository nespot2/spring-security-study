package com.example.springsecuritystudy.service.login

import com.example.springsecuritystudy.domain.admin.Admin
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

/**
 * @author nespot2
 **/
class CustomOAuth2User(
    private val admin: Admin,
) : OAuth2User {
    override fun getName(): String {
        return this.admin.email
    }

    override fun getAttributes(): MutableMap<String, Any>? {
        return null
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return hashSetOf(SimpleGrantedAuthority("ROLE_${this.admin.role.name}"))
    }

    fun getAdmin(): Admin {
        return this.admin
    }


}