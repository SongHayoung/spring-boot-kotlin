package com.example.kotlinboot.config

import com.example.kotlinboot.service.UserRepositoryUserDetailService
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource

@EnableWebSecurity(debug = true)
class SecurityConfig(
    private val dataSource: DataSource,
    private val userDetailService: UserRepositoryUserDetailService
) : WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity?) {
        web!!.ignoring()
            .antMatchers("/h2-console/**")
    }

    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests()
            .antMatchers("/home")
            .access("hasRole('ROLE_USER')")
            .antMatchers(
                "/",
                "/error",
                "/favicon.ico",
                "/**/*.png",
                "/**/*.gif",
                "/**/*.svg",
                "/**/*.jpg",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                "/h2-console/**",
                "/**"
            )
            .access("permitAll")
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/home", true)
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .and()
            .csrf()
            .ignoringAntMatchers("/shop/**", "/integration/**")
            .and()
            .httpBasic()
            .and()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        //인메모리 기반 시큐리티
        /*
        auth!!.inMemoryAuthentication()
            .withUser("user1")
            .password("{noop}password1")
            .authorities("ROLE_USER")
            .and()
            .withUser("user2")
            .password("{noop}password2")
            .authorities("ROLE_USER")
         */

        //jdbc 기반 시큐리티
        /*
        auth!!.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select username, password, enabled from users where username=?")
            .authoritiesByUsernameQuery("select username, authority from authorities where username=?")
            .passwordEncoder(noEncodingPasswordEncoder())
         */

        //ldap 기반 시큐리티
        //Embedded LDAP server is not provided
        //org.apache.directory.server.core.partition.impl.btree.jdbm.JdbmPartition: method 'void <init>()' not found
        //에러 나온다.. 디펜던시 문제인듯 함.
        /*
        auth!!.ldapAuthentication()
            .userSearchBase("ou=people")
            .userSearchFilter("(uid={0})")
            .groupSearchBase("ou=groups")
            .groupSearchFilter("member={0}")
            .contextSource()
            .root("dc=tacocloud,dc=com")
            .ldif("classpath:users.ldif")
            .and()
            .passwordCompare()
            .passwordEncoder(noEncodingPasswordEncoder())
            .passwordAttribute("userPasscode")
         */

        auth!!.userDetailsService(userDetailService)
            .passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    fun noEncodingPasswordEncoder(): PasswordEncoder {
        return object : PasswordEncoder {
            override fun encode(rawPassword: CharSequence?): String = rawPassword!!.toString()

            override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean =
                rawPassword!!.toString() == encodedPassword
        }
    }

    @Bean
    fun bCryptPasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
