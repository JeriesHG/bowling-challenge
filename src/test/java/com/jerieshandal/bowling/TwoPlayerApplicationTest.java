package com.jerieshandal.bowling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(args = "-fsrc/test/resources/files/sample.txt")
public class TwoPlayerApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testRun() throws Exception {
    }
}
