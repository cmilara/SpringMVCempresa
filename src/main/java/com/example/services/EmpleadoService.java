package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entities.Empleado;

@Service
public interface EmpleadoService{

public List <Empleado> findAll();
public Empleado findById (int idEmpleado);
public void save (Empleado empleado);
public void deleteById (int idempleado);

}

