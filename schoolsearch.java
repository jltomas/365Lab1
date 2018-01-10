/**
 * @author Justin Tomas and Victor Bodell
 * @version Lab1
 */

import java.util.*;
import java.io.*;

class schoolsearch {
   
   static final String Database = "students.txt";
   static final String commandError = "Command incorrectly formatted";
           
    
   static class Student {
      String stLastName;   // 0
      String stFirstName;  // 1
      int grade;           // 2
      int classroom;       // 3
      int bus;             // 4
      double GPA;          // 5
      String tLastName;    // 6
      String tFirstName;   // 7
   }

   public static void main(String[] args) throws FileNotFoundException {

      Scanner studentScanner = new Scanner(new FileInputStream(Database));
      ArrayList<Student> students = new ArrayList<Student>(); // input from file goes here

      while (studentScanner.hasNextLine()) { // line number
         String student = studentScanner.nextLine();
         String[] attributes = student.split(",");

         int j = 0;
         Student s = new Student();

         s.stLastName = attributes[j++];
         s.stFirstName = attributes[j++];
         s.grade = Integer.parseInt(attributes[j++]);
         s.classroom = Integer.parseInt(attributes[j++]);
         s.bus = Integer.parseInt(attributes[j++]);
         s.GPA = Double.parseDouble(attributes[j++]);
         s.tLastName = attributes[j++];
         s.tFirstName = attributes[j++];

         students.add(s);
      }
      
      // int numStudents = students.size();
      Scanner userInput = new Scanner(System.in);

      while (true) {
         System.out.print("Enter a command: ");
         String command = userInput.nextLine();
         String[] commandComponents = command.split("\\s+");
         int bus = 0;
         int arguments = Arrays.asList(commandComponents).size();
         
         boolean found = false;
         boolean infoCmd = false;

         // Check for quit
         String firstComm = commandComponents[0];
         if (firstComm.equals("Q") || firstComm.equals("Quit")) {
            System.out.println("Terminating program...");
            break;
         }
         else if (firstComm.equals("S:") || firstComm.equals("Student:")) {
            // Check for correct format
            if (arguments != 2 && arguments != 3) {
               System.out.println(commandError);
            }
            else {
               String lastName = commandComponents[1];
               // Search for last name
               // print last name, first name, grade, classroom, and name of teacher.
               // if bus flag is included, search for students, print last, first, and bus route.
               if (arguments == 3) {
                  if (commandComponents[2].equals("B") || commandComponents[2].equals("Bus")) {
                     for (Student s:students) {
                        if (s.stLastName.equals(lastName)) {
                           found = true;
                           System.out.println(s.stLastName + ", " + s.stFirstName + ", " + s.bus);
                        }
                     }
                  }
                  else
                     System.out.println(commandError);
               }
               else {
                  for (Student s: students) {
                     if (s.stLastName.equals(lastName)) {
                        found = true;
                        System.out.println(s.stLastName + ", " + s.stFirstName + ", " + s.grade + ", " + s.classroom + ", " + s.tLastName + ", " + s.tFirstName);
                     }
                  }
               }
            }
         }
         else if (firstComm.equals("T:") || firstComm.equals("Teacher:")) {
            // Check for correct format
            int arguments = Arrays.asList(commandComponents).size();
            if (arguments != 2) {
               System.out.println(commandError);
            }
            else {
                String teacherLN = commandComponents[1];
                for (Student s : students) { //Search for teacher
                    if(s.tLastName.equals(teacherLN)) {
                        found = true;
                        System.out.println(s.stLastName + ", " + s.stFirstName);
                    }
                }
            }
//            System.out.println("Queried teachers.");
         }
         else if (firstComm.equals("B:") || firstComm.equals("Bus:")) {
            // Check for correct format
            if (arguments != 2)
                System.out.println(commandError);
            else {
               // Check that the second argument is a number
               if (isNumeric(commandComponents[1])) {
                  int argBus = Integer.parseInt(commandComponents[1]);
                  for (Student s:students) {
                     if (s.bus == argBus) {
                        System.out.println(s.stFirstName + ", " + s.stLastName + ", " + s.grade + ", " + s.classroom);
                     }
                  }
               }
               else
                  System.out.println(commandError);
            }
         }
         else if (firstComm.equals("G:") || firstComm.equals("Grade:")) {
            System.out.println("Queried grades.");
         }
         else if (firstComm.equals("A:") || firstComm.equals("Average:")) {
            System.out.println("Queried averages.");
         }
         else if (firstComm.equals("I") || firstComm.equals("Info")) {
            infoCmd = true;
            System.out.println("Queried info.");
         }
         else {
            infoCmd = true;
            System.out.println("Invalid Command");
         }
         
         if(!found && !infoCmd) {
             System.out.println("Could not find a match for given query");
         }
      }
   }

   private static boolean isNumeric(String str) {
      try {
         int i = Integer.parseInt(str);
      }
      catch(NumberFormatException nfe) {
         return false;
      }
      return true;
   }
}
