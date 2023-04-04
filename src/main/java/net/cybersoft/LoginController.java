package net.cybersoft;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    //Eger kullanici http://localhost:8080/login
    // adresine giderse login.html sayfasini geri dondur.
    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
