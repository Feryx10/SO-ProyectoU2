/*
 * Copyright (C) 2020 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package proyectosou2;

import java.util.ArrayList;

class Region {
    
    String nombre;
    private int contadoctor = 0;
    private int contalab = 0;
    private ArrayList<Fuente> doctores = new ArrayList<>();
    private ArrayList<Fuente> laboratorios = new ArrayList<>();
    private ArrayList<Caso> casos = new ArrayList<>();

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
    
    public void agregarCaso(Caso caso)
    {
        casos.add(caso);
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
    
    public Caso casosNuevos()
    {
        for (int i = 0; i < casos.size(); i++) {
            if(!casos.get(i).getProcesado())
            {
                return casos.get(i);
            }
        }
        return null;
    }
    
    public int casosDisponibles()
    {
        int casosdisp=0;
        for (int i = 0; i < casos.size(); i++) {
            if(!casos.get(i).getProcesado())
            {
                casosdisp++;
            }
        }
        return casosdisp;
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
    
    public int cantidadCasos()
    {
        return casos.size();
    }
            
    public Caso getCaso(int i)
    {
        return casos.get(i);
    }
    
    public Caso getCasoID(int i)
    {
        for (int j = 0; j < casos.size(); j++) {
            if(casos.get(j).getId()==i)
            {
                return casos.get(j);
            }
        }
        System.out.println("xd");
        return null;
    }
    
    public void removeCasoID(int i)
    {
        for (int j = 0; j < casos.size()-1; j++) {
            if(casos.get(j).getId()==i)
            {
                casos.remove(j);
                return;
            }
        }
        System.out.println("xd2");
    }
    
    public int sizeDoctores()
    {
        return doctores.size();
    }
    
    public int sizeLaboratorios()
    {
        return doctores.size();
    }
    
    public int sizeCasos()
    {
        return casos.size();
    }
}
