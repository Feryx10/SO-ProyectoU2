/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosou2;

import java.util.ArrayList;

/**
 *
 * @author Luciano
 */
class Region {
    
    String nombre;
    int contadoctor = 0;
    int contalab = 0;
    ArrayList<Fuente> doctores = new ArrayList<Fuente>();
    ArrayList<Fuente> laboratorios = new ArrayList<Fuente>();
    ArrayList<Caso> casos = new ArrayList<Caso>();

    public Region(String nombre) {
        this.nombre = nombre;
        
    }

    public void agregarDoctor(Fuente doctor)
    {
        doctor.setId(contadoctor++);
        doctores.add(doctor);
    }
    
    public void agregarLaboratorio(Fuente laboratorio)
    {
        laboratorio.setId(contalab++);
        laboratorios.add(laboratorio);
    }
    
    public void eliminarDoctor(int i)
    {
        for (int j = 0; j < doctores.size(); j++) {
            if(doctores.get(j).getId()==i)
            {
                doctores.remove(j);
            }
        }
    }
    
    public void eliminarLaboratorio(int i)
    {
        for (int j = 0; j < laboratorios.size(); j++) {
            if(laboratorios.get(j).getId()==i)
            {
                laboratorios.remove(j);
            }
        }
    }
    
    public Fuente obtenerDoctor(int i)
    {
        return doctores.get(i);
    }
    
    public Fuente obtenerLaboratorio(int i)
    {
        return laboratorios.get(i);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
            
    
    
}
