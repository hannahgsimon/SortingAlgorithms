package sorting_algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sorting_Algorithms
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Faculty> faculty = new ArrayList<>();
        int sel = 9; //arbitrary
        while(true)
        {
            sel = 9; //arbitrary
            System.out.println("""
                               1) Author Info
                               2) Add Student
                               3) Add Faculty
                               4) Sort by ID (selection sort)
                               5) Sort by ID (radix sort)
                               6) Sort Students by GPA (merge sort)
                               7) Sort Faculty by Course load (insertion sort)
                               8) Print data
                               0) Exit""");
            try
            {
                sel = input.nextInt();
                if ((sel < -1) || (sel > 8))
                {
                    System.err.println("Invalid selection; Please enter an integer 0-8.");
                }
            }
            catch (InputMismatchException e)
            {
                System.err.println("Invalid selection; Please enter an integer 0-8.");
                input.next();
            }
            
            if (sel == 0)
            {
                break;
            }
            else if (sel == 1)
            {
                System.out.println("\nAuthor Info: Hannah Simon\n");
            }
            else if (sel == 2)
            {
                students.add(new Student(input));
            }
            else if (sel == 3)
            {
                System.out.println("\nEnter Name");
                String name = input.next();
                
                System.out.println("\nEnter id");
                int id = 0;
                boolean gotCorrect = false;
                while (!gotCorrect)
                {
                    try
                    {
                        id = input.nextInt();
                        if (id < 0)
                        {
                            System.err.println("Error; Please enter a positive integer number.");
                        }
                        else
                        {
                            System.out.println("id " + id + " has successfully been added to faculty " + name + "\n");
                            gotCorrect = true;
                        }
                    }
                    catch (InputMismatchException e)
                    {
                        System.err.println("Error; Please enter an integer number.");
                        input.next();
                    }
                }
                input.skip(".*");
                
                System.out.println("Enter Course Load");
                int courseLoad = 0;
                gotCorrect = false;
                while (!gotCorrect)
                {
                    try
                    {
                        courseLoad = input.nextInt();
                        if (courseLoad < 0)
                        {
                            System.err.println("Error; Please enter a positive integer number.");
                        }
                        else
                        {
                            System.out.println("Courseload " + courseLoad + " has successfully been added to faculty " + name + "\n");
                            gotCorrect = true;
                        }
                    }
                    catch (InputMismatchException e)
                    {
                        System.err.println("Error; Please enter an integer number.");
                        input.next();
                    }
                }                
                
                faculty.add(new Faculty(name, id, courseLoad));
            }
            else if (sel == 4)
            {
                System.out.println("1) Sort Students\n2) Sort Faculty\n");

                boolean gotCorrect = false;
                while (!gotCorrect)
                {
                    try
                    {
                        sel = input.nextInt();
                        if (sel != 1 && sel != 2)
                        {
                            System.err.println("Error; Please enter 1 or 2.");
                        }
                        else
                        {
                            gotCorrect = true;
                        }
                    }
                    catch (InputMismatchException e)
                    {
                        System.err.println("Error; Please enter an integer number.");
                        input.next();
                    }
                }
                input.skip(".*");
                
                if (sel == 1)
                {
                    new SortMethods<Student>().selectionSortAscendingId(students);
                }
                else if (sel == 2)
                {
                    new SortMethods<Faculty>().selectionSortAscendingId(faculty);
                }
            }
            else if (sel == 5)
            {
                //name
                System.out.println("1) Sort Students\n2) Sort Faculty\n");
                
                boolean gotCorrect = false;
                while (!gotCorrect)
                {
                    try
                    {
                        sel = input.nextInt();
                        if (sel != 1 && sel != 2)
                        {
                            System.err.println("Error; Please enter 1 or 2.");
                        }
                        else
                        {
                            gotCorrect = true;
                        }
                    }
                    catch (InputMismatchException e)
                    {
                        System.err.println("Error; Please enter an integer number.");
                        input.next();
                    }
                }
                input.skip(".*");
                
                if (sel == 1)
                {
                    if (students.isEmpty())
                    {
                        System.out.printf("No students entered in database, no data to sort.\n\n");
                    }
                    else
                    {
                        students = new SortMethods<Student>().radixSortDescendingId(students);
                    }
                }
                else if (sel == 2)
                {
                    if (faculty.isEmpty())
                    {
                        System.out.printf("No faculty entered in database, no data to sort.\n\n");
                    }
                    else
                    {
                        faculty = new SortMethods<Faculty>().radixSortDescendingId(faculty);
                    }
                }
            }
            else if (sel == 6)
            {
                //gpa
                //new SortMethods<Student>().bubbleSortComparator(students, new Comparator<Student>(){
                new SortMethods<Student>().mergeSortAscendingGpa(students, new Comparator<Student>(){
                    @Override
                    public int compare(Student o1, Student o2) {
                        if (o1.getGPA() == o2.getGPA())
                        {
                            return(0);
                        }
                        else if (o1.getGPA() < o2.getGPA())
                        {
                            return(-1);
                        }
                        else
                        {
                            return(1);
                        }
                    }
                });
            }
            else if (sel == 7)
            {
                //course load
                new SortMethods<Faculty>().insertionSortAscendingCouseLoad(faculty, (Comparator<Faculty>) (Faculty o1, Faculty o2) -> {
                    if (o1.getCourseLoad() == o2.getCourseLoad())
                    {
                        return(0);
                    }
                    else if (o1.getCourseLoad() < o2.getCourseLoad())
                    {
                        return(-1);
                    }
                    else
                    {
                        return(1);
                    }
                });
            }
            else if (sel == 8)
            {
                System.out.printf("\nStudent Data: (Name: ID GPA)\n%s\n\nFaculty Data: (Name: ID Courseload)\n%s\n\n", students, faculty);
            }
            else if (sel == -1)
            {
                faculty.add(new Faculty("Omega", 2, 1));
                faculty.add(new Faculty("Gamma", 20, 3));
                faculty.add(new Faculty("Alpha", 12, 8));
                faculty.add(new Faculty("Beta", 50, 6));
                faculty.add(new Faculty("Beta", 30, 19));
                faculty.add(new Faculty("Beta", 49, 15));
                faculty.add(new Faculty("Beta", 48, 20));
                faculty.add(new Faculty("Beta", 33, 11));
                faculty.add(new Faculty("Beta", 38, 32));
                faculty.add(new Faculty("Beta", 35, 23));
                
                students.add(new Student("Omega", 56, 8));
                students.add(new Student("Gamma", 19, 6));
                students.add(new Student("Gamma", 24, 13.4));
                students.add(new Student("Alpha", 92, 3.2));
                students.add(new Student("Beta", 54, 22.7));
                students.add(new Student("Omega", 36, 18.6));
                students.add(new Student("Gamma", 31, 39.1));
                students.add(new Student("Gamma", 77, 42.9));
                students.add(new Student("Alpha", 22, 33.2));
                students.add(new Student("Beta", 43, 53.8));
            }
        }
    }
    
}