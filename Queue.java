//Name: Donovan Troshynski
//Class: 1620-002
//Program #: 6
//Due Date: 10 November 2015
//Honor Pledge: On my honor as a student of the University of Nebraska at Omaha,I have neither given nor received unauthorized help on this homework assignment.
//NAME: Donovan Troshynski


//Partners: NONE
//This assignment will implement everything from the previous assignment along with an employee manager system which the user can operate to add, delete, or alter different types of employees.

package dataStructures;

import dataStructures.LinkedList;

public class Queue<E extends Comparable<E>>
{

    private LinkedList<E> list;

    //Method Name: Queue
    //Parameters: none
    //Return value(s): none
    //Partners: none
    //This is the default constructor for the Queue class
    public Queue()
    {
        list = new LinkedList<E>();
    }

    //Method Name: Queue
    //Parameters: String name
    //Return value(s): none
    //Partners: none
    //This is a constructor for the Queue class that accepts a name for the list
    public Queue(String name)
    {
        list = new LinkedList<E>(name);
    }

    //Method Name: enqueue
    //Parameters: E item
    //Return value(s): none
    //Partners: none
    //Adds the item to the back of the queue
    public void enqueue(E item)
    {
        list.insertAtBack(item);
    }

    //Method Name: dequeue
    //Parameters: none
    //Return value(s): E
    //Partners: None
    //Removes the item from the front of the queue and returns that item
    public E dequeue()
    {
        E removed = list.getItem(0);
        list.removeFromFront();
        return removed;
    }

    //Method Name: lengthIs
    //Parameters: none
    //Return value(s): int
    //Partners: none
    //Returns the length of the queue
    public int lengthIs()
    {
        return list.lengthIs();
    }

    //Method Name: peek
    //Parameters: none
    //Return value(s): E
    //Partners: none
    //Returns the item at the front of the queue
    public E peek()
    {
        return list.getItem(0);
    }

    //Method Name: toString
    //Parameters: none
    //Return value(s): String
    //Partners: none
    //Returns a string of all the items in the queue
    public String toString()
    {
        return list.toString();
    }

    //Method Name: isEmpty
    //Parameters: none
    //Return value(s): boolean
    //Partners: none
    //Returns true if the queue is empty, false if not
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    //Method Name: clear
    //Parameters: none
    //Return value(s): none
    //Partners: none
    //Clears the queue of all items
    public void clear()
    {
        list.clear();
    }
}
