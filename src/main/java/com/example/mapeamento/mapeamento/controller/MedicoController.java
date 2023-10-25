package com.example.mapeamento.mapeamento.controller;

import com.example.mapeamento.mapeamento.model.entity.Medico;
import com.example.mapeamento.mapeamento.model.entity.Paciente;
import com.example.mapeamento.mapeamento.model.repository.MedicoRepository;
import com.example.mapeamento.mapeamento.model.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    MedicoRepository repository;

    @GetMapping("/form")
    public String form(Medico medico){
        return "/medico/form";
    }
    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("medicos", repository.medicos());
        return new ModelAndView("/medico/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(Medico medico){
        repository.save(medico);
        return new ModelAndView("redirect:/medicos/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/medicos/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("medico", repository.medico(id));
        return new ModelAndView("/medico/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Medico medico) {
        repository.update(medico);
        return new ModelAndView("redirect:/medicos/list");
    }
}
