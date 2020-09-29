package com.inmonst.testtask.bulletin_board.Controller;

import com.inmonst.testtask.bulletin_board.Model.Advert;
import com.inmonst.testtask.bulletin_board.Model.Client;
import com.inmonst.testtask.bulletin_board.Model.User;
import com.inmonst.testtask.bulletin_board.Repositories.AdvertRepository;
import com.inmonst.testtask.bulletin_board.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AddAdvert {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdvertRepository advertRepository;

    @PostMapping(value = {"/addAdvert"})
    public ModelAndView addAdvert(@ModelAttribute Advert advert) {
        Advert advert1 = new Advert(advert.getTitle(), advert.getText());
        advertRepository.save(advert1);
        User user = userRepository.findById(Client.id).get();
        user.getAdverts().add(advert1);
        userRepository.save(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("client", userRepository.findById(Client.id).get());
        modelAndView.setViewName("personalCabinet");
        return modelAndView;
    }
}
