package com.example.youtubeSheet.service;

import com.example.youtubeSheet.dto.UserCreateRequestDto;
import com.example.youtubeSheet.dto.UserRequestDto;
import com.example.youtubeSheet.entity.SiteUser;

import com.example.youtubeSheet.entity.UserRole;
import com.example.youtubeSheet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    //private final EncryptHelper encryptHelper;

    //private final HttpSession httpSession;

    public SiteUser createUser(UserCreateRequestDto userCreateRequestDto) {

        SiteUser siteUser =new SiteUser();
        siteUser.setUsername(userCreateRequestDto.getUsername());
        siteUser.setEmail(userCreateRequestDto.getEmail());
        siteUser.setUuid(UUID.randomUUID());


        siteUser.setPassword(passwordEncoder.encode(
                userCreateRequestDto.getPassword()
        ));

        this.userRepository.save(siteUser);

        return siteUser;

    }

    public SiteUser findByUsername(String name) {
        return this.userRepository.findByUsername(name).get();
    }
}


//    public void login(UserRequestDto userRequestDto) {
//
//        null일 경우 검사해주세용
//        SiteUser user = userRepository.findByUserId(userRequestDto.getUserId()).get();
//
//        //비밀번호 설정
//        if(encryptHelper.isMatch(userRequestDto.getPassword(), user.getPassword())) {
//
//            httpSession.setAttribute("userId", userRequestDto.getUserId());
//            httpSession.setAttribute("role",user.getRole());
//
//            log.info(String.valueOf(user.getRole()));
//        }else throw new PasswordsNotSameException("패스워드가 일치하지 않습니다");
//
//    }
//
//    public void logout() {
//       // httpSession.invalidate();;
//    }
//}
