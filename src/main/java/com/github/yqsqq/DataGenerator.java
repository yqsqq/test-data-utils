package com.github.yqsqq;

import com.csvreader.CsvWriter;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by qsyou on 24/07/2017.
 */
public class DataGenerator {

    public static final String BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
    public static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        new DataGenerator().write();
    }

    public void write() {

        for (int i = 0; i < 3; i++) {
            String registerFilePath = String.format("./register00%d.csv", i);
            String signInFilePath = String.format("./sign_in00%d.csv", i);

            try {
                CsvWriter csvRegisterWriter = new CsvWriter(registerFilePath, ',', Charset.forName("GBK"));
                CsvWriter csvSignInWriter = new CsvWriter(signInFilePath, ',', Charset.forName("GBK"));

                for (int record = 0; record < 500; record++) {
                    String username = getUsername();
                    String password = getPassword();
                    String[] registerRecord = {getEmail(), getDisplayName(), username, password};
                    csvRegisterWriter.writeRecord(registerRecord);

                    String[] signInRecord = {"password", "0", username, password};
                    csvSignInWriter.writeRecord(signInRecord);

                }
                csvRegisterWriter.close();
                csvSignInWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public String getEmail() {
        return RandomStringUtils.randomAlphanumeric(1) +
                RandomStringUtils.random(RandomUtils.nextInt(10) + 1, (BASE + ".").toCharArray()) +
                RandomStringUtils.randomAlphanumeric(1) + "@" +
                RandomStringUtils.random(RandomUtils.nextInt(5) + 1, (LOWER_CASE + "0123456789").toCharArray()) +
                "." + RandomStringUtils.random(RandomUtils.nextInt(4) + 2, LOWER_CASE.toCharArray());
    }

    public String getDisplayName() {
        return RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(18) + 1);
    }

    public String getUsername() {
        return RandomStringUtils.randomAlphabetic(1)
                + RandomStringUtils.random(RandomUtils.nextInt(13) + 5, (BASE + "-").toCharArray());
    }

    public String getPassword() {
        return RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(13) + 6);
    }

}
