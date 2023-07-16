package com.example.springsecuritystudy.domain.admin

import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author nespot2
 **/
interface AdminRepository : JpaRepository<Admin, Long> {
    fun findFirstByEmailAndDeletedAndType(email: String, deleted: YesNo, type: SnsType): Admin?
}