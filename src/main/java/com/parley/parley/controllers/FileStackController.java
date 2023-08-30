package com.parley.parley.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileStackController {
    @Value("${filestack.api.key}")
    private String IGotTheKeys;

    @Value("${profanity.x.rapid.api.key}")
    private String xKey;

    @Value("${profanity.x.rapid.api.host}")
    private String xHost;


    @GetMapping(value = "/js/keys.js", produces = "text/javascript")
    @ResponseBody
    public String keys(){
        return "const iGotTheKey = \"" + IGotTheKeys + "\";" +
                "const XRapidKey = \"" + xKey + "\";" +
                "const XRapidHost = \"" + xHost + "\";";
    }
}
