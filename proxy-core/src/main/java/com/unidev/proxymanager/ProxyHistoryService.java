package com.unidev.proxymanager;

import com.unidev.proxymanager.data.ProxyHistory;
import com.unidev.proxymanager.data.ProxyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Proxy history service
 */
@Service
public class ProxyHistoryService {

    @Autowired(required = false)
    private MongoTemplate mongoTemplate;

    @Autowired(required = false)
    private ProxyHistoryRepository proxyHistoryRepository;

    /**
     * Add new proxy for history tracking
     * @param ip
     * @param port
     */
    public void addProxy(String ip, Integer port) {

        ProxyHistory proxyHistory = new ProxyHistory();
        proxyHistory.setIp(ip);
        proxyHistory.setPort(port);
        proxyHistory.setAddDate(new Date());

        proxyHistoryRepository.save(proxyHistory);

    }


}
