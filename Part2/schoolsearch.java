/**
 * @author Justin Tomas and Victor Bodell
 * @version Lab1
 */

import java.util.*;
import java.io.*;

class schoolsearch {
   
   static final String Students = "list.txt";
   static final String Teachers = "teachers.txt";
   static final String commandError = "Command incorrectly formatted";
   static final int maxGrade = 6;
           
  static class Student {
      String stLastName;   // 0
      String stFirstName;  // 1
      int grade;           // 2
      int classroom;       // 3
      int bus;             // 4
      double GPA;          // 5
   }
   
   static class Teacher {
      String TLastName;
      String TFirstName;
      int classroom;
   }

   public static void main(String[] args) throws FileNotFoundException {
      
      
      try {
          Scanner studentScanner = new Scanner(new FileInputStream(Students));
          ArrayList<Student> students = new ArrayList<Student>(); // input from file goes here

          while (studentScanner.hasNextLine()) { // line number
             String student = studentScanner.nextLine();
             String[] attributes = student.split(",");

             int j = 0;
             Student s = new Student();

             s.stLastName = attributes[j++].trim();
             s.stFirstName = attributes[j++].trim();
             s.grade = Integer.parseInt(attributes[j++]);
             s.classroom = Integer.parseInt(attributes[j++]);
             s.bus = Integer.parseInt(attributes[j++]);
             s.GPA = Double.parseDouble(attributes[j++]);

             students.add(s);
          }

          // Create the teacher ArrayList
          Scanner teacherScanner = new Scanner(new FileInputStream(Teachers));
          ArrayList<Teacher> teachers = new ArrayList<Teacher>();

          while(teacherScanner.hasNextLine()) {
             String teacher = teacherScanner.nextLine();
             String[] attributes = teacher.split(",");

             int j = 0;
             Teacher t = new Teacher();

             t.TLastName = attributes[j++].trim();
             t.TFirstName = attributes[j++].trim();
             t.classroom = Integer.parseInt(attributes[j++].trim());

             teachers.add(t);
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
                      String teacherLast = null, teacherFirst = null;
                      for (Student s: students) {
                         if (s.stLastName.equals(lastName)) {
                            found = true;


                            for (Teacher t: teachers) {
                                if (s.classroom == t.classroom) {
                                    teacherLast = t.TLastName;
                                    teacherFirst = t.TFirstName;
                                }
                            }
                            System.out.println(s.stLastName + ", " + s.stFirstName + ", " + s.grade + ", " + s.classroom + ", " + teacherLast + ", " + teacherFirst);
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
                    boolean foundTeacher = false;

                    int classroom = 0;
                    for (Teacher t : teachers) {
                       if (t.TLastName.equals(teacherLN)) {
                          foundTeacher = true;
                          classroom = t.classroom;
                          break;
                       }
                    }

                    if(foundTeacher) {
                       for (Student s : students) { //Search for teacher
                          if(s.classroom == classroom) {
                             found = true;
                             System.out.println(s.stLastName + ", " + s.stFirstName);
                          }
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
                            found = true;
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
                                   int classroom;
                                   String tLastName = null, tFirstName = null;
                                   for (Teacher t : teachers) {
                                      if (t.classroom == uniq.classroom) {
                                         tLastName = t.TLastName;
                                         tFirstName = t.TFirstName;
                                      }
                                   }
                                   System.out.println(uniq.stLastName + ", " + uniq.stFirstName + ", gpa:" + uniq.GPA + 
                                            ", teacher:" + tLastName + ", " + tFirstName + ", bus:" + uniq.bus);
                                }
                            }
                            else if(commandComponents[2].equals("T") || commandComponents[2].equals("Teacher")){ //List teachers of given grade
                                Set<Integer> tempClasses = new HashSet<Integer>();
                                for(Student s : students) {
                                    if(s.grade == argGrade) {
                                        found = true;
                                        tempClasses.add(s.classroom);
                                    }
                                }
                                for(Teacher t : teachers) {
                                    if(tempClasses.contains(t.classroom)) {
                                        System.out.println(t.TLastName + ", " + t.TFirstName);
                                    }
                                }
                            }
                            else {
                                System.out.println(commandError);
                                found = true;
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
             else if (firstComm.equals("C:") || firstComm.equals("Class:")) {
                 //Two scenarios: List students (arg==2) OR List Teachers (arg==3)
                 if (arguments != 2 && arguments != 3) {
                     System.out.println(commandError);
                     found = true;
                 }
                 else {
                     // Get classroom number 
                     if(!isNumeric(commandComponents[1])) {
                         System.out.println(commandError);
                         found = true;
                     }
                     else {
                         int classroomQuery = Integer.parseInt(commandComponents[1]);
                         if(arguments == 2) {
                             for (Student s : students) {
                                 if(s.classroom == classroomQuery) {
                                     found = true;
                                     System.out.println(s.stLastName + ", " + s.stFirstName + ", " + s.grade + ", " + s.GPA);
                                 }
                             }
                         }
                         else if (commandComponents[2].equals("T") || commandComponents[2].equals("Teacher")){
                             for (Teacher t : teachers) {
                                 if(t.classroom == classroomQuery) {
                                     found = true;
                                     System.out.println(t.TLastName + ", " + t.TFirstName);
                                 }
                             }
                         }
                         else {
                             System.out.println(commandError);
                             found = true;
                         }
                     }
                 }
             }
             else if (firstComm.equals("E") || firstComm.equals("Enrolled")) {
                 found = true; //Either we print commandError OR list of classrooms
                 if (arguments != 1) {
                     System.out.println(commandError);
                 }
                 else {
                     TreeMap<Integer, Integer> classCount = new TreeMap<Integer, Integer>();
                     for (Student s : students) {
                         // Add class as key if non-existent, increment value of class-key
                         if(classCount.containsKey(s.classroom)) {
                             classCount.put(s.classroom, classCount.get(s.classroom) + 1); // Increment if classroom has students 
                         }
                         else {
                             classCount.put(s.classroom, 1); //At least one student's in the classroom
                         }
                     }
                     //Sort by keys (classroom), and print
                     for(Integer classroom : classCount.keySet()) {
                         System.out.println(classroom + ": " + classCount.get(classroom));
                     }
                 }
             }
             else if (firstComm.equals("Analyze") || firstComm.equals("Z")) {
                if (arguments != 2) {
                   System.out.println(commandError);
                }
                else if (commandComponents[1].equals("G")) {
                   double[] averages = new double[maxGrade + 1];

                   for (int i = 0; i <= maxGrade; i++) {
                      int numStudents = 0;
                      for (int j = 0; j < students.size(); j++) {
                         if (students.get(j).grade == i) {
                            averages[i] += students.get(j).GPA;
                            numStudents++;
                         }
                      }

                      averages[i] = averages[i] / numStudents;
                   }

                   for (int i = 0; i <= maxGrade; i++) {
                      System.out.println("Average GPA for grade " + i + " is " + averages[i]);
                   }
                }
                else if (commandComponents[1].equals("T")) {

                }
                else if (commandComponents[1].equals("B")) {
                }
                else
                    System.out.println(commandError);
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
          System.out.println("File not found! Make sure database is named 'list.txt' or 'teachers.txt' \nTerminating...");
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
