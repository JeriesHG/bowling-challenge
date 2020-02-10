package com.jerieshandal.bowling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(args = "-fsrc/test/resources/files/sample-perfect.txt")
public class PerfectGameApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testRun() throws Exception {
    }
}
