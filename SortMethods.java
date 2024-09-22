package project_sortingalgorithms;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Arrays;
import java.lang.Math;

/**
 *
 * @author hannahgsimon
 */

public class SortMethods<T extends Comparable & SortProjectData>
{

    /*public void bubbleSortName(ArrayList<T> data) {
        boolean keepgoing = true;
        for (int i = 0; i < data.size() && keepgoing; ++i) {
            keepgoing = false;
            for (int j = 0; j < data.size() - 1; ++j) {
                if (data.get(j).getName().compareTo(data.get(j + 1).getName()) > 0) {
                    keepgoing = true;
                    T tmp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, tmp);
                }
            }
        }
    }*/
    
    public ArrayList<T> radixSortDescendingId(ArrayList<T> data)
    {
	//int max = ((T) Collections.max(data)).getID();
        //Only works on positive numbers
        int max = data.get(0).getID();
        for (int i = 0; i < data.size() - 1; i++)
        {
            if (data.get(i).getID() > data.get(i + 1).getID())
            {
                max = data.get(i).getID();
            }
        }
	boolean keepgoing = true; int maxbase = 0;
	while (keepgoing)
	{            
	    if (max / (int) Math.pow(10, maxbase) == 0)
            {
                keepgoing = false;
            }
            else
            {
                maxbase++;
            }
	}
        maxbase--;

        int[] countArray = new int[10];
        int[] digits = new int[data.size()];
        //int[] auxillaryArray = new int[data.size()];
        ArrayList<T> auxillaryArray = (ArrayList<T>) data.clone();

	for (int i = 0; i <= maxbase; i++)
	{
	    for (int j = data.size() - 1; j >= 0; j--)
            {
                digits[j] = (data.get(j).getID() / (int) Math.pow(10, i)) % 10;   
                //int k = (data.get(j).getID() / (int) Math.pow(10, i)) % 10;              
                (countArray[digits[j]])++;
                
                if (j == 0)
                {
                    //System.out.println("Digits: " + Arrays.toString(digits));
                    (countArray[0])--;
                    for (int m = 1; m < countArray.length; m++)
                    {
                        countArray[m] += countArray[m - 1];
                    }
                    //System.out.println("Count Array before decrements: " + Arrays.toString(countArray));
                    for (int n = data.size() - 1; n >= 0; n--)
                    {
                        //int k = (data.get(j).getID() / (int) Math.pow(10, i)) % 10;
                        //auxillaryArray[countArray[digits[n]]--] = data.get(n).getID();
                        auxillaryArray.set(countArray[digits[n]]--, data.get(n)); //can't modify data itself of it'll get overwritten
                    }
                    //System.out.println("Count Array after decrements: " + Arrays.toString(countArray));
                    //System.out.println("Auxillary Array: " + Arrays.toString(auxillaryArray));
                    countArray = new int[10]; //resets count array to all zeroes
                    data = (ArrayList<T>) auxillaryArray.clone();
                }      
            }
	}
        return data; //Must be returned because when clone, changes memory location in heap
    } 

    /*public void bubbleSortID(ArrayList<T> data) {
        boolean keepgoing = true;
        for (int i = 0; i < data.size() && keepgoing; ++i) {
            keepgoing = false;
            for (int j = 0; j < data.size() - 1; ++j) {
                if (data.get(j).compareTo(data.get(j + 1)) > 0) {
                    keepgoing = true;
                    T tmp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, tmp);
                }
            }
        }
    }*/
    
    public void selectionSortAscendingId(ArrayList<T> data)
    {
        for (int j = data.size() - 1; j > 0; j--) //j > 0 instead of j != 0 because prevents error when sorting 0 elements
        {
            int i; int tmp = 0;
            for (i = 1; i <= j; i++)
            {
                if (data.get(tmp).compareTo(data.get(i)) < 0)
                {
                    tmp = i;
                }
            }
            Collections.swap(data, tmp, j);
        }
        /*Alternate Method
        int j = data.size() - 1; boolean keepgoing = true;
        while (keepgoing)
        {
            int i; int tmp = 0;
            for (i = 1; i <= j; i++)
            {
                if (data.get(tmp).compareTo(data.get(i)) < 0)
                {
                    tmp = i;
                }
            }
            Collections.swap(data, tmp, j--);
            if (j == 0)
            {
                keepgoing = false;
            }
        }*/
    }

    /*public void bubbleSortComparator(ArrayList<T> data, Comparator compar) {
        boolean keepgoing = true;
        for (int i = 0; i < data.size() && keepgoing; ++i) {
            keepgoing = false;
            for (int j = 0; j < data.size() - 1; ++j) {
                if (compar.compare(data.get(j), data.get(j + 1)) > 0) {
                    keepgoing = true;
                    T tmp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, tmp);
                }
            }
        }
    }*/
    
    public void insertionSortAscendingCouseLoad (ArrayList<T> data, Comparator compar)
    {
        int count = 1;
        for (int i = 1; i < data.size(); i++)
        {
            if (compar.compare(data.get(i), data.get(i - 1)) < 0)
            {
                 Collections.swap(data, i, i - 1);
                 if ( i >= 2)
                 {
                     i -= 2;
                 }
                 else //i = 1
                 {
                     count++;
                 }
            }
            else
            {
                i = count++;
            }
        }
    }
    
    public void mergeSortAscendingGpa (ArrayList<T> data, Comparator compar)
    {
        if (data.size() > 1)
        {
            mergeSortSplit(data, 0, data.size() - 1, compar);
        }
    }
    
    public void mergeSortSplit (ArrayList<T> data, int left, int right, Comparator compar)
    {
        int midpoint = (right - left)/2 + left;
        if (left != right)
        {
            mergeSortSplit(data, left, midpoint, compar);
            mergeSortSplit(data, midpoint + 1, right, compar);
            mergeSortMerge (data, left, right, compar, midpoint); //Never get to this line if left = right
        }        
    }
    
    public void mergeSortMerge (ArrayList<T> data, int left, int right, Comparator compar, int midpoint)
    {
         ArrayList<T> tmp = (ArrayList<T>)data.clone();
         int origMidpoint = midpoint;
         int i = left;
         while (midpoint + 1 <= right && left <= origMidpoint)
        {
            if (compar.compare(tmp.get(left), tmp.get(midpoint + 1)) <= 0)
            {
                data.set(i, tmp.get(left));
                left++;
            }
            else
            {
                data.set(i, tmp.get(midpoint + 1));
                midpoint++;
            }
            i++;
         }
         for (int j = i; j <= right; j++)
         {
             if (midpoint + 1 > right)
             {
                 data.set(j, tmp.get(left));
                 left++;
             }
             else
             {
                 data.set(j, tmp.get(midpoint + 1));
                 midpoint++;
             }           
         }  

/* Scrapped Method that works, but is inefficient because of extra unecessary checks
        for (left = left; left < midpoint + 1 && midpoint != data.size() - 1; left++)
        {
            if (compar.compare(data.get(left), data.get(midpoint + 1)) > 0)
            {
                Collections.swap(data, left, midpoint + 1);
            }
        }
        for (midpoint = midpoint; midpoint < right; midpoint++)
        {
           if (compar.compare(data.get(midpoint), data.get(right)) > 0)
            {
                Collections.swap(data, midpoint, right);
            } 
        }
        */
    }  

}
