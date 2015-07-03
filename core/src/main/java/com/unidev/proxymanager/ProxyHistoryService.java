package com.unidev.proxymanager;

import com.google.common.collect.Lists;
import com.unidev.proxymanager.data.ProxyHistory;
import com.unidev.proxymanager.data.ProxyHistoryRepository;
import com.unidev.proxymanager.domain.ProxyCheckResult;
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

    public static final int MAX_SCORE = 10;
    private static final int MIN_SCORE = -10;


    @Autowired
    private ProxyHistoryRepository proxyHistoryRepository;

    /**
     * Add new proxy for history tracking
     * @param ip
     * @param port
     */
    public void addProxy(String ip, Integer port) {

        long count = proxyHistoryRepository.count();

        ProxyHistory proxyHistory = new ProxyHistory();
        proxyHistory.setId(count + 1);
        proxyHistory.setIp(ip);
        proxyHistory.setPort(port);
        proxyHistory.setAddDate(new Date());

        proxyHistoryRepository.save(proxyHistory);

    }

    /**
     * Remove proxy from indexing
     */
    public void removeProxy(Long id) {
        proxyHistoryRepository.delete( id );
    }

    /**
     * List all available proxies
     * @return
     */
    public Collection<ProxyHistory> listProxies() {
        Iterable<ProxyHistory> proxyHistory = proxyHistoryRepository.findAll();
        return Lists.newArrayList(proxyHistory);
    }

    /**
     * Update proxy history
     * @param proxyCheckResult Proxy check result
     */
    public void updateProxyHistory(ProxyHistory proxyHistory, ProxyCheckResult proxyCheckResult) {

        proxyCheckResult.setCheckStatus(proxyCheckResult.getCheckStatus());
        proxyHistory.setLastUpdate(new Date());
        proxyHistory.setRequestTime(proxyCheckResult.getRequestTime() / 1000D);
        proxyHistory.addRequestCount(1);
        proxyHistory.setRemoteIp(proxyCheckResult.getRemoteIp());

        if (proxyCheckResult.getCheckStatus() == ProxyCheckResult.CheckStatus.OK) {
            proxyHistory.addScore(1);
        }

        if (proxyCheckResult.getCheckStatus() == ProxyCheckResult.CheckStatus.FAILED) {
            proxyHistory.addScore(-1);
        }

        if (proxyHistory.getScore() > MAX_SCORE) {
            proxyHistory.setScore(MAX_SCORE);
        }

        if (proxyHistory.getScore() < MIN_SCORE) {
            proxyHistory.setScore(MIN_SCORE);
        }

        proxyHistoryRepository.save(proxyHistory);
    }


}
