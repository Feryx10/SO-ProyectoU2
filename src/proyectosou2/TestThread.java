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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class PrintDemo {    
    
    public void printCount(String nombre, String fuente) {
        try {
            System.out.println("Starting " +  nombre +", "+ fuente );
            for(int i = 5; i > 0; i--) 
            {
         //       System.out.println("Counter   ---   "  + i );
            }  
        } catch (Exception e) {
            System.out.println("Thread  interrupted.");
        }
        System.out.println("Thread " +  nombre+", "+fuente + " exiting.");
        
    }
}


class procesarCaso extends Thread  {
    private Thread t;
    private String nombreRegion;
    private int idFuente;
    private String fuente;
    PrintDemo  PD;

    procesarCaso( Region region,  PrintDemo pd, String fuente) {
       nombreRegion = region.getNombre();
       PD = pd;
       this.fuente = fuente;
    }
   
   public void run() {
      synchronized(PD) {
         PD.printCount(nombreRegion, fuente);
      }
    //  System.out.println("Thread " +  nombreRegion+", "+fuente + " exiting.");
      
   }

   public void start () {
  //    System.out.println("Starting " +  nombreRegion +", "+ fuente );
      if (t == null) {
         t = new Thread (this);
         t.start ();
      }
   }
}

public class TestThread implements FuncionFichero {

    static Region region1 = new Region("Maule");
    static Region region2 = new Region("Bio-Bio");
    
   public static void main(String args[]) {
      
      
        iniciarSimulacion();

      
   }
   
   public static void iniciarSimulacion()
   {
        
        
        PrintDemo PD1 = new PrintDemo();
        PrintDemo PD2 = new PrintDemo();
        procesarCaso regionUnoLaboratirios = new procesarCaso( region1, PD1, "Lab" );
        procesarCaso regionUnoDoctores = new procesarCaso( region1, PD2, "Doc" );
        procesarCaso regionDosLaboratorios = new procesarCaso( region2, PD1, "Lab");
        procesarCaso regionsDosDoctorees = new procesarCaso( region2, PD2, "Doc" );
        regionUnoLaboratirios.start();
        regionUnoDoctores.start();
        regionDosLaboratorios.start();
        regionsDosDoctorees.start();

      // wait for threads to end
        try {
            regionUnoLaboratirios.join();
            regionUnoDoctores.join();
            regionDosLaboratorios.join();
            regionsDosDoctorees.join();
        } catch ( Exception e) {
            System.out.println("Interrupted");
        }
   }

    @Override
    public void escribir(String direccion, Caso caso) {        
       try {
           BufferedWriter bw = new BufferedWriter(new FileWriter(direccion));
           bw.write(caso.toString() + "\n");
       } catch (IOException ex) {
           Logger.getLogger(TestThread.class.getName()).log(Level.SEVERE, null, ex);
       }
      
    }   

    @Override
    public String leer(String direccion) {           
       try {
           BufferedReader br = new BufferedReader(new FileReader(new File(direccion)));
           String linea;
           String aux = "";
           while((linea = br.readLine())!=null){
               
           }
           
       } catch (IOException ex) {
           Logger.getLogger(TestThread.class.getName()).log(Level.SEVERE, null, ex);
       }
       return "jaja";
    }
}
