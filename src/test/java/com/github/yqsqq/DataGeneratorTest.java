package com.github.yqsqq;

import jdk.nashorn.internal.runtime.regexp.RegExp;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by qsyou on 24/07/2017.
 */
public class DataGeneratorTest {
    @Test
    public void should_return_matched_username_2000_times() throws Exception {
        //given
        Pattern pattern = Pattern.compile("^[a-zA-Z][-_a-zA-Z\\d]{5,17}$");
        DataGenerator dataGenerator = new DataGenerator();
        //when
        for (int i = 0; i < 2000; i++) {
            String username = dataGenerator.getUsername();
            assertThat(pattern.matcher(username).find(), is(true));
        }
    }

    @Test
    public void should_return_matched_password_2000_times() throws Exception {
        //given
        Pattern pattern = Pattern.compile("\\S{6,18}");
        DataGenerator dataGenerator = new DataGenerator();
        //when
        for (int i = 0; i < 2000; i++) {
            String password = dataGenerator.getPassword();
            assertThat(pattern.matcher(password).find(), is(true));
        }
    }

    @Test
    public void should_return_matched_display_name_2000_times() throws Exception {
        //given
        DataGenerator dataGenerator = new DataGenerator();
        //when
        for (int i = 0; i < 2000; i++) {
            String displayName = dataGenerator.getDisplayName();
            assertThat(displayName.length() >= 1, is(true));
            assertThat(displayName.length() <= 18, is(true));
        }
    }

    @Test
    public void should_return_matched_email_2000_times() throws Exception {
        //given
        Pattern pattern = Pattern.compile("^[a-zA-Z\\d][\\.-_a-zA-Z\\d]*[a-zA-Z\\d]@[a-z\\d]*\\.[a-z]{2,5}$");
        DataGenerator dataGenerator = new DataGenerator();
        //when
        for (int i = 0; i < 2000; i++) {
            String email = dataGenerator.getEmail();
            System.out.println(email);
            assertThat(pattern.matcher(email).find(), is(true));
        }
    }
}
