package com.eduardo.spoilerappnetwork.security.userdetails;

import com.eduardo.spoilerappnetwork.security.jwt.dto.JwtRequest;
import com.eduardo.spoilerappnetwork.security.jwt.dto.JwtResponse;
import com.eduardo.spoilerappnetwork.security.jwt.service.JwtService;
import com.eduardo.spoilerappnetwork.user.entity.User;
import com.eduardo.spoilerappnetwork.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse authenticate(JwtRequest userCredentials){
        String username = userCredentials.getUsername();
        String password = userCredentials.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        UserDetails userDetails = this.loadUserByUsername(userCredentials.getUsername());

        String token = jwtService.generateToken(userDetails);

        return new JwtResponse(token);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        return new UserDetailsImpl(user.getUsername(), user.getPassword());
    }
}
