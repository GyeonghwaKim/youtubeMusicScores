package com.example.youtubeSheet.service;

import com.example.youtubeSheet.entity.dto.ProfileForm;
import com.example.youtubeSheet.entity.dto.SignupForm;
import com.example.youtubeSheet.entity.SiteUser;

import com.example.youtubeSheet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public SiteUser createUser(SignupForm signupForm) {

        SiteUser siteUser =new SiteUser();
        siteUser.setUsername(signupForm.getUsername());
        siteUser.setEmail(signupForm.getEmail());
        siteUser.setUuid(UUID.randomUUID());

        String encodePassword=passwordEncoder.encode(signupForm.getPassword());
        siteUser.setPassword(encodePassword);

        this.userRepository.save(siteUser);

        return siteUser;

    }

    public SiteUser findByUsername(String name) {
        return this.userRepository.findByUsername(name).get();
    }

    public void changePassword(ProfileForm profileForm) {

        SiteUser siteUser=findByUsername(profileForm.getUsername());
        String encodePassword=passwordEncoder.encode(profileForm.getPassword());

        siteUser.setPassword(encodePassword);

        this.userRepository.save(siteUser);

    }

    public List<String> findAllEmail() {
        List<SiteUser> siteUsersList=
        this.userRepository.findAll();
        return siteUsersList
                .stream()
                .map(SiteUser::getEmail)
                .collect(Collectors.toList());

    }

    public void changeEmail(ProfileForm profileForm) {
        SiteUser siteUser=findByUsername(profileForm.getUsername());
        siteUser.setEmail(profileForm.getEmail());
        this.userRepository.save(siteUser);

    }
}