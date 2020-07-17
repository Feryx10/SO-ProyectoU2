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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

class PrintDemo {    
    
    boolean ocupado;
    public void printCount(String nombre, String fuente) throws InterruptedException {
        ocupado = true;
        try {
            System.out.println("Region: " +  nombre +", Fuente:"+ fuente + " Inicia" );            
            for(int i = 5; i > 0; i--) 
            {
        //       System.out.println("Counter   ---   "  + i );
            }  
        } catch (Exception e) {
            System.out.println("Thread  interrupted.");
        }
        
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Region: " +  nombre+", Fuente: "+fuente + " Finaliza.");
        ocupado=false;
        
        
    }
    
    public boolean ocupado()
    {
        return ocupado;
    }
}


class procesarCaso extends Thread  {
    private Thread t;
    private Region region;
    private int idFuente;
    private String fuente;
    private int caso;
    PrintDemo  PD;

    procesarCaso(Region region, PrintDemo pd, int caso) {
       PD = pd;
       this.fuente = region.getCasoID(caso).getFuente().getNombre();
       this.caso = caso;
       this.region=region;
    }
   
    @Override
    public void run() {
        synchronized(PD) {     
            new Fichero().escribir(region.getNombre(), region.getCasoID(caso),true);            
            try {
                PD.printCount(region.getNombre(), fuente);
            } catch (InterruptedException ex) {
                Logger.getLogger(procesarCaso.class.getName()).log(Level.SEVERE, null, ex);
            }
            
      }
    //  System.out.println("Thread " +  nombreRegion+", "+fuente + " exiting.");
      
   }

    @Override
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

   public static void main(String args[]) throws InterruptedException {    
        new Fichero().escribir(region1.getNombre(),"",false);  
        new Fichero().escribir(region2.getNombre(),"",false);   
        System.out.println("Bienvenido a la APP");
        int entrada=0;
        int region = 0;
        boolean program = true;
        Scanner sc = new Scanner(System.in);
        while(program) {
            System.out.println("");
            System.out.println("");
            System.out.println("[1] Generar N doctores y laboratorios aleatorios por región");
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
   
                int cant_doctores;
                int cant_lab;

                System.out.print("Ingrese cantidad de doctores: ");
                cant_doctores=sc.nextInt();
                System.out.print("Ingrese cantidad de laboratorios: ");
                cant_lab = sc.nextInt();
                
                crearFuentes(cant_doctores, cant_lab);
                
                                                    
            }
        
            
            if (entrada==2) {
                System.out.println("");
                System.out.print("Ingrese cantidad de casos: ");
                int cant_casos=0;
                cant_casos = sc.nextInt();
                generarCasos(cant_casos);
                
            }
            
            if (entrada==3) {
                
                int dias=0;             
                iniciarSimulacion(dias);
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
   
   public static void generarNombres(int cantDocs, int cantLabs)
   {
       //http://ensaimeitor.apsl.net/personas
       
       String nombres = "Salustiano Zorrilla Morelos \n" +
                        "Octavio Sariego Guinda \n" +
                        "Oliver Hesles Ledrado \n" +
                        "Gumersindo Medeiros Izcaray \n" +
                        "Luciana Bravo Errasti \n" +
                        "Zineb Maoño Cos \n" +
                        "Ekaterina Puebla Limofer \n" +
                        "Iovani Lanis Cediel \n" +
                        "Paola Pison Megid \n" +
                        "Nara Boliana Gayferos \n" +
                        "Tamara Francisco Soterraña \n" +
                        "Leonardo Yurre Ubidea \n" +
                        "Josué Guardo Robredo \n" +
                        "Verónica Cuba Almandoz \n" +
                        "Sara Cubero Lebrija \n" +
                        "Kimberly Tamaron Oribe \n" +
                        "Rodame Basante Santiyan \n" +
                        "Magnolia Sarricolea Cotorro \n" +
                        "Luana Arrieta Olivarez \n" +
                        "Celia Valdevielso Albarellos";
       
        String laboratorios =   "Hamaren\n" +
                                "Vors\n" +
                                "Creative Envirole\n" +
                                "Nasketz\n" +
                                "Soapsounds\n" +
                                "Noctorspot\n" +
                                "Fastbook\n" +
                                "Future Life Body\n" +
                                "Mass Yarns\n" +
                                "Websporthh\n" +
                                "Leaper\n" +
                                "Shopmingde\n" +
                                "Hings\n" +
                                "Mmsrd\n" +
                                "BoxPlate\n"+
                                "FGI EZ Networks\n" +
                                "Aks Corral\n" +
                                "AGP Insights\n" +
                                "Frame Canopy\n" +
                                "Itresphapi\n" +
                                "Ishaw\n" +
                                "Ultimate Trolley\n" +
                                "Jmlpa\n" +
                                "Bloom.fm\n" +
                                "Wolmbm\n" +
                                "ShampooBaker\n" +
                                "Ubega\n" +
                                "Writiner";
        
        
                
        String[] arrDocs = nombres.split("\n"); 
  
        for (int i=0; i<cantDocs; i++) 
        {
            Fuente doctor = new Fuente(arrDocs[i]);
            region1.agregarDoctor(doctor);
        }
        
        for (int i=0; i<cantDocs; i++) 
        {
            Fuente doctor = new Fuente(arrDocs[cantDocs-1-i]);
            region2.agregarDoctor(doctor);
        }
        
        String[] arrLabs = laboratorios.split("\n"); 
  
        for (int i=0; i<cantLabs; i++)
        {
            Fuente doctor = new Fuente("Laboratorio "+arrLabs[i]);
            region1.agregarLaboratorio(doctor);
        }
        
        for (int i=0; i<cantLabs; i++) 
        {
            Fuente doctor = new Fuente("Laboratorio "+arrLabs[cantLabs-1-i]);
            region2.agregarLaboratorio(doctor);
        }
        
   }
   
   public static void generarCasos(int cantCasos)
   {
       String nombreCasos =     "Maria Gonzalez\n"+
                                "Juan Rojas\n"+
                                "Jose Diaz\n"+
                                "Luis Perez\n"+
                                "Carlos Soto\n"+
                                "Jorge Contreras\n"+
                                "Ana Silva\n"+
                                "Rosa Martinez\n"+
                                "Manuel Sepulveda\n"+
                                "Cristian Morales\n"+
                                "Victor Rodriguez\n"+
                                "Francisco Lopez\n"+
                                "Hector Araya\n"+
                                "Patricia Fuentes\n"+
                                "Sergio Hernandez\n"+
                                "Pedro Torres\n"+
                                "Claudia Espinoza\n"+
                                "Carolina Flores\n"+
                                "Rodrigo Castillo\n"+
                                "Miguel Valenzuela\n"+
                                "Eduardo Ramirez\n"+
                                "Patricio Reyes\n"+
                                "Claudio Gutierrez\n"+
                                "Mario Castro\n"+
                                "Jaime Vargas\n"+
                                "Ricardo Alvarez\n"+
                                "Pablo Vasquez\n"+
                                "Alejandro Tapia\n"+
                                "Margarita Fernandez\n"+
                                "Carmen Lopez\n";
       
       String[] arrCasos = nombreCasos.split("\n"); 
       int randomgRegion;
        for (int i=0; i<cantCasos; i++) 
        {
            randomgRegion = ((int)Math.floor(Math.random()*2)); //random de region
            
            if (randomgRegion==1)
            {
                if((int)Math.floor(Math.random()*2) == 1) //Random de dar caso a laboratorio o doctor
                {
                    Caso caso = new Caso(arrCasos[i], region1.obtenerDoctor((int)Math.floor(Math.random()*region1.sizeDoctores())), region1, "Fallecido");
                    region1.agregarCaso(caso);
                }
                else
                {
                    Caso caso = new Caso(arrCasos[i], region1.obtenerLaboratorio((int)Math.floor(Math.random()*region1.sizeLaboratorios())), region1, "Contagiado");
                    region1.agregarCaso(caso);
                }
                
            }
            else
            {
                if((int)Math.floor(Math.random()*2) == 1)// Random de dar caso a lab o doc
                {
                    
                    Caso caso = new Caso(arrCasos[i], region2.obtenerDoctor((int)Math.floor(Math.random()*region2.sizeDoctores())), region2, "Fallecido");
                    region1.agregarCaso(caso);
            
                }
                else
                {
                    Caso caso = new Caso(arrCasos[i], region2.obtenerLaboratorio((int)Math.floor(Math.random()*region2.sizeLaboratorios())), region2, "Contagiado");
                    region2.agregarCaso(caso);
                }
                
            }
            
        }
       
   }
   
   
   public static void iniciarSimulacion(int dias) throws InterruptedException
   {    
       
 
        PrintDemo PD1 = new PrintDemo();
        PrintDemo PD2 = new PrintDemo();
        int totalCasos = (region1.casosDisponibles()+region2.casosDisponibles());
        System.out.println(totalCasos);
        int finDia;
        
        int numCasos = 0;
      // wait for threads to end
        for (int i = 1; 0 < totalCasos; i++) {
            System.out.println("Dia: "+i);
            finDia=0;
            while (finDia==0) {
                
                if((int)Math.floor(Math.random()*5 )<=3)
                {
                    if((int)Math.floor(Math.random()*2 )==1)
                    {
                        Caso caso = region1.casosNuevos();
                        if(caso != null && caso.getEstado().equals("Fallecido"))
                        {
                            caso.setDiaProcesado(i);
                            caso.setProcesado();
                            
                            procesarCaso regionUnoDoctores = new procesarCaso( region1, PD1, caso.getId());
                            regionUnoDoctores.start();
                            try 
                            {
                                regionUnoDoctores.join();
                            } 
                            catch (InterruptedException e) 
                            {
                                System.out.println("Interrupted");
                            }
                        }
                        else if (caso!=null)
                        {
                            caso.setDiaProcesado(i);
                            caso.setProcesado();
                            procesarCaso regionUnoLaboratorios = new procesarCaso( region1, PD2, caso.getId());
                            regionUnoLaboratorios.start();
                            try 
                            {
                                regionUnoLaboratorios.join();
                            } 
                            catch (InterruptedException e) 
                            {
                                System.out.println("Interrupted");
                            }


                        }
                    }
                    else if(region2.cantidadCasos()>0)
                    {
                        
                        Caso caso = region2.casosNuevos();
                        
                        if(caso != null && caso.getEstado().equals("Fallecido"))
                        {
                            caso.setDiaProcesado(i);
                            caso.setProcesado();
                            procesarCaso regionDosDoctores = new procesarCaso( region2, PD1, caso.getId());
                            regionDosDoctores.start();
                            try 
                            {
                                regionDosDoctores.join();
                            } 
                            catch (InterruptedException e) 
                            {
                                System.out.println("Interrupted "+e.getMessage());
                            }
                        }
                        else if (caso != null)
                        {
                            caso.setDiaProcesado(i);
                            caso.setProcesado();
                            procesarCaso regionDosLaboratorios = new procesarCaso( region2, PD2, caso.getId());
                            regionDosLaboratorios.start();
                            try 
                            {
                                regionDosLaboratorios.join();
                            } 
                            catch (InterruptedException e) 
                            {
                                System.out.println("Interrupted "+e.getMessage());
                            }
                        }
                    }
                }
                else
                {
                    while(finDia==0)
                    {
                        if(!PD1.ocupado && !PD2.ocupado)
                        {
                            finDia=1;
                            TimeUnit.SECONDS.sleep(4);
                            System.out.println("Fin del dia "+ i);
                            new Fichero().escribir(region1.getNombre(),"Fin del dia "+ i,true); 
                            new Fichero().escribir(region2.getNombre(),"Fin del dia "+ i,true); 
                            ArrayList<String> aux = new Fichero().leer(region1.getNombre(),i); 
                            new Fichero().escribir("Documento_Oficial_Dia "+i, " Lista oficial balance dia "+i,false);  
                            new Fichero().escribir("Documento_Oficial_Dia "+i, "",true);    
                            for (int j = 0; j < aux.size(); j++) {
                                new Fichero().escribir("Documento_Oficial_Dia "+i, aux.get(j),true);                                
                            }
                            aux = new Fichero().leer(region2.getNombre(),i); 
                            for (int j = 0; j < aux.size(); j++) {
                                new Fichero().escribir("Documento_Oficial_Dia "+i, aux.get(j),true);  
                            }                              
                        }                   
                    }                   
                }               
                //  TimeUnit.SECONDS.sleep(1);                
                    numCasos++;
                }        
            totalCasos = (region1.casosDisponibles()+region2.casosDisponibles());
        }
        
        
    } 

    private static void crearFuentes(int cant_doctores, int cant_lab) {
        for (int i = 0; i < cant_doctores; i++)
        {
      //      region1.agregarDoctor();
        }
        generarNombres(cant_doctores, cant_lab);
    }
}



class Fichero implements FuncionFichero {
    
    public Fichero(){    
    }
    
    @Override
    public void escribir(String nombreArchivo, Caso caso, boolean sobrescribir) {        
       try {
            File file = new File(nombreArchivo+".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file, sobrescribir);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)) ;
            bw.write(caso.toString());
            bw.newLine();
            bw.close();           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }
    
    @Override
    public void escribir(String nombreArchivo, String texto, boolean sobrescribir) {        
       try {
            File file = new File(nombreArchivo+".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file, sobrescribir);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)) ;
            bw.write(texto);
            bw.newLine();
            bw.close();           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }

    @Override
    public ArrayList <String> leer(String nombreArchivo, int dia) { 
       ArrayList <String> aux = new ArrayList <>();    
       try {        
            File file = new File(nombreArchivo+".txt");      
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String linea;         
            while((linea = br.readLine())!=null){    
                if(linea.equals("Fin del dia "+ (dia-1))){
                    aux.clear();                    
                }      
                if(linea.equals("Fin del dia "+ dia))
                    break;   
                if(!linea.equals("Fin del dia "+ (dia-1))) 
                    aux.add(linea);                           
            }        
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
       return aux;
    }
}
