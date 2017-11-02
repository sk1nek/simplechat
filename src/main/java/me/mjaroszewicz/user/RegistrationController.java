package me.mjaroszewicz.user;

import me.mjaroszewicz.auth.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
public class RegistrationController extends WebMvcConfigurerAdapter {

    //for logging purposes
    private Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/results").setViewName("results");
    }

    @RequestMapping("/register")
    public String getRegistrationPage(RegistrationForm registrationForm) {

//        log.info(((Authentication) SecurityContextHolder.getContext().getAuthentication()).getName());

        return "register";
    }

    @PostMapping("/register")
    public String registerUserAccount(@Valid RegistrationForm registrationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.registerUser(registrationForm);
        securityService.autologin(registrationForm.getName(), registrationForm.getPassword());

        return "redirect:/";
    }



    @GetMapping("/login")
    public String getLoginPage(LoginForm loginForm){

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm){
        securityService.autologin(loginForm.getUsername(), loginForm.getPassword());

        return "redirect:/";
    }


}
