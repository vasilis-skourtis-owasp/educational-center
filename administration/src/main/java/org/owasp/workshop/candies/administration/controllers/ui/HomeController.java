package org.owasp.workshop.candies.administration.controllers.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.owasp.workshop.candies.administration.directories.URIs.REDIRECT_TO_COURSES_LIST;
import static org.owasp.workshop.candies.administration.directories.URIs.REDIRECT_TO_COURSES_PUBLIC_LIST;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("home")
    public String getHome() {
        return "/home";
    }

    @GetMapping
    public String getIndex() {
//        return REDIRECT_TO_LOGIN_USER;
        return REDIRECT_TO_COURSES_PUBLIC_LIST;
    }

}

