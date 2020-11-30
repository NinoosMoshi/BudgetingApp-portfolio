package com.ninos.controller;

import com.ninos.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String getLogin (ModelMap model)
    {
        User user = new User();
        model.put("user", user);

        return "login";
    }

}
