package com.java.mediapp.controller;


import com.java.mediapp.model.Paciente;
import com.java.mediapp.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService service;

    @GetMapping
    public List<Paciente> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Paciente listarPorId(@PathVariable("id") Integer id){
        return service.listarPorId(id);
    }


    @PostMapping
    public Paciente registrar(Paciente p){
        return service.registrar(p);
    }

    @PutMapping
    public Paciente modificar(Paciente p){
        return service.modificar(p);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){
        service.eliminar(id);
    }


}
