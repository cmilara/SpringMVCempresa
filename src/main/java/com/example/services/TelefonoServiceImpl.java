package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.TelefonoDao;
import com.example.entities.Empleado;
import com.example.entities.Telefono;

@Service
public class TelefonoServiceImpl implements TelefonoService{

@Autowired
private TelefonoDao telefonoDao;


    @Override
    public List<Telefono> findAll() {
       return telefonoDao.findAll();
    }

    @Override
    public Telefono findById(int idTelefono) {
        return telefonoDao.findById(idTelefono).get();

    }

    @Override
    public void save(Telefono telefono) {
        telefonoDao.save(telefono);

    }

    @Override
    public void deleteById(int idTelefono) {
        telefonoDao.deleteById(idTelefono);

    }

    @Override
    public long deleteByEmpleado(Empleado empleado) {
        return telefonoDao.deleteByEmpleado(empleado);
    }

    @Override
    public List<Telefono> findByEmpleado(Empleado empleado) {
        return telefonoDao.findByEmpleado(empleado);
    }
}