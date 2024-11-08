package com.java.TodoManagement.Config;

import com.java.TodoManagement.Security.JwtAuthenticationEntryPoint;
import com.java.TodoManagement.Security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.PasswordManagementDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            //to give access to any request this is used.
//        http.csrf().disable()
//                .authorizeHttpRequests((authorize)->{
//                    authorize.anyRequest().authenticated();
//                }).httpBasic(Customizer.withDefaults());
        //to give role based authorization
        http.csrf().disable()
                .authorizeHttpRequests((authorize)->{
//                    authorize.requestMatchers(HttpMethod.POST, "/api/**")
//                                    .hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.PUT, "/api/**")
//                                    .hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.DELETE, "/api/**")
//                                    .hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.GET, "/api/**")
//                            .hasAnyRole("ADMIN", "USER");
//                    authorize.requestMatchers(HttpMethod.PATCH, "/api/**")
//                            .hasAnyRole("ADMIN", "USER");
                    //to publicly expose get related rest APIs in spring security
                    //authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
                    authorize.requestMatchers("/api/**").permitAll();
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

        http.exceptionHandling(exception -> exception.
                authenticationEntryPoint(authenticationEntryPoint));
        http.addFilterBefore(authenticationFilter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails mujahith= User.builder()
//                .username("mujahith")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(mujahith, admin);
//    }
    //we commented this code because we are using database authentication and not in memory authentication.

}
