package com.gestionale.testGestionale.controller;

import com.gestionale.testGestionale.DTO.LavoratoreDTO;
import com.gestionale.testGestionale.entity.LavoratoreEntity;
import com.gestionale.testGestionale.service.AziendaService;
import com.gestionale.testGestionale.service.LavoratoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LavoratoreControllerThymeleaf {


    @Autowired
    LavoratoreService lavoratoreService;

    @Autowired
    AziendaService aziendaService;


    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listaLavoratori", lavoratoreService.getAllLavoratoriDTO());
        model.addAttribute("listaAziende", aziendaService.getAllAziende());
        return "index";
    }

    @GetMapping("/showNewLavoratoreForm")
    public String showNewLavoratoreForm(Model model) {
        LavoratoreEntity lavoratore = new LavoratoreEntity();
        model.addAttribute("lavoratore", lavoratore);
        return "newLavoratore";
    }

    @PostMapping("/createLavoratore")
    public String createLavoratore(@ModelAttribute("lavoratore") LavoratoreEntity lavoratore) {
        lavoratoreService.createLavoratore(lavoratore);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showUpdateForm(Model model, @PathVariable int id) {
        LavoratoreEntity lavoratore = lavoratoreService.getLavoratoreById(id);
        model.addAttribute("lavoratore", lavoratore);
        return "updateLavoratore";
    }

    @GetMapping("/delete/{id}")
    public String deleteLavoratoreById(Model model, @PathVariable int id) {
        lavoratoreService.removeLavoratore(id);
        return "redirect:/";
    }

    @GetMapping("/getLavoratoriByAzienda/{idAzienda}")
    @ResponseBody
    public List<LavoratoreDTO> getLavoratoriByAzienda(@PathVariable int idAzienda) {
        return lavoratoreService.getLavoratoriByAzienda(idAzienda);
    }

//    @GetMapping("/search")
//    public String searchFiscalCodeLavoratore(Model model, @RequestParam String codFisc) {
//        model.addAttribute("lavoratore", service.searchByFiscalCode(codFisc));
//        return "lavoratore-details";
//    }

}
