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

public class TestThread implements FuncionFichero {

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
      } catch (Exception e) {
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
