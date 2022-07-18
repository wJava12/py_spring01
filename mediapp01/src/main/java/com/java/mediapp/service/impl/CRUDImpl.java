package com.java.mediapp.service.impl;

import com.java.mediapp.model.Medico;
import com.java.mediapp.repo.IGenericRepo;
import com.java.mediapp.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T,V> implements ICRUD<T,V>  {

    protected abstract IGenericRepo<T,V> getRepo();

    @Override
    public T registrar(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T modificar(T t)throws Exception {
        return getRepo().save(t);
    }

    @Override
    public List<T> listar()throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T listarPorId(V id)throws Exception {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public void eliminar(V id)throws Exception {
        getRepo().deleteById(id);
    }
}
