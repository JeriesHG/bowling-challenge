package com.jerieshandal.bowling.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Messages {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String path) {
        return messageSource.getMessage(path, null, Locale.getDefault());
    }
}
