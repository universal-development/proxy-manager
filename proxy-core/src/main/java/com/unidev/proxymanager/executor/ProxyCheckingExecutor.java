package com.unidev.proxymanager.executor;

import com.unidev.jobmanager.JobExecutor;
import com.unidev.platform.utils.RandomUtils;
import com.unidev.proxymanager.ProxyChecker;
import com.unidev.proxymanager.ProxyHistoryService;
import com.unidev.proxymanager.data.ProxyHistory;
import com.unidev.proxymanager.data.ProxyHistoryRepository;
import com.unidev.proxymanager.domain.ProxyCheckResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProxyCheckingExecutor implements JobExecutor {

    public static final int MAX_SCORE = 10;
    private static final int MIN_SCORE = -10;

    private static Logger LOG = LoggerFactory.getLogger(ProxyCheckingExecutor.class);


    @Autowired
    private ProxyHistoryService proxyHistoryService;

    @Autowired
    private RandomUtils randomUtils;

    @Autowired
    private ProxyHistoryRepository proxyHistoryRepository;

    @Autowired
    private ProxyChecker proxyChecker;

    @Override
    public String getName() {
        return "Proxy checker service";
    }

    @Override
    public boolean canHandle(String jobDataId) {
        return true;
    }

    protected boolean checkProxy(ProxyHistory proxyHistory) {
        String ip = proxyHistory.getIp();
        Integer port = proxyHistory.getPort();

        ProxyCheckResult proxyCheckResult = proxyChecker.checkProxyStatus(ip, port);

        proxyCheckResult.setCheckStatus(proxyCheckResult.getCheckStatus());
        proxyHistory.setLastUpdate(new Date());
        proxyHistory.setRequestTime(proxyCheckResult.getRequestTime() / 1000D);
        proxyHistory.addRequestCount(1);

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

        return true;
    }

    @Override
    public boolean handle(String jobDataId) {
        ProxyHistory proxyHistory = proxyHistoryRepository.findOne(jobDataId);
        LOG.info("Proxy history {} {} ", proxyHistory, jobDataId);
        return checkProxy(proxyHistory);
    }

    @Override
    public boolean executeRandom() {
        ProxyHistory proxyHistory = randomUtils.randomValue(proxyHistoryService.listProxies());
        return checkProxy(proxyHistory);
    }
}
