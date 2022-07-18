package com.java.mediapp.controller;


import com.java.mediapp.exception.ModelNotFoundException;
import com.java.mediapp.model.Medico;
import com.java.mediapp.service.IMedicoService;
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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private IMedicoService service;

    @GetMapping
    public ResponseEntity<List<Medico>> listar() throws Exception {
        List<Medico> lista=service.listar();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> listarPorId(@PathVariable("id") Integer id) throws Exception {
        Medico obj= service.listarPorId(id);

        if (obj==null){
            throw new ModelNotFoundException("ID NO ENCONTRADO "+ id);
        }
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @GetMapping("hateoas/{id}")
    public EntityModel<Medico> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
        Medico obj= service.listarPorId(id);

        if (obj==null){
            throw new ModelNotFoundException("ID NO ENCONTRADO "+ id);
        }
        EntityModel<Medico> recurso=EntityModel.of(obj);
        WebMvcLinkBuilder linkTo= linkTo(methodOn(this.getClass()).listarPorId(id));
        recurso.add(linkTo.withRel("medico-recurso"));

        return recurso;
    }


    /*@PostMapping
    public ResponseEntity<Medico> registrar(@Valid @RequestBody Medico p){
        Medico obj= service.registrar(p);
        return new ResponseEntity<>(obj,HttpStatus.CREATED);
    }*/

    @PostMapping
    public ResponseEntity<Medico> registrar(@Valid @RequestBody Medico p) throws Exception {
        Medico obj= service.registrar(p);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMedico()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Medico> modificar(@Valid @RequestBody Medico p) throws Exception {
        Medico obj= service.modificar(p);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        Medico obj= service.listarPorId(id);
        if (obj==null){
            throw new ModelNotFoundException("ID NO ENCONTRADO "+ id);
        }
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
