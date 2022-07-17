package com.java.mediapp.service.impl;

import com.java.mediapp.model.Paciente;
import com.java.mediapp.repo.IPacienteRepo;
import com.java.mediapp.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class IPacienteServiceImpl implements IPacienteService {

    @Autowired
    IPacienteRepo repo;


    @Override
    public Paciente registrar(Paciente p) {
        return repo.save(p);
    }

    @Override
    public Paciente modificar(Paciente p) {
        return repo.save(p);
    }

    @Override
    public List<Paciente> listar() {
        return repo.findAll();
    }

    @Override
    public Paciente listarPorId(Integer id) {
        Optional<Paciente> objPaciente=repo.findById(id);

        return (objPaciente.isPresent())?objPaciente.get():new Paciente();
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
