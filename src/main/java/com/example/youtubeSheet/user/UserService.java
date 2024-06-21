package com.example.youtubeSheet.user;

import com.example.youtubeSheet.exception.DataNotFoundException;

import com.example.youtubeSheet.user.dto.ProfileForm;
import com.example.youtubeSheet.user.dto.SignupForm;
import com.example.youtubeSheet.user.dto.SiteUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private SiteUserDto of(SiteUser siteUser){
        return modelMapper.map(siteUser,SiteUserDto.class);
    }
    private SiteUser of(SiteUserDto siteUser){
        return modelMapper.map(siteUser,SiteUser.class);
    }

    public SiteUser createUser(SignupForm signupForm) {

        SiteUser siteUser =new SiteUser();
        siteUser.setUsername(signupForm.getUsername());
        siteUser.setEmail(signupForm.getEmail());

        String encodePassword=passwordEncoder.encode(signupForm.getPassword());
        siteUser.setPassword(encodePassword);

        this.userRepository.save(siteUser);

        return siteUser;

    }



    public SiteUserDto getUser(String name) {
        Optional<SiteUser> siteUser=this.userRepository.findByUsername(name);

        if(siteUser.isPresent()){
            return of(siteUser.get());
        }else{
            throw new DataNotFoundException("User Not Found");
        }
    }

    public void changePassword(ProfileForm profileForm) {

        SiteUserDto siteUserDto= getUser(profileForm.getUsername());

        String encodePassword=passwordEncoder.encode(profileForm.getPassword());

        siteUserDto.setPassword(encodePassword);

        this.userRepository.save(of(siteUserDto));

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
        SiteUserDto siteUserDto= getUser(profileForm.getUsername());
        siteUserDto.setEmail(profileForm.getEmail());
        this.userRepository.save(of(siteUserDto));

    }


}