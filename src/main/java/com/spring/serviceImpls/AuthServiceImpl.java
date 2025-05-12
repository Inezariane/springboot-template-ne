package com.spring.serviceImpls;

import com.spring.exceptions.AppException;
import com.spring.models.User;
import com.spring.payload.response.JwtAuthenticationResponse;
import com.spring.security.JwtTokenProvider;
import com.spring.services.IAuthService;
import com.spring.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtAuthenticationResponse login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = null;
        try {
            jwt = jwtTokenProvider.generateToken(authentication);
        } catch (Exception e) {
            throw new AppException("Error generating token", e);
        }
        User user = this.userService.getByEmail(email);
        return new JwtAuthenticationResponse(jwt, user);
    }
}
