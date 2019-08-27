package org.liu.websocket.controller;

import org.liu.websocket.constants.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("websocket")
@Controller
public class WebSocketController {

    @PostMapping("login")
    public String login(String username, ModelMap modelMap, HttpServletRequest request){
        request.getSession().setAttribute(Constants.SESSION_KEY_USERNAME, username);
        return "/chat.jsp";
    }

}
