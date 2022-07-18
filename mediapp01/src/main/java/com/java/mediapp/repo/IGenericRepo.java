package com.java.mediapp.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericRepo <T,V> extends JpaRepository<T,V> {
}
