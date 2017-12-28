//Name: Donovan Troshynski
//Class: 1620-002
//Program #: 6
//Due Date: 10 November 2015
//Honor Pledge: On my honor as a student of the University of Nebraska at Omaha,I have neither given nor received unauthorized help on this homework assignment.
//NAME: Donovan Troshynski


//Partners: NONE
//This assignment will implement everything from the previous assignment along with an employee manager system which the user can operate to add, delete, or alter different types of employees.

package dataStructures;

import exceptions.InvalidSizeException;
import exceptions.MaximumCapacityException;

import employeeType.employee.Employee;

import employeeType.subTypes.HourlyEmployee;
import employeeType.subTypes.SalaryEmployee;
import employeeType.subTypes.CommissionEmployee;

@SuppressWarnings("unchecked")
public class ArrayList<E extends Comparable<E>>
{
    private final int defCap = 50;
    private int origCap;
    private int numElements;
    private Object[] list;

    //Method Name: ArrayList
    //Parameters: none
    //Return value(s): none
    //Partners: none
    //This is the default constructor for the ArrayList class; sets the size of the array to defCap
    public ArrayList()
    {
        origCap = defCap;
        numElements = 0;
        list = (E[]) new Object[origCap];
    }

    //Method Name: ArrayList
    //Parameters: int size
    //Return value(s): none
    //Partners: none
    //This is the constructor for the ArrayList class when an initial size is given; validates the size and sets the size of the array
    public ArrayList(int size) throws InvalidSizeException
    {
        numElements = 0;
        if (size <= defCap)
        {
            origCap = size;
            list = new Object[origCap];
        }
        else
            throw new InvalidSizeException();
    }

    //Method Name: addItem
    //Parameters: E item
    //Return value(s): none
    //Partners: none
    //This method adds an item to the array, defined by the generic type E, which is set to mean type Employee
    public void addItem(E item) throws MaximumCapacityException
    {
        if (numElements == 10)
            throw new MaximumCapacityException();
        if (numElements == origCap)
        {
            enlarge();
            origCap = (2 * origCap);
        }
        list[numElements++] = (E) item;
    }

    //Method Name: getItem
    //Parameters: int index
    //Return value(s): E
    //Partners: none
    //This method returns the item at the given index
    public E getItem(int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index > numElements)
            throw new IndexOutOfBoundsException("Index out of range.");
        else
            return (E)list[index];
    }

    //Method Name: setItem
    //Parameters: int index, E item
    //Return value(s): none
    //Partners: none
    //This method sets the passed item to the place in the array at the given index
    public void setItem(int index, E item) throws IndexOutOfBoundsException
    {
        if (index < 0 || index > numElements)
            throw new IndexOutOfBoundsException("Index out of range.");
        else
            list[index] = item;
    }

    //Method Name: removeItem
    //Parameters: int index
    //Return value(s): E
    //Partners: none
    //Removes the item at the given index value and returns the array
    public E removeItem(int index) throws IndexOutOfBoundsException
    {
        E x = null;
        if (index < 0 || index > numElements)
            throw new IndexOutOfBoundsException("Index out of range.");
        else
        {
            x = (E) list[numElements - 1];
            list[numElements - 1] = list[index];
            list[index] = x;
            numElements--;
            return (E)list[index];
        }
    }

    //Method Name: findItem
    //Parameters: E item
    //Return value(s): int
    //Partners: none
    //Returns the spot it the array that contains the passed item
    public int findItem(E item)
    {
        for (int x = 0; x < numElements; x++)
        {
            if (list[x] == item)
                return x;
        }
        return -1;
    }

    //Method Name: isEmpty
    //Parameters: none
    //Return value(s): boolean
    //Partners: none
    //Returns true if the array is empty, false otherwise
    public boolean isEmpty()
    {
        if (numElements == 0)
            return true;
        else
            return false;
    }

    //Method Name: lengthIs
    //Parameters: none
    //Return value(s): int
    //Partners: none
    //Returns the number of elements in the array
    public int lengthIs()
    {
        return numElements;
    }

    //Method Name: clear
    //Parameters: none
    //Return value(s): none
    //Partners: none
    //Clears the array of all items by making a new array and assigning the old array to the new one, sets numElements to 0
    public void clear()
    {
        if (numElements > origCap)
        {
            Object[] newerList = new Object[origCap];
            list = newerList;
        }
        numElements = 0;
    }

    //Method Name: toString
    //Parameters: none
    //Return value(s): String
    //Partners: none
    //Prints out the items in the array
    public String toString()
    {
        for (int x = 0; x < numElements; x++)
        {
            System.out.println();
            System.out.println(list[x].toString());
        }
        return "";
    }

    //Method Name: sort
    //Parameters: none
    //Return value(s): none
    //Partners: none
    //Sorts the items in the array using an insertion sort
    public void sort()
    {
        E insert;
        for (int i = 1; i < numElements; i++)
        {
            insert = (E) list[i];
            int hold = i - 1;

            while (hold > -1 && ( ((Comparable)list[hold]).compareTo(insert) > 0))
            {
                list[hold + 1] = list[hold];
                hold--;
            }
            list[hold + 1] = (E) insert;

        }
    }

    //Method Name: enlarge
    //Parameters: none
    //Return value(s): none
    //Partners: none
    //Enlarges the array by the origCap plus the current length of the array
    private void enlarge() throws MaximumCapacityException
    {
        if ((list.length + origCap) > defCap)
            throw new MaximumCapacityException();
        Object[] larger = new Object[list.length + origCap];
        for (int x = 0; x < list.length; x++)
            larger[x] = list[x];
        list = larger;
    }

}
