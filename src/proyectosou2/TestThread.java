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
import java.util.Scanner;
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
         new Fichero().escribir(nombreRegion, new Caso());
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

public class TestThread {

    static Region region1 = new Region("Maule");
    static Region region2 = new Region("Bio-Bio");
    
   public static void main(String args[]) {
      
        
       
        System.out.println("Bienvenido a la APP");
        int entrada=0;
        int region = 0;
        boolean program = true;
        Scanner sc = new Scanner(System.in);
        while(program) {
            System.out.println("");
            System.out.println("");
            System.out.println("[1] Ingrese como administrador");
            System.out.println("[2] Generar N casos aleatorios");
            System.out.println("[3] Iniciar Simulación");
            System.out.println("[4] Funciones del servicio de salud");
            System.out.println("[0] Salir");
            
            
            System.out.print("Ingrese un número: ");
            entrada= sc.nextInt();
            
            if (entrada<0 || entrada >4) {
                System.out.println("");
                System.out.println("Entrada inválida");
                System.out.println("");
            }
            
            if (entrada==1) {
                int ent2=0;
                System.out.println("");
                System.out.println("[1] Generar N doctores y laboratorios aleatorios");
                System.out.print("Ingrese un número: ");
                ent2=sc.nextInt();
                
                if (ent2==1) {
                    System.out.println("");
                    System.out.println("Elija región");
                    System.out.println("[1] Región A");
                    System.out.println("[2] Región B");
                    System.out.print("Ingrese un número: ");
                    region=sc.nextInt();
                    
                    if (region>2 || region <1) {
                        System.out.println("");
                        System.out.println("Entrada inválida");
                        System.out.println("");
                    }
                    else {
                        int cant_doctores=0;
                        int cant_lab=0;
                        
                        System.out.print("Ingrese cantidad de doctores: ");
                        cant_doctores=sc.nextInt();
                        System.out.print("Ingrese cantidad de laboratorios: ");
                        cant_lab = sc.nextInt();
                    }
                }
                else {
                    System.out.println("Entrada inválida");
                }                                      
            }
            
            if (entrada==2) {
                System.out.println("");
                System.out.println("Elija región");
                System.out.println("[1] Región A");
                System.out.println("[2] Región B");
                System.out.print("Ingrese un número: ");
                region=sc.nextInt();
                    
                if (region>2 || region <1) {
                    System.out.println("");
                    System.out.println("Entrada inválida");
                    System.out.println("");
                }
                
                else {
                    int cant_contagiados=0;
                    int cant_muertos=0;
                        
                    System.out.print("Ingrese cantidad de contagiados: ");
                    cant_contagiados=sc.nextInt();
                    System.out.print("Ingrese cantidad de muertos: ");
                    cant_muertos = sc.nextInt();
                }
            }
            
            if (entrada==3) {
                
                int dias=0;
                System.out.print("Ingrese cantidad de días: ");
                dias=sc.nextInt();
            }
            
            if (entrada==4) {
                System.out.println("");
                System.out.println("[1] Eliminar Caso");
                System.out.println("[2] Modificar Caso");
                System.out.println("[3] Exportar Caso");
                
                System.out.print("Ingrese un número: ");
                int ent2=sc.nextInt();
                
            }
            
            if (entrada==0) {
                program=false;
            }
            
            
        }
      
        //iniciarSimulacion();

      
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
}

class Fichero implements FuncionFichero {
    
    public Fichero(){    
    }
    
    @Override
    public void escribir(String nombreArchivo, Caso caso) {        
       try {               
            File file = new File("/"+nombreArchivo+".txt");      
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(caso.toString());
            bw.newLine();
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }   

    @Override
    public String[] leer(String nombreArchivo) { 
       String[]aux = null;       
       try {        
            File file = new File("/"+nombreArchivo+".txt");      
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String linea;              
            while((linea = br.readLine())!=null){
                aux = linea.split("");
            }        
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
       return aux;
    }    
}
