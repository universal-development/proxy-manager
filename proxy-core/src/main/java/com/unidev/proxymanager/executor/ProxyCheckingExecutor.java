package com.unidev.proxymanager.executor;

import com.unidev.jobmanager.JobExecutor;
import org.springframework.stereotype.Service;

@Service
public class ProxyCheckingExecutor implements JobExecutor {
    @Override
    public String getName() {
        return "Proxy checker service";
    }

    @Override
    public boolean canHandle(String jobDataId) {
        return false;
    }

    @Override
    public boolean handle(String jobDataId) {
        return false;
    }

    @Override
    public boolean executeRandom() {
        return false;
    }
}
