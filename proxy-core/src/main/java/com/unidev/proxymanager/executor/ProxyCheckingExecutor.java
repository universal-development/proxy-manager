package com.unidev.proxymanager.executor;

import com.unidev.jobmanager.JobExecutor;
import com.unidev.platform.utils.RandomUtils;
import com.unidev.proxymanager.ProxyHistoryService;
import com.unidev.proxymanager.data.ProxyHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProxyCheckingExecutor implements JobExecutor {

    @Autowired
    private ProxyHistoryService proxyHistoryService;

    @Autowired
    private RandomUtils randomUtils;

    @Override
    public String getName() {
        return "Proxy checker service";
    }

    @Override
    public boolean canHandle(String jobDataId) {
        return true;
    }

    @Override
    public boolean handle(String jobDataId) {
        return false;
    }

    @Override
    public boolean executeRandom() {
        ProxyHistory proxyHistory = randomUtils.randomValue(proxyHistoryService.listProxies());
        return handle(proxyHistory.getId());
    }
}
