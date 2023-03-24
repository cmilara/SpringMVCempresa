package com.example;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.Correo;
import com.example.entities.Departamento;
import com.example.entities.Empleado;
import com.example.entities.Telefono;
import com.example.entities.Empleado.Genero;
import com.example.services.CorreoService;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;
import com.example.services.TelefonoService;

@SpringBootApplication
public class SpringMvCempresaApplication implements CommandLineRunner {

	@Autowired
	private DepartamentoService departamentoService;

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private TelefonoService telefonoService;

	@Autowired
	private CorreoService correoService;

	public static void main(String[] args) {
		SpringApplication.run(SpringMvCempresaApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		departamentoService.save(
				Departamento.builder()
						.nombre("Informática")
						.build());
		departamentoService.save(
				Departamento.builder()
						.nombre("Calidad")
						.build());

		empleadoService.save(
				Empleado.builder()
						.id(1)
						.nombre("Luis")
						.apellidos("Lopez Garcia")
						.fechaAlta(LocalDate.of(2020, Month.JANUARY, 1))
						.genero(Genero.HOMBRE)
						.departamento(departamentoService.findById(1))
						.build());

		empleadoService.save(
				Empleado.builder()
						.id(2)
						.nombre("Cristina")
						.apellidos("Martinez Garcia")
						.fechaAlta(LocalDate.of(2018, Month.JULY, 12))
						.genero(Genero.MUJER)
						.departamento(departamentoService.findById(2))
						.build());

		telefonoService.save(
				Telefono.builder()
						.id(1)
						.numero("611221122")
						.empleado(empleadoService.findById(1))
						.build());

		telefonoService.save(
				Telefono.builder()
						.id(2)
						.numero("678987123")
						.empleado(empleadoService.findById(1))
						.build());


		correoService.save(
			Correo.builder()
					.id(1)
					.email("cmg@gmail.com")
					.empleado(empleadoService.findById(1))
					.build()
					);
	}

}
