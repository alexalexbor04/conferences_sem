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
    private ConfService service;

    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Conferences> listConf = service.listAll(keyword);
        model.addAttribute("listConf", listConf);
        model.addAttribute("keyword", keyword);
        Integer average = service.averageConfs();
        if (average == -1) {
            model.addAttribute("average", 0);
        } else {
            model.addAttribute("average", average);
        }
        return "index";
    }

    @RequestMapping("/filterByDate")
    public String filterByDate(Model model, @Param("date") java.sql.Date date) {
        List<Conferences> listConf = service.filterByDate(date);
        model.addAttribute("listConf", listConf);
        model.addAttribute("date", date);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewConfForm(Model model) {
        Conferences conf = new Conferences();
        model.addAttribute("conf", conf);
        return "new_conf";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveConf(@ModelAttribute("conf") Conferences conf) {
        service.save(conf);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditConfForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_conf");
        Conferences conf = service.get(id);
        mav.addObject("conf", conf);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteConf(@PathVariable(name = "id") Long id) {
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


