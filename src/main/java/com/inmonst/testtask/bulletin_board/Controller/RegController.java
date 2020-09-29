package com.inmonst.testtask.bulletin_board.Controller;

import com.inmonst.testtask.bulletin_board.Model.User;
import com.inmonst.testtask.bulletin_board.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class RegController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = {"/signUp"})
    public ModelAndView getReg(ModelAndView model, @ModelAttribute User user) {
        model.setViewName("signUp");
        model.addObject("msg", "");
        model.addObject("user", user);
        return model;
    }

    @PostMapping("/regfinished")
    public ModelAndView addUser(@ModelAttribute User user) {
        ModelAndView model = new ModelAndView();
        if (isEmptyFields(user)) {
            model.setViewName("signUp");
            model.addObject("msg", "Поля не заповнені");
            model.addObject("user", user);
            return model;
        }

        if (isUniqueEmail(user.getEmail()) || userRepository.findById(user.getId()).get().getEmail().equals(user.getEmail())) {
            userRepository.save(user);
            model.setViewName("login");
            model.addObject("msg", "Користувач зареєстрований");
            model.addObject("user", user);
        } else {
            model.setViewName("signUp");
            user.setEmail("");
            model.addObject("msg", "Користувач з таким логіном уже існує");
            model.addObject("user", user);
        }
        return model;
    }

    private boolean isUniqueEmail(String em) {
        List<User> userSet = (List<User>) userRepository.findAll();
        boolean welcome = userSet
                .stream()
                .noneMatch(a ->
                        (a.getEmail().equals(em))
                );
        return welcome;
    }

    private boolean isEmptyFields(User user) {
        return user.getFirst_name().equals("") || user.getLast_name().equals("") || user.getPwd().length() < 3
                || user.getEmail().equals("");
    }

}
