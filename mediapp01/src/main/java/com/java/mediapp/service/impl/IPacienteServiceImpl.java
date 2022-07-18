package com.java.mediapp.service.impl;

import com.java.mediapp.model.Paciente;
import com.java.mediapp.repo.IGenericRepo;
import com.java.mediapp.repo.IPacienteRepo;
import com.java.mediapp.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IPacienteServiceImpl extends CRUDImpl<Paciente,Integer> implements IPacienteService {

    @Autowired
    IPacienteRepo repo;

    @Override
    protected IGenericRepo<Paciente, Integer> getRepo() {
        return repo;
    }
}
