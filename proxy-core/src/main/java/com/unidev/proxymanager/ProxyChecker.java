package com.unidev.proxymanager;

import com.mongodb.util.JSON;
import com.unidev.platform.http.HTTPClientUtils;
import com.unidev.platform.http.ProxyHTTPClient;
import com.unidev.proxymanager.data.ProxyCheckResult;
import static com.unidev.proxymanager.data.ProxyCheckResult.CheckStatus.*;
import com.unidev.proxymanager.data.ProxyHistory;
import org.apache.http.message.BasicHeader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import static com.unidev.proxymanager.data.ProxyCheckResult.*;

import java.io.IOException;

/**
 * Proxy checking service
 */
@Service
public class ProxyChecker {

    public static final String REQUEST_URL = "http://myip.apps.universal-development.com/";
    public static final String IP_KEY = "ip";

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Check proxy status
     * @param ip Proxy ip
     * @param port Proxy Port
     * @return Return instance of @see ProxyCheckResult with check status, remote ip, connection time and so on
     */
    public ProxyCheckResult checkProxyStatus(String ip, Integer port) {

        ProxyHTTPClient proxyHTTPClient = applicationContext.getBean(ProxyHTTPClient.class);
        proxyHTTPClient.init(ip, port, HTTPClientUtils.USER_AGENTS);
        String backendResponse;
        long start, end;
        try {
            start = System.currentTimeMillis();
            backendResponse = proxyHTTPClient.get(REQUEST_URL,
                    new BasicHeader("Content-Type", "application/json"));
            end = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
            return ProxyCheckResult.FAILED;
        }

        JSONObject jsonObject = new JSONObject(backendResponse);
        String remoteIp = jsonObject.getString(IP_KEY);

        return newCheckResult().withCheckStatus(OK)
                            .withRemoteIp(remoteIp)
                            .withRequestTime(end - start);
    }

}
