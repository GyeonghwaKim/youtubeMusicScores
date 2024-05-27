package com.example.youtubeSheet.dto;

import com.example.youtubeSheet.annotation.Unique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

@Getter
@Setter
public class UserCreateRequestDto {


    @NotBlank(message = "사용자 ID는 필수 항목입니다")
    private String username;


    @NotBlank(message = "비밀번호는 필수 항목입니다")
    private String password;

    @NotBlank(message = "비밀번호 확인 필수 항목입니다")
    private String confirmPassword;

    @Email
    @NotBlank(message = "이메일은 필수 항목입니다")
    private String email;


    public UserCreateRequestDto() {
    }
}
