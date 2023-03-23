package com.example.entities;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name= "departamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Departamento implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

// creo la realacion de uno a muchos y para q sea bidireccional decimos quien posee la relacion de clave externa (mappedBy)
    //maneja la relacion el campo facultad de la parte de muchos, de los estudiantes
    @OneToMany(fetch = FetchType.LAZY,
    cascade = CascadeType.PERSIST,mappedBy = "departamento")
    private List<Empleado> empleados;
    
}
    


