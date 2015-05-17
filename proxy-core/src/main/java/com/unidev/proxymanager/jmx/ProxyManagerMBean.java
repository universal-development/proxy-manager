package com.unidev.proxymanager.jmx;


import com.unidev.proxymanager.ProxyHistoryService;
import com.unidev.proxymanager.data.ProxyHistory;
import com.unidev.proxymanager.data.ProxyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "services:name=ProxyManagerMBean", description = "ProxyManagerMBean")
public class ProxyManagerMBean {

    @Autowired
    private ProxyHistoryRepository proxyHistoryRepository;

    @Autowired
    private ProxyHistoryService proxyHistoryService;

    @ManagedOperation(description = "List proxies")
    @ManagedOperationParameters({})
    public String listProxies() {
        StringBuilder list = new StringBuilder();
        Iterable<ProxyHistory> proxyHistoryIterable = proxyHistoryRepository.findAll();
        for(ProxyHistory proxyHistory : proxyHistoryIterable) {
            list.append(proxyHistory);
            list.append("\n");
        }
        return list.toString();
    }

    @ManagedOperation(description = "Add proxy")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "ip", description = "Proxy IP"),
            @ManagedOperationParameter(name = "port", description = "Proxy port")
    })
    public void addProxy(String ip, String port) {
        Integer intPort = Integer.parseInt(port);

        proxyHistoryService.addProxy(ip, intPort);
    }


}
