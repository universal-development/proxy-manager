package com.unidev.proxymanager.integration;


import com.unidev.proxymanager.ProxyHistoryService;
import com.unidev.proxymanager.data.ProxyHistory;
import com.unidev.proxymanager.data.ProxyHistoryRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/platform-common-beans.xml", "/proxy-core-beans.xml"})
@Ignore
public class ProxyCrudTest {

    private static Logger LOG = LoggerFactory.getLogger(ProxyCrudTest.class);


    @Autowired
    private ProxyHistoryRepository proxyHistoryRepository;

    @Autowired
    private ProxyHistoryService proxyHistoryService;

    @Test
    public void testProxyAdding() {

        proxyHistoryService.addProxy("192.168.0.50", 9000);

        Iterable<ProxyHistory> iterable = proxyHistoryRepository.findAll();
        LOG.debug("Iterable {}", iterable);
    }

}
