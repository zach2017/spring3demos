package zac.demo.hello.controller;

import jakarta.annotation.PostConstruct;

import java.util.HashMap;

import com.duosecurity.Client;
import com.duosecurity.exception.DuoException;
import com.duosecurity.model.Token;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;


public class DuoSecureController {
/*
    @Value("${duo.api.host}")
    private String apiHost;

    @Value("${duo.clientId}")
    private String clientId;

    @Value("${duo.clientSecret}")
    private String clientSecret;

    @Value("${duo.redirect.uri}")
    private String redirectUri;

    @Value("${duo.failmode}")
    private String failmode;

    private Map<String, String> stateMap;

    private Client duoClient;

    /**
     * Create and initialize the Duo Client.
     *
     * @throws DuoException For problems creating the Clients

    @PostConstruct
    public void initializeDuoClient() throws DuoException {
        stateMap = new HashMap<>();
        duoClient = new Client.Builder(clientId, clientSecret, apiHost, redirectUri).build();

    /* Example of setting optional fields
    duoClient = new Client.Builder(clientId, clientSecret, apiHost, redirectUri)
            .setUseDuoCodeAttribute(false)
            .setCACerts(customCerts)
            .appendUserAgentInfo("custom string")
            .build();

    }*/


}