package com.unidev.proxymanager.jmx;


import com.unidev.proxymanager.ProxyHistoryService;
import com.unidev.proxymanager.data.ProxyHistory;
import com.unidev.proxymanager.data.ProxyHistoryRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    @ManagedOperation(description = "Import proxies")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "fileName", description = "File path")
    })
    public String importProxies(String fileName) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("Import ing file: " + fileName);
        result.append("\n");
        List<String> lines = FileUtils.readLines(new File(fileName));
        int lineId = 1;
        for(String line : lines) {
            try {
                String[] split = line.trim().split("\\:");
                String ip = split[0];
                Integer port = Integer.parseInt(split[1] + "");
                proxyHistoryService.addProxy(ip, port);
            }catch(Exception e) {
                e.printStackTrace();
                result.append(lineId + " : " + e.getMessage() + "\n");
            }
        }

        return result.toString();
    }


}
