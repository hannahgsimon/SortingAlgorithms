package project_sortingalgorithms;

/**
 *
 * @author hannahgsimon
 */

public class Faculty implements Comparable<Faculty>, SortProjectData
{
    private int id, courseLoad;
    private String name;
    
    public int getCourseLoad()
    {
        return(courseLoad);
    }

    public int getID()
    {
	    return(id);
    }
    
    public Faculty(String name, int id, int load)
    {
        this.name = name;
        this.id = id;
        courseLoad = load;
    }
    
    @Override
    public int compareTo(Faculty o)
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
        return(String.format("{%s: %d %d}", name, id, courseLoad));
    }

    @Override
    public String getName()
    {
        return(name);
    }
}
