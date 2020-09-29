package com.inmonst.testtask.bulletin_board.Controller;

import com.inmonst.testtask.bulletin_board.Model.Client;
import com.inmonst.testtask.bulletin_board.Model.User;
import com.inmonst.testtask.bulletin_board.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PersonalCabController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/personalCab")
    public ModelAndView personalCab(ModelAndView model) {
        model.addObject("client", userRepository.findById(Client.id).get());
        model.setViewName("personalCabinet");
        return model;
    }

    @GetMapping(value = {"/editProfile/{id}"})
    public ModelAndView edit(@PathVariable("id") long id){
        User user = userRepository.findById(id).get();
        ModelAndView model = new ModelAndView();
        model.setViewName("signUp");
        model.addObject("msg", "Змінити параметри");
        model.addObject("user", user);
        return model;
    }

    @GetMapping(value = {"/getAddAdvert"})
    public ModelAndView getAddAdvert(ModelAndView modelAndView) {
        modelAndView.setViewName("addAdvert");
        String avtor = userRepository.findById(Client.id).get().getLast_name();
        modelAndView.addObject("avtor", avtor);
        return modelAndView;
    }
}