package com.unidev.proxymanager.integration;

import com.unidev.proxymanager.ProxyChecker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpoth:/platform-common-beans.xml"})
public class ProxyCheckerTest {

    @Autowired
    private ProxyChecker proxyChecker;

    @Test
    public void testProxyAvailability() {

    }

}
