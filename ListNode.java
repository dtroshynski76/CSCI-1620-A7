//Name: Donovan Troshynski
//Class: 1620-002
//Program #: 6
//Due Date: 10 November 2015
//Honor Pledge: On my honor as a student of the University of Nebraska at Omaha,I have neither given nor received unauthorized help on this homework assignment.
//NAME: Donovan Troshynski


//Partners: NONE
//This assignment will implement everything from the first assignment with the addition of an employee manager system, with which the user can add, delete, or alter employees however he/she wishes. It will make use of abstract classes and methods along with polymorphism and downcasting.

package dataStructures;

public class ListNode<E extends Comparable<E>>
{
    private E data;
    private ListNode<E> nextNode;

    public ListNode(E d)
    {
        nextNode = null;
        data = d;
    }

    public ListNode(E d, ListNode<E> node)
    {
        nextNode = node;
        data = d;
    }

    //Method Name: setData
    //Parameters: E d
    //Return value(s): none
    //Partners: None
    //Sets the data to the passed item
     public void setData(E d)
    {
        data = d;
    }

    //Method Name: getData
    //Parameters: none
    //Return value(s): E
    //Partners: None
    //Returns the data
    public E getData()
    {
        return data;
    }

    //Method Name: setNext
    //Parameters: ListNode<E> next
    //Return value(s): none
    //Partners: None
    //Sets the next node in the linked list to the passed value
    public void setNext(ListNode<E> next)
    {
        nextNode = next;
    }

    //Method Name: getNext
    //Parameters: None
    //Return value(s): ListNode<E>
    //Partners: None
    //Returns the next node
    public ListNode<E> getNext()
    {
        return nextNode;
    }
}
