package com.example.mapeamento.mapeamento.controller;

import com.example.mapeamento.mapeamento.model.entity.Consulta;
import com.example.mapeamento.mapeamento.model.repository.ConsultaRepository;
import com.example.mapeamento.mapeamento.model.repository.MedicoRepository;
import com.example.mapeamento.mapeamento.model.repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("consultas")
public class ConsultaController {
    @Autowired
    ConsultaRepository repository;
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("consultas", repository.consultas());
        return new ModelAndView("/consulta/list", model);
    }
    @GetMapping("/form")
    public String form(ModelMap model, Consulta consulta){
        listas(model);
        return "/consulta/form";
    }

    @GetMapping("/listamedico/{id}")
    public ModelAndView listamedico(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("consultas", repository.consultas_medico(id));
        return new ModelAndView("/consulta/list", model);
    }
    @GetMapping("/listapaciente/{id}")
    public ModelAndView listapaciente(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("consultas", repository.consultas_paciente(id));
        return new ModelAndView("consulta/list");
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid Consulta consulta, BindingResult result,ModelMap model){
        if (result.hasErrors()){
            listas(model);
            return new ModelAndView("/consulta/form");
        }
        repository.save(consulta);
        return new ModelAndView("redirect:/consultas/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/consultas/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("consulta", repository.consulta(id));
        listas(model);
        return new ModelAndView("/consulta/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Consulta consulta,BindingResult result, ModelMap model) {
        if (result.hasErrors()){
            listas(model);
            return new ModelAndView("/consulta/form");
        }
        repository.update(consulta);
        return new ModelAndView("redirect:/consultas/list");
    }
    private void listas(ModelMap model){
        model.addAttribute("pacientes", pacienteRepository.pacientes());
        model.addAttribute("medicos", medicoRepository.medicos());
    }

}
