package com.java.mediapp.service;

import com.java.mediapp.dto.ConsultaListaExamenDTO;
import com.java.mediapp.model.Consulta;


public interface IConsultaService extends ICRUD<Consulta,Integer>{

    Consulta registrarTransaccional(ConsultaListaExamenDTO dto) throws Exception;

}
