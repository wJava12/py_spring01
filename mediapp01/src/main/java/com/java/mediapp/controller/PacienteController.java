package com.java.mediapp.controller;


import com.java.mediapp.exception.ModelNotFoundException;
import com.java.mediapp.model.Paciente;
import com.java.mediapp.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService service;

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() throws Exception {
        List<Paciente> lista=service.listar();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> listarPorId(@PathVariable("id") Integer id) throws Exception {
        Paciente obj= service.listarPorId(id);

        if (obj==null){
            throw new ModelNotFoundException("ID NO ENCONTRADO "+ id);
        }
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @GetMapping("hateoas/{id}")
    public EntityModel<Paciente> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
        Paciente obj= service.listarPorId(id);

        if (obj==null){
            throw new ModelNotFoundException("ID NO ENCONTRADO "+ id);
        }
        EntityModel<Paciente> recurso=EntityModel.of(obj);
        WebMvcLinkBuilder linkTo= linkTo(methodOn(this.getClass()).listarPorId(id));
        recurso.add(linkTo.withRel("paciente-recurso"));

        return recurso;
    }


    /*@PostMapping
    public ResponseEntity<Paciente> registrar(@Valid @RequestBody Paciente p){
        Paciente obj= service.registrar(p);
        return new ResponseEntity<>(obj,HttpStatus.CREATED);
    }*/

    @PostMapping
    public ResponseEntity<Paciente> registrar(@Valid @RequestBody Paciente p) throws Exception {
        Paciente obj= service.registrar(p);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPaciente()).toUri();
        return ResponseEntity.created(location).build();
    }




    @PutMapping
    public ResponseEntity<Paciente> modificar(@Valid @RequestBody Paciente p) throws Exception {
        Paciente obj= service.modificar(p);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        Paciente obj= service.listarPorId(id);
        if (obj==null){
            throw new ModelNotFoundException("ID NO ENCONTRADO "+ id);
        }
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
