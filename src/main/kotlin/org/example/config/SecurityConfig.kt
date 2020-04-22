package org.example.config

import org.example.domain.user.UserRole
import org.example.security.AuthEntryPoint
import org.example.security.TokenFilter
import org.example.security.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

private val SWAGGER_URLS = arrayOf(
    "/v2/api-docs",
    "/swagger-resources",
    "/swagger-resources/**",
    "/configuration/ui",
    "/configuration/security",
    "/swagger-ui.html#"
)

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val userDetailsService: UserDetailsService,
    private val unauthorizedHandler: AuthEntryPoint,
    private val tokenProvider: TokenProvider
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationJwtTokenFilter(): TokenFilter {
        return TokenFilter(tokenProvider)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder
            .userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests()
            .antMatchers("/**/login", "/**/sign_up", *SWAGGER_URLS).permitAll()
            .antMatchers("/**").hasAnyRole(UserRole.ROLE_USER.name, UserRole.ROLE_ADMIN.short)
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }
}