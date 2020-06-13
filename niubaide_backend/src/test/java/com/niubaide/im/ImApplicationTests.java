package com.niubaide.im;

import com.niubaide.im.mapper.TbUserMapper;
import com.niubaide.im.pojo.TbUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ImApplicationTests {

    @Autowired
    private TbUserMapper userMapper;
    @Test
    void contextLoads() {
    }

}
