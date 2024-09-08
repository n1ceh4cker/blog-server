package com.dev.blog.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Message {
    private final MessageSource messageSource;
    private MessageSourceAccessor accessor;

    public Message(MessageSource messageSource) {
        this.messageSource = messageSource;
        accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }
    public String get(String code) {
        return accessor.getMessage(code);
    }

    public String getStatusCode(String code){
        code = code.trim();
        //return Integer.parseInt(accessor.getMessage(code).trim());
        return accessor.getMessage(code).trim();
    }

    public void setLocale(String keyLang){

        accessor = new MessageSourceAccessor(messageSource, Locale.forLanguageTag(keyLang));
    }

}

