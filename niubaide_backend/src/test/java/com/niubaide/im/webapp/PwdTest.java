package com.niubaide.im.webapp;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class PwdTest {

    @Autowired
    @Qualifier("encryptor")
    private StringEncryptor encryptor;

    @Test
    public void getPass() {
        String url = encryptor.encrypt("jdbc:mysql://81.68.219.235:3306/nchat?useUnicode=true&characterEncoding=utf8");
        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("mysql");
        System.out.println(url);
        System.out.println(name+"----------------");
        System.out.println(password+"----------------");
//        Assert.assertTrue(name.length() > 0);
//        Assert.assertTrue(password.length() > 0);
    }

    @Test
    public void fdfs(){

    }
}
