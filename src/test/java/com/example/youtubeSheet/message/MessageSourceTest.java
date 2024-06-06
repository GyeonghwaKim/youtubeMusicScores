package com.example.youtubeSheet.message;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {
    @Autowired
    MessageSource ms;

    @Test
    void youtubeSheetMusicMessage(){

        String result=ms.getMessage("youtubeSheetMusic",null,null);
        assertThat(result).isEqualTo("YouTube Sheet Music");


    }

    @Test
    void internationalMessage(){
        //assertThat(ms.getMessage("login",null,null)).isEqualTo("Login");
        //assertThat(ms.getMessage("login",null,Locale.ENGLISH)).isEqualTo("Login");
        assertThat(ms.getMessage("login",null, Locale.KOREA)).isEqualTo("로그인");

    }

    @Test
    void notFoundMessageCode() {

        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }
}
