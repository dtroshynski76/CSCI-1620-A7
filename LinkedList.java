//Name: Donovan Troshynski
//Class: 1620-002
//Program #: 6
//Due Date: 10 November 2015
//Honor Pledge: On my honor as a student of the University of Nebraska at Omaha,I have neither given nor received unauthorized help on this homework assignment.
//NAME: Donovan Troshynski


//Partners: NONE
//This assignment will implement everything from the first assignment with the addition of an employee manager system, with which the user can add, delete, or alter employees however he/she wishes. It will make use of abstract classes and methods along with polymorphism and downcasting.

package dataStructures;

import exceptions.EmptyListException;

import dataStructures.ListNode;

@SuppressWarnings("unchecked")
public class LinkedList<E extends Comparable<E>>
{
    private ListNode<E> firstNode;
    private ListNode<E> lastNode;
    private int numElements;
    private String name;

    public LinkedList()
    {
        this("list");
    }

    public LinkedList(String name)
    {
        firstNode = null;
        lastNode = null;
        numElements = 0;
        this.name = name;
    }

    //Method Name: insertAtFront
    //Parameters: E item
    //Return value(s): none
    //Partners: None
    //Inserts the passed item at the front of the linked list
    public void insertAtFront(E item)
    {
        ListNode<E> newNode = new ListNode<E>(item);

        if (isEmpty())
        {
            firstNode = newNode;
            lastNode = newNode;
        }
        else
        {
            newNode.setNext(firstNode);
            firstNode = newNode;
        }
        numElements++;
    }

    //Method Name: insertAtBack
    //Parameters: E item
    //Return value(s): none
    //Partners: None
    //Inserts the passed item at the back of the linked list
    public void insertAtBack(E item)
    {
        ListNode<E> newNode = new ListNode<E>(item);
        
        if (isEmpty())
        {
            firstNode = newNode;
            lastNode = newNode;
        }
        else
        {
            lastNode.setNext(newNode);
            lastNode = newNode;
        }
        numElements++;
    }

    //Method Name: removeFromFront
    //Parameters: none
    //Return value(s): E
    //Partners: None
    //Removes the item at the front of the Linked List (index value of 0)
    public E removeFromFront() throws EmptyListException
    {
        E data = null;

        if (isEmpty())
            throw new EmptyListException(name);
        else
        {
            data = (E) firstNode.getData();

            if (firstNode == lastNode)
            {
                firstNode = null;
                lastNode = null;
            }
            else
            {
                firstNode = firstNode.getNext();
            }

            numElements--;

            return data;
        }
        
    }

    //Method Name: removeFromBack
    //Parameters: none
    //Return value(s): E
    //Partners: None
    //Removes the item at the back of the linked list
    public E removeFromBack() throws EmptyListException
    {
        if (isEmpty())
            throw new EmptyListException(name);

        E data = null;

        data = (E) lastNode.getData();

        if (firstNode == lastNode)
        {
           firstNode = null;
           lastNode = null;
           numElements--;

           return data;
        }

        ListNode<E> currentNode = firstNode, prevNode = null;

        for (ListNode<E> e = firstNode; e != null; e = e.getNext())
        {
            prevNode = currentNode;
            currentNode = e;
        }

        prevNode.setNext(null);
        lastNode = prevNode;

        numElements--;

        return data;
    }

    //Method Name: removeItem
    //Parameters: int index
    //Return value(s): E
    //Partners: None
    //Removes the item that exists at the passed index value
    public E removeItem(int index) throws IndexOutOfBoundsException
    {
        E data;

        if (index < 0 || index > (numElements - 1))
            throw new IndexOutOfBoundsException(name + " Index out of Range");
        else
        {
            if (index == 0)
            {
                //remove firstNode
                return removeFromFront();
            }

            if (index == (numElements - 1))
            {
                //remove lastNode
                return removeFromBack();
            }

            ListNode<E> currentNode = null;
            ListNode<E> prev = null;
            int cnt = 0;

            for(ListNode<E> curr = firstNode; curr != lastNode; curr = curr.getNext())
            {
                if (cnt == (index - 1))
                    prev = curr;
                if (index == cnt)
                {
                    currentNode = curr;
                }
                cnt++;
            }

            data = currentNode.getData();
            prev.setNext(currentNode.getNext());
        }
        numElements--;
        return data;
    }

    //Method Name: getItem
    //Parameters: int index
    //Return value(s): E
    //Partners: None
    //Returns the data in the item at the given index
    public E getItem(int index) throws IndexOutOfBoundsException
    {
        E data = null;

        if (index < 0 || index > (numElements - 1))
            throw new IndexOutOfBoundsException(name + " Index out of Range");
        else
        {
            int cnt = 0;
            for (ListNode<E> curr = firstNode; curr != null; curr = curr.getNext())
            {
                if (index == cnt)
                    data = curr.getData();
                cnt++;
            }
        }

        return data;
    }

    //Method Name: setItem
    //Parameters: int index, E item
    //Return value(s): none
    //Partners: None
    //Sets the value at the given index to the value of the passed item
    public void setItem(int index, E item) throws IndexOutOfBoundsException
    {
        if (index < 0 || index > (numElements - 1))
            throw new IndexOutOfBoundsException(name + " Index out of Range");
        else
        {
            int cnt = 0;
            for (ListNode<E> curr = firstNode; curr != null; curr = curr.getNext())
            {
                if (index == cnt)
                    curr.setData(item);
                cnt++;
            }
        }
    }

    //Method Name: findAndRemove
    //Parameters: E item
    //Return value(s): boolean
    //Partners: None
    //Finds and removes the item from the linked list
    public boolean findAndRemove(E item)
    {
        int find = findItem(item);
        if (find < 0)
            return false;

        removeItem(find);
        return true;
    }

    //Method Name: findItem
    //Parameters: E item
    //Return value(s): int
    //Partners: None
    //Finds the passed item in the linked list and returns its index value
    public int findItem(E item)
    {
        int cnt = 0;
        for (ListNode<E> curr = firstNode; curr != null; curr = curr.getNext())
        {
            if (item.compareTo(curr.getData()) == 0)
            {
                return cnt;
            }
            cnt++;
        }
        return -1;
    }

    //Method Name: lengthIs
    //Parameters: none
    //Return value(s): int
    //Partners: None
    //Returns the number of elements in the linked list
    public int lengthIs()
    {
        return numElements;
    }

    //Method Name: clear
    //Parameters: none
    //Return value(s): none
    //Partners: None
    //Clears the linked list of all values
    public void clear()
    {
        numElements = 0;
        firstNode = null;
        lastNode = null;
    }

    //Method Name: toString
    //Parameters: None
    //Return value(s): String
    //Partners: None
    //Returns all the data in the linked list
    public String toString()
    {
        String output = "";
        for( ListNode<E> e = firstNode; e != null; e = e.getNext() )
        {
             output += e.getData() + "\n\n";
        }
        return output;
    }

    //Method Name: isEmpty
    //Parameters: None
    //Return value(s): boolean
    //Partners: None
    //Returns true if the linked list is empty, false if it isn't
    public boolean isEmpty()
    {
        if (numElements == 0)
            return true;
        else
            return false;
    }

    //Method Name: sort
    //Parameters: none
    //Return value(s): none
    //Partners: None
    //Sorts the linked list using a selection sort
    public void sort() throws EmptyListException
    {
        if (isEmpty())
            throw new EmptyListException(name);
        if (firstNode == lastNode)
            return;

        ListNode<E> temp = null;

        for (ListNode<E> curr = firstNode; curr != null; curr = curr.getNext())
        {
            ListNode<E> smallest = curr;

            for(ListNode<E> x = curr.getNext(); x != null; x = x.getNext())
            {
                if (x.getData().compareTo(smallest.getData()) < 0)
                    smallest = x;
            }
            
            E y = curr.getData();
            curr.setData(smallest.getData());
            smallest.setData(y);
        }
    }
}
