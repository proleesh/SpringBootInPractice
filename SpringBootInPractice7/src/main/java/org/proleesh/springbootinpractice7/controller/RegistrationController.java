package org.proleesh.springbootinpractice7.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.proleesh.springbootinpractice7.dto.UserDto;
import org.proleesh.springbootinpractice7.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class RegistrationController {
    private final UserService userService;

    @GetMapping("/adduser")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "add-user";
    }

    @PostMapping("/adduser")
    public String register(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result
    ){
        if(result.hasErrors()){
            return "add-user";
        }
        userService.createUser(userDto);
        return "redirect: adduser ? success";
    }
}
