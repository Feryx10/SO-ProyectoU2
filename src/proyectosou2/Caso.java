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
import java.util.Objects;

public class Caso {
    private String nombrePaciente;
    private int diaProcesado;
    private Fuente fuente;
    private Region region;   
    private String hora;
    private String estado;
    private Boolean procesado=false;

    public Caso(String nombrePaciente, Fuente fuente, Region region, String estado) {
        this.nombrePaciente = nombrePaciente;
        this.fuente = fuente;
        this.region = region;               
        this.hora = Calendar.getInstance().getTime().toString();
        this.estado = estado;       
    }
    
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public void setProcesado()
    {
        procesado = true;
    }
    public Fuente getFuente() {
        return fuente;
    }

    public void setFuente(Fuente fuente) {
        this.fuente = fuente;
    }
    
    public void setDiaProcesado(int i)
    {
        this.diaProcesado=i;
    }

    public Region getRegion() {
        return region;
    }
    
    public boolean getProcesado()
    {
        return procesado;
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
        return " ["+hora+"]: "+this.getId()+" "+region.getNombre()+"/"+fuente.nombre+" Nombre:"+nombrePaciente+"\n Estado:"+estado;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Caso other = (Caso) obj;
        if (!Objects.equals(this.nombrePaciente, other.nombrePaciente)) {
            return false;
        }
        return Objects.equals(this.region.getNombre(), other.region.getNombre());
    }

    @Override
    public int hashCode(){
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.nombrePaciente);
        hash = 29 * hash + Objects.hashCode(this.region.getNombre());
        return hash;
    }
}