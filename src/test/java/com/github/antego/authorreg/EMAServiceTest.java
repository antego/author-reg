package com.github.antego.authorreg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;


@RunWith(SpringRunner.class)
@RestClientTest(EMAService.class)
public class EMAServiceTest {
    @Value("${ema.url}")
    private String emaUrl;

    @Autowired
    private EMAService emaService;

    @Autowired
    private MockRestServiceServer server;

    EMAAccount account = new EMAAccount();
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturnNamesAccount() throws JsonProcessingException {
        String name = "Name";
        String address = "813744315345913484571394597859134";
        String privateKey = "6134589561308765081347529335786513495861349";
        account.setAddress(new BigInteger(address));
        account.setPrivateKey(new BigInteger(privateKey));
        server.expect(requestTo(emaUrl)).andRespond(MockRestResponseCreators.withSuccess(mapper.writeValueAsString(account), MediaType.APPLICATION_JSON));

        NamedAccount namedAccount = emaService.getNamedAccount("Name");
        assertEquals(name, namedAccount.getName());
        assertEquals(address, namedAccount.getAddress());
        assertEquals(privateKey, namedAccount.getPrivateKey());
    }
}