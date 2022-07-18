package com.java.mediapp.service.impl;

import com.java.mediapp.model.Examen;

import com.java.mediapp.repo.IExamenRepo;
import com.java.mediapp.repo.IGenericRepo;

import com.java.mediapp.service.IExamenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IExamenServiceImpl extends CRUDImpl<Examen,Integer> implements IExamenService {

    @Autowired
    IExamenRepo repo;

    @Override
    protected IGenericRepo<Examen, Integer> getRepo() {
        return repo;
    }
}
