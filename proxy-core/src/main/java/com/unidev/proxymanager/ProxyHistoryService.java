package com.unidev.proxymanager;

import com.google.common.collect.Lists;
import com.unidev.proxymanager.data.ProxyHistory;
import com.unidev.proxymanager.data.ProxyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Proxy history service
 */
@Service
public class ProxyHistoryService {

    @Autowired
    private ProxyHistoryRepository proxyHistoryRepository;

    /**
     * Add new proxy for history tracking
     * @param ip
     * @param port
     */
    public void addProxy(String ip, Integer port) {

        ProxyHistory proxyHistory = new ProxyHistory();
        proxyHistory.setId((ip + ":" + port).hashCode() + "");
        proxyHistory.setIp(ip);
        proxyHistory.setPort(port);
        proxyHistory.setAddDate(new Date());

        proxyHistoryRepository.save(proxyHistory);

    }

    /**
     * Remove proxy from indexing
     * @param ip
     * @param port
     */
    public void removeProxy(String ip, Integer port) {
        proxyHistoryRepository.delete( (ip + ":" + port).hashCode() + "");
    }

    /**
     * List all available proxies
     * @return
     */
    public Collection<ProxyHistory> listProxies() {
        Iterable<ProxyHistory> proxyHistory = proxyHistoryRepository.findAll();
        return Lists.newArrayList(proxyHistory);
    }


}
