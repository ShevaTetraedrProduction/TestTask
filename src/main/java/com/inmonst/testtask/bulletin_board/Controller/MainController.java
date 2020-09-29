package com.inmonst.testtask.bulletin_board.Controller;

import com.inmonst.testtask.bulletin_board.Model.Advert;
import com.inmonst.testtask.bulletin_board.Model.Client;
import com.inmonst.testtask.bulletin_board.Model.User;
import com.inmonst.testtask.bulletin_board.Repositories.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.IntStream;

@RestController
public class MainController {

    private Page<Advert> pageAdvert;

    @Autowired
    private AdvertRepository advertRepository;

    @PostMapping("/delete/{id}/page/{page}")
    public ModelAndView delete(@PathVariable("id") long id,
                               @PathVariable(value = "page", required = false) Integer page) {
        /* TODO: check if the current user can actually delete id */
        ModelAndView model = new ModelAndView();
        advertRepository.deleteById(id);
        model.setViewName("redirect:/main?page=" + page);
        return model;
    }

    @GetMapping("/main")
    public ModelAndView mainWindow(ModelAndView model
            , @RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
        pageAdvert = advertRepository.
                findAll(PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "id")));
        model.addObject("id", Client.id);
        model.addObject("adverts", pageAdvert);
        model.addObject("numbers", IntStream.range(0, pageAdvert.getTotalPages()).toArray());
        model.setViewName("main");
        return model;
    }

    @GetMapping("/signOut")
    public ModelAndView sighOut(ModelAndView model) {
        model.setViewName("login");
        Client.id = -1;
        model.addObject("msg", "");
        model.addObject("user", new User());
        return model;
    }
}