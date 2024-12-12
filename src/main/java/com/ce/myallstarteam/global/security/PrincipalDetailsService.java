package com.ce.myallstarteam.global.security;

import com.ce.myallstarteam.user.entity.User;
import com.ce.myallstarteam.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



// 인증 실제 진행 -> loadUserByUsername
@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // 시큐리티 내부에서 실제 인증 --> 성공적이라면? return UserDetails
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return new PrincipalDetails(user);
            // 내부적으로 PrincipalDetails의 password와 입력한 password 비교
        }

        return null;
    }

}


