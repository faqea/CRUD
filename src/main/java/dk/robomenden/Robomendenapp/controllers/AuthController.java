package dk.robomenden.Robomendenapp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    //Страница с авторизацией
    //Page with authorization
    @GetMapping("/login")
    public String auth() {
        return "auth/login";
    }


}
