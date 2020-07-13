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

import java.util.Calendar;

public class Caso {
    private String nombrePaciente;
    private Fuente fuente;
    private Region region;
    private final int id = this.hashCode();
    private String hora;
    private String estado;

    public Caso(String nombrePaciente, Fuente fuente, Region region, String estado) {
        this.nombrePaciente = nombrePaciente;
        this.fuente = fuente;
        this.region = region;               
        this.hora = Calendar.getInstance().getCalendarType();
        this.estado = estado;       
    }
    
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public Fuente getFuente() {
        return fuente;
    }

    public void setFuente(Fuente fuente) {
        this.fuente = fuente;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public int getId() {            
        return this.hashCode();
    }  

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    } 

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString(){
        return " ["+hora+"]: "+this.hashCode()+" "+region.getNombre()+"/"+fuente.nombre+" Nombre:"+nombrePaciente+"\n Estado:"+estado;
    }
}