package com.example.conferences_sem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private BarbService service;

    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Barbershop> listClients = service.listAll(keyword);
        model.addAttribute("listClients", listClients);
        model.addAttribute("keyword", keyword);
        Integer average = service.averageClients();
        if (average == -1) {
            model.addAttribute("average", 0);
        } else {
            model.addAttribute("average", average);
        }
        return "index";
    }

    @RequestMapping("/filterByDate")
    public String filterByDate(Model model, @Param("date") java.sql.Date date) {
        List<Barbershop> listClients = service.filterByDate(date);
        model.addAttribute("listClients", listClients);
        model.addAttribute("date", date);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewClientForm(Model model) {
        Barbershop client = new Barbershop();
        model.addAttribute("client", client);
        return "new_client";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveClient(@ModelAttribute("play") Barbershop barb) {
        service.save(barb);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditClientForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_client");
        Barbershop client = service.get(id);
        mav.addObject("client", client);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteClient(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/chart")
    public String getChart(Model model) {
        String chart = null;
        try {
            chart = service.generateBarChart();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("chart", chart);

        return "chart";
    }
}


