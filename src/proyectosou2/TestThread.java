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
    
    public void printCount() {
        try {
            for(int i = 5; i > 0; i--) 
            {
                System.out.println("Counter   ---   "  + i );
            }  
        } catch (Exception e) {
            System.out.println("Thread  interrupted.");
        }
    }
}

class procesarCaso extends Thread  {
   private Thread t;
   private String threadName;
   PrintDemo  PD;

   procesarCaso( String name,  PrintDemo pd) {
      threadName = name;
      PD = pd;
   }
   
   public void run() {
      synchronized(PD) {
         PD.printCount();
      }
      System.out.println("Thread " +  threadName + " exiting.");
      
   }

   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}

public class TestThread {

   public static void main(String args[]) {
      PrintDemo PD = new PrintDemo();

      procesarCaso T1 = new procesarCaso( "Thread - 1 ", PD );
      procesarCaso T2 = new procesarCaso( "Thread - 2 ", PD );

      T1.start();
      T2.start();

      // wait for threads to end
      try {
         T1.join();
         T2.join();
      } catch ( Exception e) {
         System.out.println("Interrupted");
      }
   }
}
