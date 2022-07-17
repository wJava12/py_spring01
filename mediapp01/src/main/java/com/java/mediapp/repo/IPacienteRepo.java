package com.java.mediapp.repo;

import com.java.mediapp.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepo extends JpaRepository<Paciente,Integer>{

}
