package com.example.springsecuritystudy.service.login

import com.example.springsecuritystudy.domain.admin.AdminRepository
import com.example.springsecuritystudy.domain.admin.SnsType
import com.example.springsecuritystudy.domain.admin.YesNo
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * @author nespot2
 **/
@Service
class UserDetailsServiceImpl(
    private val adminRepository: AdminRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val adminUser = this.adminRepository.findFirstByEmailAndDeletedAndType(
            email = username,
            type = SnsType.EMAIL,
            deleted = YesNo.NO
        )
            ?: throw UsernameNotFoundException(username)

        return CustomUserDetails(adminUser)
    }
}