package com.example.mapeamento.mapeamento.controller;

import com.example.mapeamento.mapeamento.model.entity.Medico;
import com.example.mapeamento.mapeamento.model.entity.Paciente;
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
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    PacienteRepository repository;
    @GetMapping("/form")
    public String form(Paciente paciente){
        return "/paciente/form";
    }
    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("pacientes", repository.pacientes());
        return new ModelAndView("/paciente/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(Paciente paciente){
        repository.save(paciente);
        return new ModelAndView("redirect:/pacientes/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/pacientes/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("paciente", repository.paciente(id));
        return new ModelAndView("/paciente/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Paciente paciente) {
        repository.update(paciente);
        return new ModelAndView("redirect:/pacientes/list");
    }
}
