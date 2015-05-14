package com.unidev.proxymanager;

import com.mongodb.util.JSON;
import com.unidev.platform.http.HTTPClientUtils;
import com.unidev.platform.http.ProxyHTTPClient;
import com.unidev.proxymanager.data.ProxyCheckResult;
import static com.unidev.proxymanager.data.ProxyCheckResult.CheckStatus.*;
import com.unidev.proxymanager.data.ProxyHistory;
import org.apache.http.message.BasicHeader;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import static com.unidev.proxymanager.data.ProxyCheckResult.*;

import java.io.IOException;

/**
 * Proxy checking service
 */
@Service
public class ProxyChecker {

    private static Logger LOG = LoggerFactory.getLogger(ProxyChecker.class);

    @Value("proxy.check.url")
    private String requestUrl;

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
        proxyHTTPClient.setConnectionTimeout(30000);
        String backendResponse;
        long start, end;
        try {
            start = System.currentTimeMillis();
            backendResponse = proxyHTTPClient.get(requestUrl,
                    new BasicHeader("Content-type", "application/json"),
                    new BasicHeader("Accept", "*/*")
            );
            end = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
            return ProxyCheckResult.FAILED;
        }

        LOG.debug("{}", backendResponse);

        JSONObject jsonObject = new JSONObject(backendResponse);
        String remoteIp = jsonObject.getString(IP_KEY);

        return newCheckResult().withCheckStatus(OK)
                            .withRemoteIp(remoteIp)
                            .withRequestTime(end - start);
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
