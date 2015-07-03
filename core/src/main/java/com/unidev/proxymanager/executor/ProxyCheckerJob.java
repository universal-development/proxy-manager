package com.unidev.proxymanager.executor;

import com.unidev.platform.ApplicationContextProvider;
import com.unidev.platform.jobs.AbstractCallable;
import com.unidev.platform.jobs.ExecutionResult;
import com.unidev.platform.utils.RandomUtils;
import com.unidev.proxymanager.ProxyChecker;
import com.unidev.proxymanager.ProxyHistoryService;
import com.unidev.proxymanager.data.ProxyHistory;
import com.unidev.proxymanager.domain.ProxyCheckResult;

import java.util.Collection;

/**
 * Job for proxy checking
 */
public class ProxyCheckerJob implements AbstractCallable {
    @Override
    public ExecutionResult<ProxyCheckResult> call() throws Exception {
        ProxyHistoryService proxyHistoryService = ApplicationContextProvider.getInstance().getBean(ProxyHistoryService.class);
        Collection<ProxyHistory> proxyList = proxyHistoryService.listProxies();
        if (proxyList == null || proxyList.isEmpty()) {
            return null;
        }
        RandomUtils randomUtils = ApplicationContextProvider.getInstance().getBean(RandomUtils.class);
        ProxyHistory proxyHistory = randomUtils.randomValue(proxyList);

        ProxyChecker proxyChecker = ApplicationContextProvider.getInstance().getBean(ProxyChecker.class);

        String ip = proxyHistory.getIp();
        Integer port = proxyHistory.getPort();

        ProxyCheckResult proxyCheckResult = proxyChecker.checkProxyStatus(ip, port);
        proxyHistoryService.updateProxyHistory(proxyHistory, proxyCheckResult);
        return new ExecutionResult<ProxyCheckResult>().addResult(proxyCheckResult);
    }
}
