package com.posmosalimos.geulgwi;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.jupiter.api.Test;

public class JasyptTest {

    @Test
    public void jasyptTest(){

        String password = "rlaqnadmlqlalfqjsgh123123";

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4);
        encryptor.setPassword(password);
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");

        String content = "";
        String encryptedContent = encryptor.encrypt(content);
        String decryptedContent = encryptor.decrypt("eBv0hbOQ8AUKyfUrZqtEOurEJrunjvztI7AnIArX7DzPuaxb1dQ746MSMs/8BzKIzq/wvwOBRWoeWgiqZH4KShALxSBEKSZR");

        System.out.println("Enc : " + encryptedContent + ", Dec : " + decryptedContent);
    }
}
