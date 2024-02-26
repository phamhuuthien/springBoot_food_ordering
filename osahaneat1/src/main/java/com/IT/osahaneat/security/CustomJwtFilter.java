package com.IT.osahaneat.security;

import com.IT.osahaneat.Responsitory.UserRepository;
import com.IT.osahaneat.entity.Users;
import com.IT.osahaneat.services.CustomUserDetailService;
import com.IT.osahaneat.services.UserService;
import com.IT.osahaneat.utils.JwtUtilsHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class CustomJwtFilter extends OncePerRequestFilter {


    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getTokenFromHeader(request);
        if(token!=null){
//          nếu token do server mình sinh ra
            if(jwtUtilsHelper.verifyToken(token)){
                String username = jwtUtilsHelper.getUsernameFromJWT(token);
                UserDetails userDetails =  customUserDetailService.loadUserByUsername(username);
//                tạo chứng thực
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        "",
                        userDetails.getAuthorities()
                );

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }
        filterChain.doFilter(request, response);
    }

    public String getTokenFromHeader(HttpServletRequest httpServletRequest){
        String header = httpServletRequest.getHeader("Authorization");
        String token = null;
        if(StringUtils.hasText(header)&& header.startsWith("Bearer ")){
            token = header.substring(7);
        }
        return token;
    }

    public Users getUserFromToken(HttpServletRequest request){
        String token = getTokenFromHeader(request);
        String username =  jwtUtilsHelper.getUsernameFromJWT(token);
        Users user = userRepository.findByUserName(username);
        return user;
    }
}
