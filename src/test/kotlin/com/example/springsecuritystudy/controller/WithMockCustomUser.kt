package com.example.springsecuritystudy.controller

import org.springframework.security.test.context.support.WithSecurityContext


@Retention(AnnotationRetention.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory::class)
annotation class WithMockCustomUser(val username: String = "nespot3@gmail.com", val password: String = "1234")