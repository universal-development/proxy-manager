package com.unidev.proxymanager;

import com.unidev.proxymanager.data.ProxyCheckResult;
import com.unidev.proxymanager.data.ProxyHistory;
import org.springframework.stereotype.Service;

/**
 * Proxy checking service
 */
@Service
public class ProxyChecker {

    /**
     * Check proxy status
     * @param ip Proxy ip
     * @param port Proxy Port
     * @return Return instance of @see ProxyCheckResult with check status, remote ip, connection time and so on
     */
    public ProxyCheckResult checkProxyStatus(String ip, Integer port) {

        return ProxyCheckResult.FAILED;
    }

}
