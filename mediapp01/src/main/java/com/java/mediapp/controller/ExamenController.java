package com.java.mediapp.controller;


import com.java.mediapp.exception.ModelNotFoundException;
import com.java.mediapp.model.Examen;
import com.java.mediapp.service.IExamenService;
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
@RequestMapping("/examenes")
public class ExamenController {

    @Autowired
    private IExamenService service;

    @GetMapping
    public ResponseEntity<List<Examen>> listar() throws Exception {
        List<Examen> lista=service.listar();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Examen> listarPorId(@PathVariable("id") Integer id) throws Exception {
        Examen obj= service.listarPorId(id);

        if (obj==null){
            throw new ModelNotFoundException("ID NO ENCONTRADO "+ id);
        }
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @GetMapping("hateoas/{id}")
    public EntityModel<Examen> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
        Examen obj= service.listarPorId(id);

        if (obj==null){
            throw new ModelNotFoundException("ID NO ENCONTRADO "+ id);
        }
        EntityModel<Examen> recurso=EntityModel.of(obj);
        WebMvcLinkBuilder linkTo= linkTo(methodOn(this.getClass()).listarPorId(id));
        recurso.add(linkTo.withRel("examen-recurso"));

        return recurso;
    }


    /*@PostMapping
    public ResponseEntity<Examen> registrar(@Valid @RequestBody Examen p){
        Examen obj= service.registrar(p);
        return new ResponseEntity<>(obj,HttpStatus.CREATED);
    }*/

    @PostMapping
    public ResponseEntity<Examen> registrar(@Valid @RequestBody Examen p) throws Exception {
        Examen obj= service.registrar(p);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdExamen()).toUri();
        return ResponseEntity.created(location).build();
    }




    @PutMapping
    public ResponseEntity<Examen> modificar(@Valid @RequestBody Examen p) throws Exception {
        Examen obj= service.modificar(p);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        Examen obj= service.listarPorId(id);
        if (obj==null){
            throw new ModelNotFoundException("ID NO ENCONTRADO "+ id);
        }
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
