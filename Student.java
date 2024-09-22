package project_sortingalgorithms;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author hannahgsimon
 */

public class Student implements Comparable<Student>, SortProjectData
{

    private double gpa;
    private int id;
    private String name;
    
    public Student(Scanner input)
    {
        System.out.println("\nEnter Name");
        name = input.next();
        
        System.out.println("\nEnter id");
        id = 0;
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
                    System.out.println("id " + id + " has successfully been added to student " + name + "\n");
                    gotCorrect = true;
                }
            }
            catch (InputMismatchException e) {
                System.err.println("Error; Please enter an integer number.");
                input.next();
            }
        }
        input.skip(".*");            
        
        System.out.println("Enter GPA");
        gpa = 0;
        gotCorrect = false;
        while (!gotCorrect) {
            try
            {
                gpa = input.nextDouble();
                if (gpa < 0)
                {
                    System.err.println("Error; Please enter a positive integer or decimal number.");
                }
                else
                {
                    System.out.printf("GPA " + gpa + " has successfully been added to student " + name + "\n\n");
                    gotCorrect = true;
                }
            }
            catch (InputMismatchException e)
            {
                System.err.println("Error; Please enter an integer or decimal number.");
                input.next();
            }
        }
        input.skip(".*");
    }
    
    public Student(String name, int id, double gpa)
    {
        this.name =  name;
        this.gpa = gpa;
        this.id = id;
    }
    
    public double getGPA()
    {
        return(gpa);
    }
    
//    public void setGPA(double gpa)
//    {
//        this.gpa = gpa;
//    }

    public int getID()
    {
	    return(id);
    }
   
    @Override
    public int compareTo(Student o)
    {
        if (id == o.id)
        {
            return(0);
        }
        else if (id < o.id)
        {
            return(-1);
        }
        else
        {
            return(1);
        }
    }
    
    @Override
    public String toString()
    {
        return(String.format("{%s: %d %.2f}", name, id, gpa));
    }

    @Override
    public String getName()
    {
        return(name);
    }
    
}
