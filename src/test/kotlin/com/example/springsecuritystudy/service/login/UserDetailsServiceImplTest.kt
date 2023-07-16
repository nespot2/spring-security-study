package com.example.springsecuritystudy.service.login

import com.example.springsecuritystudy.config.QuerydslConfig
import com.example.springsecuritystudy.domain.admin.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.security.core.userdetails.UsernameNotFoundException

/**
 * @author nespot2
 */
@DataJpaTest
@Import(QuerydslConfig::class, UserDetailsServiceImpl::class)
class UserDetailsServiceImplTest @Autowired constructor(
    private val userDetailsService: UserDetailsServiceImpl,
    private val adminRepository: AdminRepository
) {

    @Test
    @DisplayName("이메일을 이용하여 사용자를 조회한다.")
    fun loadUserByUsername() {
        val email = "nespot2@gmail.com"
        //given
        saveAdmin(email)

        //when
        val userDetails = this.userDetailsService.loadUserByUsername(email)

        //then
        assertThat(userDetails).isNotNull
        assertThat(userDetails.username).isEqualTo(email)
    }

    @Test
    @DisplayName("등록되지 않은 사용자를 조회하면 예외가 발생한다.")
    fun loadUserByUsernameWithNoUser() {
        //given
        val email = "nespot3@gmail.com"
        saveAdmin(email = email)

        //when, then
        assertThatThrownBy {
            this.userDetailsService.loadUserByUsername(username = "nespot2@gmail.com")
        }.isInstanceOf(UsernameNotFoundException::class.java)
    }

    private fun saveAdmin(email: String): Admin {
        val admin = Admin(
            email = email,
            type = SnsType.EMAIL,
            role = AdminRole.ADMIN,
            password = "1234",
            deleted = YesNo.NO
        )

        return this.adminRepository.save(admin)
    }
}