package com.java.mediapp.service.impl;

import com.java.mediapp.model.Medico;

import com.java.mediapp.repo.IGenericRepo;
import com.java.mediapp.repo.IMedicoRepo;
import com.java.mediapp.service.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class IMedicoServiceImpl extends CRUDImpl<Medico,Integer> implements IMedicoService {

    @Autowired
    IMedicoRepo repo;

    @Override
    protected IGenericRepo<Medico, Integer> getRepo() {
        return repo;
    }
}
