package com.dogWoodFlora.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

import java.io.IOException;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF (enable it in production if needed)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login", "/css/**", "/js/**").permitAll() // Public access to login/register
                        .requestMatchers("/admin/").hasRole("ADMIN")
                        .requestMatchers("/orders/**").hasRole("ADMIN")
                        .requestMatchers("/orders/placeOrder/**").hasRole("USER")
                        .anyRequest().authenticated() // All other endpoints require authentication
                )
                .formLogin(login -> login
                        .loginPage("/login") // Custom login page
                        .successHandler(roleBasedAuthenticationSuccessHandler()) // Role-based redirection
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // Redirect to login after logout
                        .permitAll()
                );

        return http.build();
    }

    /**
     * Custom authentication success handler for role-based redirection
     */
    @Bean
    public AuthenticationSuccessHandler roleBasedAuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                // Get the role of the logged-in user
                var roles = authentication.getAuthorities().stream()
                        .map(grantedAuthority -> grantedAuthority.getAuthority())
                        .toList();

                // Redirect based on role
                if (roles.contains("ROLE_ADMIN")) {
                    response.sendRedirect("/admin"); // Redirect admin to dashboard
                } else {
                    response.sendRedirect("/user"); // Redirect regular user to home
                }
            }
        };
    }
}
