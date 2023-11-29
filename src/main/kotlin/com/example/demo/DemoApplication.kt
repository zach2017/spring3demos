package com.demo.zac

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.beans
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import com.example.demo.Library

@EnableWebSecurity
@SpringBootApplication
class SpringSecurityKotlinApplication

@Order(1)
@Configuration
class AdminSecurityConfiguration {
    @Bean
     fun filterChainAdmin(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            securityMatcher("/greetings/**")
            authorizeRequests {
                authorize("/greetings/**", hasAuthority("ROLE_ADMIN"))
            }
            httpBasic {}
        }
        return http.build()
    }
}




@Configuration
class BasicSecurityConfiguration {

    @Bean
     fun filterChainBasic(http: HttpSecurity): SecurityFilterChain {
      
        http {
            authorizeRequests {
                authorize("/**", permitAll)
            }
            httpBasic {}
        }

        http
            // Other security configurations
            .logout()
                .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        
        return http.build()
    }
}

fun main(args: Array<String>) {
    runApplication<SpringSecurityKotlinApplication>(*args) {
        addInitializers( beans {
            bean {
                fun user(user: String, password: String, vararg  roles: String) =
                        User
                        .withDefaultPasswordEncoder()
                        .username(user)
                        .password(password)
                        .roles(*roles)
                        .build()

                InMemoryUserDetailsManager(user("userx", "password", "USER")
                         , user("adminx", "password", "USER", "ADMIN"))
            }

            bean {
                router {
                    GET("/greetings")  {
                        request -> request.principal().map { it.name }.map { ServerResponse.ok().body(mapOf("greeting" to "Hello $it")) }.orElseGet { ServerResponse.badRequest().build() }

                    }
                    
                    GET("/Secure") { ServerResponse.ok().body(mapOf("Secure" to "Secure ")) } 

        
                }
            }
        })
    }
}
    @RestController
    class GreetingController {
    @GetMapping("/hello/{name}")
    fun get(@PathVariable name: String) : String {
       val myclass = Library("more dd")
      val foo = myclass.getName()
    return "Hello, $foo"
    }
}