package com.unidev.proxymanager.executor;

import com.unidev.platform.jobs.AbstractCallable;
import com.unidev.platform.jobs.ExecutionResult;
import com.unidev.proxymanager.domain.ProxyCheckResult;

/**
 * Job for proxy checking
 */
public class ProxyCheckerJob implements AbstractCallable {
    @Override
    public ExecutionResult<ProxyCheckResult> call() throws Exception {



/*        String ip = proxyHistory.getIp();
        Integer port = proxyHistory.getPort();

        ProxyCheckResult proxyCheckResult = proxyChecker.checkProxyStatus(ip, port);
*/


        return null;
    }
}
