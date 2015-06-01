package com.unidev.proxymanager;

import com.unidev.proxymanager.data.ProxyHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ProxyHistoryService proxyHistoryService;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/proxy/list")
    @ResponseBody
    public Collection<ProxyHistory> listProxies() {
        return proxyHistoryService.listProxies();
    }

}
