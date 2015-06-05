package com.unidev.proxymanager.integration;

import com.unidev.proxymanager.ProxyChecker;
import com.unidev.proxymanager.domain.ProxyCheckResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/platform-common-beans.xml", "/proxy-core-beans.xml"})
public class ProxyCheckerTest {

    private static Logger LOG = LoggerFactory.getLogger(ProxyCheckerTest.class);


    @Autowired
    private ProxyChecker proxyChecker;

    @Test
    public void testProxyAvailability() {
        proxyChecker.setRequestUrl("http://myipdc.apps.universal-development.com");
        ProxyCheckResult proxyCheckResult = proxyChecker.checkProxyStatus("192.168.0.50", 9100);
        LOG.info("Proxy check result {}", proxyCheckResult);
    }

}
