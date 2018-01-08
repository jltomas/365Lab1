/**
 * @author Justin Tomas
 * @version Lab1
 */

import java.util.*;
import java.io.*;

class schoolsearch {
   static class student {
      String StLastName;
      String StFirstName;
      int Grade;
      int Classroom;
      int Bus;
      double GPA;
      String TLastName;
      String TFirstName;
   }

   public static void main(String[] args) throws FileNotFoundException {
      Scanner studentScanner = new Scanner(new FileInputStream("students.txt"));

      ArrayList<student> students = new ArrayList<student>(); // input from file goes here

      while(studentScanner.hasNextLine()) { // line number
         String student = studentScanner.nextLine();
         String[] attributes = student.split(",");

         System.out.println(attributes[0]);
         
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
   }
}
