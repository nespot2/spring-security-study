package com.example.springsecuritystudy.service.login

import com.example.springsecuritystudy.domain.admin.Admin
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * @author nespot2
 **/
class CustomUserDetails(
    private val admin: Admin,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return hashSetOf(SimpleGrantedAuthority("ROLE_${this.admin.role.name}"))
    }

    override fun getPassword(): String {
        return this.admin.password!!
    }

    override fun getUsername(): String {
        return this.admin.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun getAdmin(): Admin {
        return this.admin
    }

}