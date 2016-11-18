package com.github.antego.authorreg;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class NamedAccountSerializerTest {
    private NamedAccountSerializer serializer = new NamedAccountSerializer();

    @Test
    public void checkSerializedAccount() throws JsonProcessingException {
        NamedAccount namedAccount = new NamedAccount();
        namedAccount.setName("Name");
        namedAccount.setAddress("aprodghpoadfuh345uih");
        namedAccount.setPrivateKey("privatekey");

        String expectedJson = "{\"name\":\"Name\",\"address\":\"aprodghpoadfuh345uih\",\"privateKey\":\"privatekey\"}";
        assertEquals(expectedJson, new String(serializer.serialize(namedAccount), StandardCharsets.UTF_8));
    }
}