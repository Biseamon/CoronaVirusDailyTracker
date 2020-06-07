package com.example.coronavirusglobaldailytracker.controllers;

import com.example.coronavirusglobaldailytracker.models.DataCounts;
import com.example.coronavirusglobaldailytracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

   @GetMapping("/")
    public String home(Model model){
       List <DataCounts> dataCounts = coronaVirusDataService.getDataCounts();
       int totalCases = dataCounts.stream().mapToInt(stat -> stat.getTotalCases()).sum();
       int newTotalCases = dataCounts.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
       model.addAttribute("dataCounts", dataCounts);
       model.addAttribute("totalCases", totalCases);
       model.addAttribute("newTotalCases", newTotalCases);

        return "home";
    }

}
