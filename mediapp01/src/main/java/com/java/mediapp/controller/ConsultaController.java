package com.java.mediapp.controller;


import com.java.mediapp.dto.ConsultaListaExamenDTO;
import com.java.mediapp.exception.ModelNotFoundException;
import com.java.mediapp.model.Consulta;

import com.java.mediapp.service.IConsultaService;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private IConsultaService service;

    @GetMapping
    public ResponseEntity<List<Consulta>> listar() throws Exception {
        List<Consulta> lista=service.listar();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> listarPorId(@PathVariable("id") Integer id) throws Exception {
        Consulta obj= service.listarPorId(id);

        if (obj==null){
            throw new ModelNotFoundException("ID NO ENCONTRADO "+ id);
        }
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @GetMapping("hateoas/{id}")
    public EntityModel<Consulta> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
        Consulta obj= service.listarPorId(id);

        if (obj==null){
            throw new ModelNotFoundException("ID NO ENCONTRADO "+ id);
        }
        EntityModel<Consulta> recurso=EntityModel.of(obj);
        WebMvcLinkBuilder linkTo= linkTo(methodOn(this.getClass()).listarPorId(id));
        recurso.add(linkTo.withRel("Consulta-recurso"));

        return recurso;
    }

    /*@PostMapping
    public ResponseEntity<Consulta> registrar(@Valid @RequestBody Consulta p){
        Consulta obj= service.registrar(p);
        return new ResponseEntity<>(obj,HttpStatus.CREATED);
    }*/

    @PostMapping
    public ResponseEntity<Consulta> registrar(@Valid @RequestBody ConsultaListaExamenDTO dto) throws Exception {
        Consulta obj= service.registrarTransaccional(dto);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsulta()).toUri();
        return ResponseEntity.created(location).build();
    }


    @PutMapping
    public ResponseEntity<Consulta> modificar(@Valid @RequestBody Consulta p) throws Exception {
        Consulta obj= service.modificar(p);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        Consulta obj= service.listarPorId(id);
        if (obj==null){
            throw new ModelNotFoundException("ID NO ENCONTRADO "+ id);
        }
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
