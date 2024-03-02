package com.IT.osahaneat.security;

import com.IT.osahaneat.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class CustomFilterSecurity {
    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    CustomJwtFilter customJwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());

//        build để xây dựng authenticationManager
        return authenticationManagerBuilder.build();
    }


//    config security request
    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//            .cors(AbstractHttpConfigurer::disable)
////            .and()
////          dung jwt nên hủy session
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .authorizeHttpRequests(request -> {
//                request.requestMatchers("/login/**")
//                        .permitAll()
////                        những request còn lại đê phải chứng thưc
//                        .anyRequest()
//                        .authenticated();
//            })
//                .httpBasic(withDefaults())
//                ;
////      add filter JWT trước khi filter chứng thực run
////        http.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }

//    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> {
                    request.requestMatchers(
                                "/login/**",
                                "/promo/home/**"
                            ).permitAll()
                            .requestMatchers(
                                    "/admin/**",
                                    "/food/**",
                                    "/promo/**",
                                    "/restaurant/admin/**",
                                    "/category/admin/**",
                                    "/order/admin/**",
                                    "/clearCache/**",
                                    "/redis/**"
                            ).hasRole("ADMIN")
                            .requestMatchers(
                                    "/user/**",
                                    "/order/**",
                                    "/ratingRestaurant/**",
                                    "/ratingFood/**"
                            ).hasRole("USER")
                            .requestMatchers(
                                    "/restaurant/**",
                                    "/category/**"
                            )
                            .permitAll()
                            .anyRequest().authenticated();
                });
//                .httpBasic(withDefaults());
        http.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


//  mã hóa tạp thời
//   bcryp chỉ mã hóa chứ k giải được
//   PasswordEncoder có hỗ trợ các phương thức về kiểm tra password
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
