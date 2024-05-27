package com.example.youtubeSheet;


import com.example.youtubeSheet.dto.ErrorMessageDto;
import com.example.youtubeSheet.entity.exception.EmptyTitleException;
import com.example.youtubeSheet.entity.exception.PasswordsNotSameException;
import com.example.youtubeSheet.entity.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    //비밀번호 다를때
    @ExceptionHandler(PasswordsNotSameException.class)
    public ResponseEntity<ErrorMessageDto> handlePasswordsNotSame(
            PasswordsNotSameException ex
    ){
        ErrorMessageDto errorMessageDto=new ErrorMessageDto(ex.getMessage());
        return new ResponseEntity<>(errorMessageDto, HttpStatus.BAD_REQUEST);
    }

    //인증되지 않은 사용자
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorMessageDto> handleAuthorizedException(
            UnauthorizedException ex
    ){
        ErrorMessageDto errorMessageDto=new ErrorMessageDto(ex.getMessage());
        return new ResponseEntity<>(errorMessageDto, HttpStatus.UNAUTHORIZED);
    }
    //title 수정 공백
    @ExceptionHandler(EmptyTitleException.class)
    public ResponseEntity<ErrorMessageDto> handleAuthorizedException(
            EmptyTitleException ex
    ){
        ErrorMessageDto errorMessageDto=new ErrorMessageDto(ex.getMessage());
        return new ResponseEntity<>(errorMessageDto, HttpStatus.NOT_ACCEPTABLE);
    }




}
