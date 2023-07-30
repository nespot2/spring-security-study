package com.example.springsecuritystudy

import com.example.springsecuritystudy.config.JpaAuditingConfig
import com.example.springsecuritystudy.config.QuerydslConfig
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

/**
 * @author nespot2
 **/
@DataJpaTest
@Import(QuerydslConfig::class, JpaAuditingConfig::class)
abstract class DomainTestSupport {
}