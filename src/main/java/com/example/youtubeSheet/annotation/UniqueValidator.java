package com.example.youtubeSheet.annotation;

import com.example.youtubeSheet.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;


@RequiredArgsConstructor
public class UniqueValidator implements ConstraintValidator<Unique,String > {

    private final UserRepository userRepository;

    @Override
    public void initialize(Unique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null||value.isEmpty()) return true;

        return !userRepository.existsByUsername(value);
    }
}
