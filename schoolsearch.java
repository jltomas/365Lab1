/**
 * @author Justin Tomas and Victor Bodell
 * @version Lab1
 */

import java.util.*;
import java.io.*;

class schoolsearch {
   
   static final String Database = "students.txt";
   static final String commandError = "Command incorrectly formatted";
   static final int maxGrade = 6;
           
    
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
      
      
      try {
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

          boolean prevWasComment = false;
          while (true) {
             if(!prevWasComment){
             System.out.print("Enter a command: ");
         }
             String command = userInput.nextLine();
             String[] commandComponents = command.split("\\s+");
             int bus = 0;
             int arguments = Arrays.asList(commandComponents).size();
             
             boolean found = false;
             boolean infoCmd = false;

             String firstComm = commandComponents[0];

         // Parse comments
         if (firstComm.equals("#") || firstComm.equals(""))
            prevWasComment = true;
         else
            prevWasComment = false;

             // Check for quit
         if (firstComm.equals("Q") || firstComm.equals("Quit")) {
                System.out.println("Terminating program...");
                break;
             }
             else if (firstComm.equals("S:") || firstComm.equals("Student:")) {
                // Check for correct format
                if (arguments != 2 && arguments != 3) {
                   System.out.println(commandError);
                   found = true;
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
                         found = true;
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
                if (arguments != 2) {
                   System.out.println(commandError);
                   found = true;
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
//                System.out.println("Queried teachers.");
             }
             else if (firstComm.equals("B:") || firstComm.equals("Bus:")) {
                // Check for correct format
                if (arguments != 2) {
                    System.out.println(commandError);
                    found = true;
                }
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
                   else {
                      System.out.println(commandError);
                      found = true;
                   }
                }
             }
             
             //Grade instruction
             //Requires a grade int & optional High/Low flag
             else if (firstComm.equals("G:") || firstComm.equals("Grade:")) {
                if(arguments != 2 && arguments != 3) { 
                    System.out.println(commandError);
                    found = true;
                }
                
                else {
                    if(!isNumeric(commandComponents[1])) {
                        System.out.println(commandError);
                        found = true;
                    } 
                    else {
                        int argGrade = Integer.parseInt(commandComponents[1]);

                        if(arguments == 2) {
                            for(Student s : students) { //Find all students w/ grade == argGrade
                                if (s.grade == argGrade) {
                                    System.out.println(s.stLastName + ", " + s.stFirstName);
                                    found = true;
                                }
                            }
                        }
                        else { 
                            //Search for Hi/Lo GPA
                            Boolean hiGrade = null;
                            if(commandComponents[2].equals("H") || commandComponents[2].equals("High"))
                                hiGrade = true;
                            else if(commandComponents[2].equals("L") || commandComponents[2].equals("Low"))
                                hiGrade = false;
                            else {
                                System.out.println(commandError);
                                found = true;
                            }
                            if(hiGrade != null) {
                                //Find hi/lo student among same-graders & report
                                Student uniq = null;
                                for(Student s : students) {
                                    if(s.grade == argGrade) {
                                        if(uniq == null) {
                                            found = true;
                                            uniq = s;
                                        }
                                        else if(hiGrade && s.GPA > uniq.GPA) {
                                            uniq = s;
                                        }
                                        else { //hiGrade == false, find lo-student
                                            if(s.GPA < uniq.GPA) { //Only update if curr stud has lower grade than uniq
                                                uniq = s;
                                            }
                                        }                                    
                                    }
                                }
                                if(found) {
                                    System.out.println(uniq.stLastName + ", " + uniq.stFirstName + ", gpa:" + uniq.GPA + 
                                            ", teacher:" + uniq.tLastName + ", " + uniq.tFirstName + ", bus:" + uniq.bus);
                                }
                            }
                        }
                    }
                }
                
             }
             else if (firstComm.equals("A:") || firstComm.equals("Average:")) {
                // Check for correct format
                if (arguments != 2) {
                    System.out.println(commandError);
                    found = true;
                }
                else {
                   double runningTotal = 0;
                   int counter = 0;
                   
                   for (int i = 0; i < students.size(); i++) {
                      if (students.get(i).grade == Integer.parseInt(commandComponents[1])) {
                         counter++;
                         runningTotal += students.get(i).GPA;
                      }
                   }

                   if (counter == 0)
                      System.out.println("No students exist for this grade");
                   else
                      System.out.println("Average GPA for Grade " + commandComponents[1] + " is " + (runningTotal/counter));
                   found = true;
                }
             }
             
             //Info instruction
             else if (firstComm.equals("I") || firstComm.equals("Info")) {
                infoCmd = true;
                
                //Traverse each grade & report # of stud in each grade
                int[] numOfStuds = new int[maxGrade+1];
                for(Student s : students) {
                    numOfStuds[s.grade]++;
                }
                
                for(int i=0; i <= maxGrade; i++) {
                    System.out.println(i + ": " + numOfStuds[i]);
                }
             }
             else {
                infoCmd = true; // Invalid command, don't print no match
            if(!prevWasComment)
                    System.out.println("Invalid Command");
             }
             
             if(!found && !infoCmd && !prevWasComment) {
                 System.out.println("Could not find a match for given query");
             }
          }
      }
      // Error-check file input
      catch(FileNotFoundException e) {
          System.out.println("File not found! Make sure database is named 'students.txt' \nTerminating...");
          System.exit(1);
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
