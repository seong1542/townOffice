package com.nhnacademy.townoffice.service;

import com.nhnacademy.townoffice.entity.Member;
import com.nhnacademy.townoffice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username)
                .orElseThrow(()-> new UsernameNotFoundException(username + " not found"));

        return new User(member.getId(), member.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(member.getRole().toString())));

    }
}
