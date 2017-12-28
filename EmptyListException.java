//Name: Donovan Troshynski
//Class: 1620-002
//Program #: 6
//Due Date: 10 November 2015
//Honor Pledge: On my honor as a student of the University of Nebraska at Omaha,I have neither given nor received unauthorized help on this homework assignment.
//NAME: Donovan Troshynski


//Partners: NONE
//This assignment will implement everything from the first assignment with the addition of an employee manager system, with which the user can add, delete, or alter employees however he/she wishes. It will make use of abstract classes and methods along with polymorphism and downcasting.

package exceptions;

public class EmptyListException extends RuntimeException
{
    public EmptyListException()
    {
        this("List");
    }

    public EmptyListException(String name)
    {
        super(name + " is empty");
    }
}
