package uz.example.foodstore.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.NonNull;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import uz.example.foodstore.dto.response.Response;


import java.io.IOException;

@Component
@RequiredArgsConstructor
@NonNullApi
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final JwtTokenProvider jwtTokenProvider;

    private final ObjectMapper objectMapper;
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";


    @Override
    @NonNull
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String header = request.getHeader(AUTHORIZATION);


            if (StringUtils.isBlank(header) || !header.startsWith(BEARER)) {
                filterChain.doFilter(request, response);
                return;

            }
            String token = header.substring(7);
            if (!jwtTokenProvider.isValid(token)) {
                filterChain.doFilter(request, response);
                return;
            }
            Claims claims = jwtTokenProvider.parseAllClaims(token);
            String phone = claims.getSubject();

            UserDetails userDetails = userDetailsService.loadUserByUsername(phone);

            var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response
                    .getOutputStream()
                    .write(
                            objectMapper.writeValueAsBytes(
                                    new Response<>(false, e.getMessage())
                            )
                    );
        }


    }
}
