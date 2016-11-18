package com.github.antego.authorreg;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EMAService {
    private final RestTemplate restTemplate;

    public EMAService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public NamedAccount getNamedAccount(String name) {
        EMAAccount account = restTemplate.getForObject("/register", EMAAccount.class);
        NamedAccount namedAccount = new NamedAccount();
        namedAccount.setName(name);
        namedAccount.setAddress(account.getAddress().toString());
        namedAccount.setPrivateKey(account.getPrivateKey().toString());
        return namedAccount;
    }
}
