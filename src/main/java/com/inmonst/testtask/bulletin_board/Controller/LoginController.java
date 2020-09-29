package com.inmonst.testtask.bulletin_board.Controller;

import com.inmonst.testtask.bulletin_board.Model.Client;
import com.inmonst.testtask.bulletin_board.Model.User;
import com.inmonst.testtask.bulletin_board.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = {"/", "index"})
    public ModelAndView getLogin(ModelAndView model) {
        model.setViewName("login");
        model.addObject("user", new User());
        model.addObject("msg", "");
        return model;
    }

    @PostMapping("main")
    public ModelAndView signIn(@ModelAttribute User user) {
        System.out.println("Логін --> " + user.getEmail());
        System.out.println("Пароль --> " + user.getPwd());
        ModelAndView model = new ModelAndView();
        if (isUnique(user.getEmail(), user.getPwd())) {
            Client.id = findId(user.getEmail(), user.getPwd());
            model.setViewName("redirect:/main?page=1");
        } else {
            model.setViewName("login");
            model.addObject("msg", "Неправильний логін чи пароль");
            model.addObject("user", new User());
        }
        return model;
    }


    public boolean isUnique(String email, String pwd) {
        List<User> userList = (List<User>) userRepository.findAll();
        boolean welcome = userList
                .stream()
                .anyMatch(a ->
                        (a.getEmail().equals(email)
                                && a.getPwd().equals(pwd))
                );
        return welcome;
    }

    private Long findId(String email, String pwd) {
        List<User> userList = (List<User>) userRepository.findAll();
        long id = userList
                .stream()
                .filter(a ->
                        (a.getEmail().equals(email)
                                && a.getPwd().equals(pwd))
                )
                .findAny()
                .get().getId();
        return id;
    }
}
