package com.java.mediapp.service.impl;

import com.java.mediapp.model.Especialidad;

import com.java.mediapp.repo.IEspecialidadRepo;
import com.java.mediapp.repo.IGenericRepo;

import com.java.mediapp.service.IEspecialidadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IEspecialidadServiceImpl extends CRUDImpl<Especialidad,Integer> implements IEspecialidadService {

    @Autowired
    IEspecialidadRepo repo;

    @Override
    protected IGenericRepo<Especialidad, Integer> getRepo() {
        return repo;
    }
}
