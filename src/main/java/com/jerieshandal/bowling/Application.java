package com.jerieshandal.bowling;

import com.jerieshandal.bowling.file.CLIParser;
import com.jerieshandal.bowling.game.Bowling;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private Bowling bowling;
    @Autowired
    private CLIParser cliParser;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String filePath = cliParser.parse(args);
        if (StringUtils.isBlank(filePath)) return;
        System.out.println(bowling.processAndPrintScore(filePath));
    }
}
