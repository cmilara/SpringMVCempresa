package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Correo;
import com.example.entities.Empleado;

public interface CorreoDao extends JpaRepository<Correo, Integer> {

    long deleteByEmpleado(Empleado empleado);

    // Para mostrar el detalle(Teléfono/s)de empleado
    List<Correo> findByEmpleado(Empleado empleado);

}
