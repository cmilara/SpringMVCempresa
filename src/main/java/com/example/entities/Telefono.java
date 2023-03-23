package com.example.entities;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Telefono  implements Serializable{

   
        private static final long serialVersionUID = 1L;
        
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private int id;
        private String email;

        @ManyToOne(fetch = FetchType.LAZY,
        cascade = CascadeType.REMOVE)
        private Empleado empleado;
        
        }

