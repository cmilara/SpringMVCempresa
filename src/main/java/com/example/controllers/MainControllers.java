package com.example.controllers;

import java.lang.ProcessBuilder.Redirect;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.entities.Correo;
import com.example.entities.Departamento;
import com.example.entities.Empleado;
import com.example.entities.Telefono;
import com.example.services.CorreoService;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;
import com.example.services.TelefonoService;

@Controller
@RequestMapping("/")


public class MainControllers {

    private static final Logger LOG = Logger.getLogger("MainController");

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private DepartamentoService departamentoService;
    @Autowired
    private TelefonoService telefonoService;

    @Autowired
    private CorreoService correoService;

    /* El metodo siguiente devuelve un listado de estudiantes */
    @GetMapping("/listar")
    public ModelAndView listar() {
        List<Empleado> empleados = empleadoService.findAll();
        ModelAndView mav = new ModelAndView("views/listarEmpleados");
        mav.addObject("empleados", empleados);

        return mav;
    }

    /**
     * Muestra el formulario de alta de estudiantes
     */
    @GetMapping("/frmAltaEmpleado")
    public String formularioAltaEmpleado(Model model) {
        List<Departamento> departamento = departamentoService.findAll();
        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);
        model.addAttribute("departamento", departamento);

        return "views/formularioAltaEmpleado";
    }

    /**
     * Metodo que recibe los datos procedentes de los controles del formulario
     */
    @PostMapping("/altaModificacionEmpleado")
    public String altaEmpleado(@ModelAttribute Empleado empleado,
            @RequestParam("numerosTelefonos") String telefonosRecibidos,
            @RequestParam("listadoCorreos") String correosRecibidos) {
        LOG.info("telefonos recibidos: " + telefonosRecibidos);
        LOG.info("correos recibidos: " + correosRecibidos);

        empleadoService.save(empleado);
        List<String> listadoNumerosTelefonos = null;
        if (telefonosRecibidos != null) {
            String[] arrayTelefonos = telefonosRecibidos.split(";");
            listadoNumerosTelefonos = Arrays.asList(arrayTelefonos);
        }
        if (listadoNumerosTelefonos != null) {
            telefonoService.deleteByEmpleado(empleado);
            listadoNumerosTelefonos.stream().forEach(n -> {
                Telefono telefonoObject = Telefono
                        .builder()
                        .numero(n)
                        .empleado(empleado)
                        .build();
                telefonoService.save(telefonoObject);
            });
        }
        // correos
        List<String> listadoCorreos = null;
        if (correosRecibidos != null) {
            String[] arrayCorreos = correosRecibidos.split(";");

            
            listadoCorreos = Arrays.asList(arrayCorreos);
        }
        if (listadoCorreos != null) {
            correoService.deleteByEmpleado(empleado);
            listadoCorreos.stream().forEach(c -> {
                Correo correoObject = Correo
                        .builder()
                        .email(c)
                        .empleado(empleado)
                        .build();
                correoService.save(correoObject);
            });

        }
        return "redirect:/listar";
    }
    //Método que borra empleados
    @GetMapping("/borrar/{id}")
    public String borrarEmpleado(@PathVariable (name = "id") int idEmpleado){
    empleadoService.deleteById(idEmpleado);
        return "redirect:/listar";
    }


    // Metodo que te da los detalles del empleado
    @GetMapping("/detalles/{id}")
    public String empleadoDetails(@PathVariable(name = "id") int id, Model model) {
        Empleado empleado = empleadoService.findById(id);

        List<Telefono> telefonos = telefonoService.findByEmpleado(empleado);
        List<String> numerosTelefono = telefonos.stream()
                .map(t -> t.getNumero())
                .toList();
        model.addAttribute("telefonos", numerosTelefono);
        model.addAttribute("empleado", empleado);

        List<Correo> correos = correoService.findByEmpleado(empleado);
        List<String> correosCorreos = correos.stream()
                .map(c -> c.getEmail())
                .toList();
        model.addAttribute("correos", correosCorreos);
        model.addAttribute("empleado", empleado);
        return "views/detalles";
    }
}
