/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Empleado;
import modelo.Departamento;

import servicio.EmpleadoServiceImpl;
import servicio.DepartamentoServiceImpl;

/**
 *
 * @author karlylvg
 */
public class EmpleadoControl {

    private EmpleadoServiceImpl empleadoServiceImpl = new EmpleadoServiceImpl();
    private DepartamentoServiceImpl departamentoServiceImpl = new DepartamentoServiceImpl();

    public EmpleadoControl() {
        this.empleadoServiceImpl = new EmpleadoServiceImpl();
    }

    public String crear(String[] data) throws Exception {

        try {

            var retorno = "No se puede crear:";
            var nombre = data[0];
            var tituloUniversitario = data[1];
            var horasDiariasTrabajadas = Integer.valueOf(data[2]).intValue();
            var horasMensuales = Integer.valueOf(data[3]).intValue();
            var costoHoras = Double.valueOf(data[4]).doubleValue();
            var numeroActividadesMes = Integer.valueOf(data[5]).intValue();
            var departamento = this.departamentoServiceImpl.DepartamentoCodigo(Integer.valueOf(data[6]));
            var genero = data[7];
            var codigo = Integer.valueOf(data[8]).intValue();

            if (horasDiariasTrabajadas < 0) {
                retorno += " El numero debe ser mayor a 0 ";
            } else {
                if (horasMensuales <= 0 || horasMensuales > 160) {
                    retorno += " El numero de horas es incorrecto ";
                    JOptionPane.showMessageDialog(null, "El numero de horas ser mayor que cero", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (costoHoras < 0) {
                        retorno += " El costo debe ser mayor a 0";
                    } else {
                        if (numeroActividadesMes < 0) {
                            retorno += " Las actividades debe ser mayor a 0";
                        } else {
                            if (departamento == null) {
                                retorno += " Empresa no registrada ";
                            } else {
                                var empleado = new Empleado(nombre, tituloUniversitario,
                                        horasDiariasTrabajadas, horasMensuales, costoHoras,
                                        numeroActividadesMes, departamento, genero, codigo);

                                if (this.codigoExiste(codigo)) {
                                    throw new RuntimeException("C??digo existe");
                                } else {

                                    this.empleadoServiceImpl.crear(empleado);

                                    retorno = "Creado correctamente ";
                                    JOptionPane.showMessageDialog(null, "Empleado Creado Exitosamente");
                                }
                            }

                        }

                    }

                }

            }
            return retorno;
        } catch (NumberFormatException e1) {
            throw new NumberFormatException("Error al convertir el formato");
        }

    }

    public boolean codigoExiste(int codigo) {

        var retorno = this.empleadoServiceImpl.BuscarCodigo(codigo);

        return retorno;
    }

    public String modificar(String[] data) {
        var retorno = "No se puede crear:";
        var nombre = data[0];
        var tituloUniversitario = data[1];
        var horasDiariasTrabajadas = Integer.valueOf(data[2]).intValue();
        var horasMensuales = Integer.valueOf(data[3]).intValue();
        var costoHoras = Double.valueOf(data[4]).doubleValue();
        var numeroActividadesMes = Integer.valueOf(data[5]).intValue();
        var departamento = this.departamentoServiceImpl.DepartamentoCodigo(Integer.valueOf(data[6]));
        var genero = data[7];
        var codigo = Integer.valueOf(data[8]).intValue();

        if (horasDiariasTrabajadas < 0) {
            retorno += " El numero debe ser mayor a 0 ";
        } else {
            if (horasMensuales <= 0 || horasMensuales > 160) {
                retorno += " El numero de horas es incorrecto ";
            } else {
                if (costoHoras < 0) {
                    retorno += " El costo debe ser mayor a 0";
                } else {
                    if (numeroActividadesMes < 0) {
                        retorno += " Las actividades debe ser mayor a 0";
                    } else {
                        if (departamento == null) {
                            retorno += " Empresa no registrada ";
                        } else {
                            var empleado = new Empleado(nombre, tituloUniversitario,
                                    horasDiariasTrabajadas, horasMensuales, costoHoras,
                                    numeroActividadesMes, departamento, genero, codigo);
                            this.empleadoServiceImpl.modificar(empleado, codigo);
                            retorno = "Creado correctamente ";

                        }

                    }

                }

            }

        }

        return retorno;

    }

    public void eliminar(String codigos) {

        var codigo = Integer.valueOf(codigos).intValue();
        this.empleadoServiceImpl.eliminar(codigo);

    }

    public List<Empleado> listar() {
        return this.empleadoServiceImpl.listar();

    }
}
