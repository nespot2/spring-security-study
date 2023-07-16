package com.example.springsecuritystudy.service.login

import com.example.springsecuritystudy.domain.admin.*
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

/**
 * @author nespot2
 **/
@Service
class GoogleOAuth2UserService(
    private val adminRepository: AdminRepository
) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        val oauth2User = super.loadUser(userRequest)
        val email = oauth2User.attributes["email"] as String
        val admin = this.adminRepository.findFirstByEmailAndDeletedAndType(
            email = email,
            type = SnsType.GOOGLE,
            deleted = YesNo.NO
        ) ?: this.adminRepository.save(
            Admin(
                email = email,
                deleted = YesNo.NO,
                type = SnsType.GOOGLE,
                role = AdminRole.USER
            )
        )

        return CustomOAuth2User(
            admin = admin,
        )
    }
}