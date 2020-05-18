package com.pixiv.imgdetail;

import com.pixiv.imgdetail.utils.IDUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImgdetailApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(IDUtils.createID());
    }

}
