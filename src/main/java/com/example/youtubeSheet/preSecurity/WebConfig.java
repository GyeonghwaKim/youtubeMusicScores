package com.example.youtubeSheet.preSecurity;


import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(authenticationInterceptor)
               .addPathPatterns("/saveSheets")
               .addPathPatterns("/saveSheets/**")
               .addPathPatterns("/saveLists")
               .addPathPatterns("/sheetLists/**");
//               .addPathPatterns("/users")
//               .addPathPatterns("/users/**");

    }
}
