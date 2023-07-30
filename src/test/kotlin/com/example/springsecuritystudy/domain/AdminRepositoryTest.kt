package com.example.springsecuritystudy.domain

import com.example.springsecuritystudy.DomainTestSupport
import com.example.springsecuritystudy.config.QuerydslConfig
import com.example.springsecuritystudy.domain.admin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

/**
 * @author nespot2
 **/
@DataJpaTest
@Import(QuerydslConfig::class)
internal class AdminRepositoryTest @Autowired constructor(
    private val adminRepository: AdminRepository
) : DomainTestSupport() {

    @DisplayName("이메일 & 타입을 이용하여 관리자를 조회한다.")
    @Test
    fun findFirstByEmailAndDeletedAndType() {

        //given
        val email = "nespot2@gmail.com"
        saveAdmin(email = email)

        //when
        val admin = this.adminRepository.findFirstByEmailAndDeletedAndType(
            email = email,
            deleted = YesNo.NO,
            type = SnsType.GOOGLE
        )
        //then
        assertThat(admin).isNotNull
    }

    @DisplayName("등록되지 않은 관리자를 조회하면 null을 반환한다.")
    @Test
    fun findFirstByEmailAndDeletedAndTypeWithNoAdmin() {
        //given
        saveAdmin(email = "nespot2@gmail.com")

        //when
        val admin = this.adminRepository.findFirstByEmailAndDeletedAndType(
            email = "nespot3@gmail.com",
            deleted = YesNo.NO,
            type = SnsType.GOOGLE
        )

        //then
        assertThat(admin).isNull()
    }

    fun saveAdmin(email: String): Admin {
        val admin = Admin(
            email = email,
            type = SnsType.GOOGLE,
            role = AdminRole.ADMIN,
            deleted = YesNo.NO
        )
        return this.adminRepository.save(admin)
    }

}