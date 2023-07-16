package com.example.springsecuritystudy.domain.admin

import com.example.springsecuritystudy.domain.BaseEntity
import jakarta.persistence.*

/**
 * @author nespot2
 **/
@Entity
class Admin(

    @Column(nullable = false)
    var email: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var deleted: YesNo,

    var password: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: AdminRole,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var type: SnsType,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) : BaseEntity() {
}