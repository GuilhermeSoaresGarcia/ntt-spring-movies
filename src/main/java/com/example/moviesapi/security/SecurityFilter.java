package com.example.moviesapi.security;

import com.example.moviesapi.model.entity.User;
import com.example.moviesapi.model.repository.UserRepository;
import com.example.moviesapi.util.HandleToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  private HandleToken handleToken;
  private UserRepository userRepository;

  public SecurityFilter(HandleToken handleToken, UserRepository userRepository) {
    this.handleToken = handleToken;
    this.userRepository = userRepository;
  }

  @SuppressWarnings("null")
  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {
    String token = this.recoverToken(request);

    if (token != null) {
      String subject = handleToken.validateToken(token);
      System.out.println(subject);
      User user = userRepository.findByUsername(subject);
      // Usei "var" só pra linha não ficar muito comprida XD
      var authentication = new UsernamePasswordAuthenticationToken(
          user, null, null);

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");

    if (authHeader == null) {
      return null;
    }

    return authHeader.replace("Bearer ", "");
  }
}
