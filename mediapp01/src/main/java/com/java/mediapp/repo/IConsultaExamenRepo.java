package com.java.mediapp.repo;


import com.java.mediapp.model.ConsultaExamen;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface IConsultaExamenRepo extends IGenericRepo<ConsultaExamen,Integer>{

    //@Transactional
    @Modifying
    @Query(value = "INSERT INTO consulta_examen(id_consulta,id_examen) values (:idConsulta,:idExamen)",nativeQuery = true)
    Integer registrar(@Param("idConsulta")Integer idConsulta,@Param("idExamen")Integer idExamen);
}
