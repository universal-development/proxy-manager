package com.unidev.proxymanager;

import com.unidev.proxymanager.data.ProxyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * Proxy history service
 */
@Service
public class ProxyHistoryService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ProxyHistoryRepository proxyHistoryRepository;



}
