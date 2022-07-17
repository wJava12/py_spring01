package com.java.mediapp.service;

import com.java.mediapp.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;



public interface IPacienteService{
    Paciente registrar(Paciente p);
    Paciente modificar(Paciente p);
    List<Paciente> listar();
    Paciente listarPorId(Integer id);
    void eliminar(Integer id);
}
