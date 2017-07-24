package com.github.yqsqq;

import com.csvreader.CsvWriter;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.apache.commons.lang.RandomStringUtils.random;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang.math.RandomUtils.nextInt;

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

        int fileCount = 5;
        int recordCount = 500;

        for (int i = 0; i < fileCount; i++) {
            String registerFilePath = String.format("./register00%d.csv", i);
            String signInFilePath = String.format("./sign_in00%d.csv", i);

            try {
                CsvWriter csvRegisterWriter = new CsvWriter(registerFilePath, ',', Charset.forName("GBK"));
                CsvWriter csvSignInWriter = new CsvWriter(signInFilePath, ',', Charset.forName("GBK"));

                for (int record = 0; record < recordCount; record++) {
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
        return randomAlphanumeric(1) +
                random(nextInt(10) + 1, (BASE + ".").toCharArray()) +
                randomAlphanumeric(1) +
                "@" +
                random(nextInt(5) + 1, (LOWER_CASE + "0123456789").toCharArray()) +
                "." +
                random(nextInt(4) + 2, LOWER_CASE.toCharArray());
    }

    public String getDisplayName() {
        return randomAlphanumeric(nextInt(18) + 1);
    }

    public String getUsername() {
        return randomAlphabetic(1)
                + random(nextInt(13) + 5, (BASE + "-").toCharArray());
    }

    public String getPassword() {
        return randomAlphanumeric(nextInt(13) + 6);
    }

}
