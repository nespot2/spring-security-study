package com.example.springsecuritystudy

import com.example.springsecuritystudy.domain.admin.*
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 * @author nespot2
 **/
@Component
class SampleAdminInitRunner(
    private val adminRepository: AdminRepository,

    private val passwordEncoder: PasswordEncoder
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        this.adminRepository.save(
            Admin(
                email = "nespot2@gmail.com",
                password = passwordEncoder.encode("zaq1234!"),
                role = AdminRole.USER,
                deleted = YesNo.NO,
                type = SnsType.EMAIL
            )
        )
    }
}