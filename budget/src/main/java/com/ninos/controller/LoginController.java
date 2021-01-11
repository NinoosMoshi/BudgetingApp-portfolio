package com.ninos.controller;



import com.ninos.domain.User;
import com.ninos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String getLogin (ModelMap model)
    {
        User user = new User();
        model.put("user", user);

        return "login";
    }



    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String getRegister (ModelMap model)
    {
        User user = new User();
        model.put("user", user);

        return "register";
    }



    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String postRegister (@ModelAttribute User user, ModelMap model)
    {
        if (!StringUtils.isEmpty(user.getPassword()) && !StringUtils.isEmpty(user.getConfirmPassword()))
        {
            if (!user.getPassword().equals(user.getConfirmPassword()))
            {
                model.put("error", "Passwords don't match");
                return "register";
            }
        }

        if (StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getConfirmPassword()))
        {
            model.put("error", "You must choose a password");
            return "register";
        }

        user = userService.saveUser(user);

        // dynamically logging in the user via Spring Security
        Authentication auth =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);

        return "redirect:/budgets";
    }


    




}
