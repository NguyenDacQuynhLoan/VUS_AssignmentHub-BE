package com.edusystem.Configuration;

import com.edusystem.Repositories.Authen.AuthenticateRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {
//    @Autowired
    private final AuthenticateRepository authenticateRepository;

    private final JWTUtil _jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String userEmail;
        final String token;

        if(authHeader == null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        token = authHeader.substring(7);
        userEmail = _jwtUtil.extractUserName(token);
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null ){
            UserDetails userDetails = authenticateRepository.findEmail(userEmail);

            if(_jwtUtil.isTokenValid(token, userDetails)){
                //
                UsernamePasswordAuthenticationToken authenToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                //
                authenToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //
                SecurityContextHolder.getContext().setAuthentication(authenToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
