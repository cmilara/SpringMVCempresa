package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Correo;
import com.example.entities.Empleado;
import com.example.entities.Telefono;

public interface CorreoDao extends JpaRepository <Telefono, Integer> {

    long deleteByEmpleado(Empleado empleado);
    
    //Para mostrar el detalle(Teléfono/s)de empleado
    
    List<Correo>findByEmpleado(Empleado empleado);
        
    }
 