/**
 * @author Justin Tomas and Victor Bodell
 * @version Lab1
 */

import java.util.*;
import java.io.*;

class schoolsearch {
   static class student {
      String StLastName;   // 0
      String StFirstName;  // 1
      int Grade;           // 2
      int Classroom;       // 3
      int Bus;             // 4
      double GPA;          // 5
      String TLastName;    // 6
      String TFirstName;   // 7
   }

   public static void main(String[] args) throws FileNotFoundException {
      Scanner studentScanner = new Scanner(new FileInputStream("students.txt"));

      ArrayList<student> students = new ArrayList<student>(); // input from file goes here

      while(studentScanner.hasNextLine()) { // line number
         String student = studentScanner.nextLine();
         String[] attributes = student.split(",");

         int j = 0;
         student s = new student();

         s.StLastName = attributes[j++];
         s.StFirstName = attributes[j++];
         s.Grade = Integer.parseInt(attributes[j++]);
         s.Classroom = Integer.parseInt(attributes[j++]);
         s.Bus = Integer.parseInt(attributes[j++]);
         s.GPA = Double.parseDouble(attributes[j++]);
         s.TLastName = attributes[j++];
         s.TFirstName = attributes[j++];

         students.add(s);
      }
      
      Scanner userInput = new Scanner(System.in);
      System.out.print("Enter a command: ");
      String command = userInput.nextLine();
      System.out.println(command);
   }
}
