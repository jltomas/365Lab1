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

      while(true) {
         System.out.print("Enter a command: ");
         String command = userInput.nextLine();
         String[] commandComponents = command.split("\\s+");

         if (commandComponents[0].equals("Q") || commandComponents[0].equals("Quit")) {
            System.out.println("Terminating program...");
            break;
         }
         else if (commandComponents[0].equals("S") || commandComponents[0].equals("Student")) {
            System.out.println("Queried students.");
         }
         else if (commandComponents[0].equals("T") || commandComponents[0].equals("Teacher")) {
            System.out.println("Queried teachers.");
         }
         else if (commandComponents[0].equals("B") || commandComponents[0].equals("Bus")) {
            System.out.println("Queried buses.");
         }
         else if (commandComponents[0].equals("G") || commandComponents[0].equals("Grade")) {
            System.out.println("Queried grades.");
         }
         else if (commandComponents[0].equals("A") || commandComponents[0].equals("Average")) {
            System.out.println("Queried averages.");
         }
         else if (commandComponents[0].equals("I") || commandComponents[0].equals("Info")) {
            System.out.println("Queried info.");
         }
         else
            System.out.println("Invalid Command");
      }
   }
}
