package com.github.antego.authorreg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class NamedAccountSerializer {
    private ObjectMapper objectMapper = new ObjectMapper();

    public byte[] serialize(NamedAccount account) throws JsonProcessingException {
        String stringifiedAccount = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(account);
        return stringifiedAccount.getBytes(StandardCharsets.UTF_8);
    }
}
