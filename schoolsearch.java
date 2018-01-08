/**
 * @author Justin Tomas
 * @version Lab1
 */

import java.util.*;

class schoolsearch {
   static class student {
      String StLastName;
      String StFirstName;
      int Grade;
      int Classroom;
      int Bus;
      int GPA;
      String TLastName;
      String TFirstName;
   }

   public static void main(String[] args) {
      Scanner studentScanner = new Scanner("students.txt");
      String input = studentScanner.nextLine();

      ArrayList<student> students = new ArrayList<student>(); // input from file goes here
      while(studentScanner.hasNextLine()) { // line number
         String student = studentScanner.nextLine();
         String[] attributes = student.split(" ");
         
         int j = 0;
         student s = new student();
         s.StLastName = attributes[j++];
         s.StFirstName = attributes[j++];
         s.Grade = Integer.parseInt(attributes[j++]);
         s.Classroom = Integer.parseInt(attributes[j++]);
         s.Bus = Integer.parseInt(attributes[j++]);
         s.GPA = Integer.parseInt(attributes[j++]);
         s.TLastName = attributes[j++];
         s.TFirstName = attributes[j++];

         students.add(s);
      }
   }
}
