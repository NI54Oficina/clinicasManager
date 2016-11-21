package ar.com.clinicasmanager.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/home/**")
@Controller
public class HomeController {

    @RequestMapping
    public String index() {
        return "index";
    }
}
