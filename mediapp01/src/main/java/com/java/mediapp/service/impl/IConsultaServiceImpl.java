package com.java.mediapp.service.impl;

import com.java.mediapp.dto.ConsultaListaExamenDTO;
import com.java.mediapp.model.Consulta;
import com.java.mediapp.repo.IConsultaExamenRepo;
import com.java.mediapp.repo.IGenericRepo;
import com.java.mediapp.repo.IConsultaRepo;
import com.java.mediapp.service.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IConsultaServiceImpl extends CRUDImpl<Consulta,Integer> implements IConsultaService {

    @Autowired
    private IConsultaRepo repo;

    @Autowired
    private IConsultaExamenRepo ceRepo;

    @Override
    protected IGenericRepo<Consulta, Integer> getRepo() {
        return repo;
    }

    @Transactional
    @Override
    public Consulta registrarTransaccional(ConsultaListaExamenDTO dto) throws Exception {
        dto.getConsulta().getDetalleConsulta().forEach(det->det.setConsulta(dto.getConsulta()));
        repo.save(dto.getConsulta());

        dto.getLstExamen().forEach(examen -> ceRepo.registrar(dto.getConsulta().getIdConsulta(),examen.getIdExamen()));


        return dto.getConsulta();
    }
}
