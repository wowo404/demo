package org.demo.controller;

import org.demo.config.UEditorConfigConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("ueditor")
@RestController
public class UEditorConfigController {

    @Autowired
    private UEditorConfigConfiguration uEditorConfigConfiguration;

    @GetMapping("getConfig")
    public String getConfig(){
        System.out.println(uEditorConfigConfiguration);
        return "success";
    }

}
