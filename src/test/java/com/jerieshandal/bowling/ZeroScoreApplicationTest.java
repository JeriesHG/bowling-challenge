package com.jerieshandal.bowling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(args = "-fsrc/test/resources/files/sample-zero.txt")
class ZeroScoreApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void runTest() throws Exception {
    }
}
