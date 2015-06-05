package com.unidev.proxymanager.executor;

import com.unidev.platform.jobs.AbstractCallable;
import com.unidev.platform.jobs.JobFactory;

/**
 * Job factory for proxy checking jobs
 */
public class ProxyCheckerJobFactory implements JobFactory {
    @Override
    public AbstractCallable newJob() {
        return new ProxyCheckerJob(); // we can think about instantiation through spring
    }
}
