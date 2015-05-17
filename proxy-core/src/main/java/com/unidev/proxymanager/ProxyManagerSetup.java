package com.unidev.proxymanager;

import com.unidev.beancrud.BeanCrudService;
import com.unidev.jobmanager.JobExecutionService;
import com.unidev.proxymanager.data.ProxyHistory;
import com.unidev.proxymanager.data.ProxyHistoryRepository;
import com.unidev.proxymanager.executor.ProxyCheckingExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ProxyManagerSetup {

    @Autowired
    private JobExecutionService jobExecutionService;

    @Autowired
    private ProxyCheckingExecutor proxyCheckingExecutor;

    @Autowired
    private BeanCrudService beanCrudService;

    @Autowired
    private ProxyHistoryRepository proxyHistoryRepository;

    @PostConstruct
    public void setup() {

        jobExecutionService.addExecutor(proxyCheckingExecutor);

        beanCrudService.addRepository(ProxyHistory.class, proxyHistoryRepository);
    }

}
