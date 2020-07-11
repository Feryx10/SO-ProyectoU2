/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosou2;

/**
 *
 * @author Luciano
 */


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

public class TestThread {

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
}
