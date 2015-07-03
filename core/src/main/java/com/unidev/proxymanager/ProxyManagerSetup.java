package com.unidev.proxymanager;

import com.unidev.proxymanager.data.ProxyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ProxyManagerSetup {


    @Autowired
    private ProxyChecker proxyChecker;

    @PostConstruct
    public void setup() {
        proxyChecker.setRequestUrl("http://myipdc.apps.universal-development.com");



    }

}
