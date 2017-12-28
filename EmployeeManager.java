//Name: Donovan Troshynski
//Class: 1620-002
//Program #: 6
//Due Date: 10 November 2015
//Honor Pledge: On my honor as a student of the University of Nebraska at Omaha,I have neither given nor received unauthorized help on this homework assignment.
//NAME: Donovan Troshynski


//Partners: NONE
//This assignment will implement everything from the previous assignment along with an employee manager system which the user can operate to add, delete, or alter different types of employees.

import employeeType.employee.Employee;

import employeeType.subTypes.HourlyEmployee;
import employeeType.subTypes.SalaryEmployee;
import employeeType.subTypes.CommissionEmployee;

import dataStructures.ArrayList;
import dataStructures.LinkedList;
import dataStructures.Queue;

import exceptions.InvalidCharacterException;
import exceptions.InvalidEmployeeNumberException;
import exceptions.MaximumCapacityException;
import exceptions.InvalidSizeException;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;
import java.lang.SecurityException;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;

import java.util.Scanner;
import java.util.Formatter;

import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;

import java.lang.Math;

@SuppressWarnings("unchecked")
public class EmployeeManager
{
    private ArrayList<Employee> employees;
    private final int employeeMax = 10;
    LinkedList<Employee> hourlyList;
    LinkedList<Employee> salaryList;
    LinkedList<Employee> commissionList;
    Queue<Employee> vacationRequests;
    
    public EmployeeManager()
    {
        try
        {
            employees = new ArrayList<Employee>(3);
        }
        catch (InvalidSizeException ISE)
        {
            employees = new ArrayList<Employee>();
        }

        hourlyList = new LinkedList<Employee>("Hourly List");
        salaryList = new LinkedList<Employee>("Salary List");
        commissionList = new LinkedList<Employee>("Commission List");
        vacationRequests = new Queue<Employee>("Vacation Requests");
    }
    
    //Method Name: addEmployee
    //Parameters: int type; String fn, ln; char m, g; int en; boolean ft; double amount
    //Return value(s): none
    //Partners: None
    //Receives input on what type of employee to add (hourly, salary, or commission) and then prompts for the required data included in that employee type. Doesn't add an employee if one of these three conditions are met: one of the required data fields is not passed, an employee with the given employee number already exists, or if the array is at maximum capacity.
    public void addEmployee(int type, String fn, String ln, char m, char g, int en, boolean ft, double amount) throws InvalidEmployeeNumberException
    {
        //Doesn't add an employee if any of the required data fields is not passed
        boolean inputError = false;

        if (type <= 0 || type > 3)
            inputError = true;
        if (fn.length() == 0)
            inputError = true;
        if (ln.length() == 0)
            inputError = true;
        if (Character.toString(m).length() == 0)
            inputError = true;
        if (Character.toString(g).length() == 0)
            inputError = true;
        if (en <= 0)
            inputError = true;
        if (amount <= 0)
            inputError = true;

        if (inputError == true)
        {
            System.out.println("Invalid Employee Type, None Added.");
            return;
        }

        //Doesn't add an employee if the given employee number is equal to an existing employee number
        Employee equalEmpnum;
        int findEmpnum;
        for ( int y = 0; y < employees.lengthIs(); y++)
        {
            equalEmpnum = employees.getItem(y);
            findEmpnum = equalEmpnum.getEmployeeNumber();

            if (findEmpnum == en)
            {
                System.out.println();
                System.out.println("Duplicate Not Added.");
                return;
            }   
        }

        //creates hourly employee
        if (type == 1)
        {
            HourlyEmployee hourlyEmployee = new HourlyEmployee(fn, ln, m, g, en, ft, amount);
            try
            {
                employees.addItem(hourlyEmployee);
                hourlyList.insertAtBack(hourlyEmployee);
            }
            catch (MaximumCapacityException MCE)
            {
                System.out.println();
                System.out.println("Cannot add more employees.");
                return;
            }
        }

        //creates salary employee
        if (type == 2)
        {
            SalaryEmployee salaryEmployee = new SalaryEmployee(fn, ln, m, g, en, ft, amount);
            try
            {
                employees.addItem(salaryEmployee);
                salaryList.insertAtBack(salaryEmployee);
            }
            catch (MaximumCapacityException MC)
            {
                System.out.println();
                System.out.println("Cannot add more employees.");
                return;
            }
        }

        //creates commission employee
        if (type == 3)
        {
            CommissionEmployee commissionEmployee = new CommissionEmployee(fn, ln, m, g, en, ft, amount);
            try
            {
                employees.addItem(commissionEmployee);
                commissionList.insertAtBack(commissionEmployee);
            }
            catch (MaximumCapacityException M)
            {
                System.out.println();
                System.out.println("Cannot add more employees.");
                return;
            }
        }
    }

    //Method Name: removeEmployee
    //Parameters: int index
    //Return value(s): none
    //Partners: None
    //Removes the employee at the given index value.
    public void removeEmployee(int index)
    {
        if (index == -1)
        {
            System.out.println();
            System.out.println("Can't remove nonexistent employee.");
            return;
        }

        if (employees.getItem(index) instanceof HourlyEmployee)
            hourlyList.findAndRemove(employees.getItem(index));
        if (employees.getItem(index) instanceof SalaryEmployee)
            salaryList.findAndRemove(employees.getItem(index));
        if (employees.getItem(index) instanceof CommissionEmployee)
            commissionList.findAndRemove(employees.getItem(index));
        employees.removeItem(index);
    }

    //Method Name: listAll
    //Parameters: none
    //Return value(s): none
    //Partners: None
    //Iterates through the array of employees and prints all the information about those employees, outputting "No Employees" if there are no employees to print.
    public void listAll()
    {
        if (employees.lengthIs() == 0)
        {
            System.out.println("No Employees");
            System.out.println();
        }
        else
            employees.toString();
    }

    //Method Name: listHourly
    //Parameters: none
    //Return value(s): none
    //Partners: None
    //Goes through the array and lists all instances of HourlyEmployees, outputting "There are none." if there are no HourlyEmployees in the array
    public void listHourly()
    {
        for (int x = 0; x < hourlyList.lengthIs(); x++)
        {
            System.out.println();
            System.out.println(hourlyList.getItem(x));
            System.out.println();
        }
    }

    //Method Name: listSalary
    //Parameters: none
    //Return value(s): none
    //Partners: None
    //Iterates through the array and lists all instances of SalaryEmployees, outputting "There are none." if there are no SalaryEmployees in the array
    public void listSalary()
    {
        for (int x = 0; x < salaryList.lengthIs(); x++)
        {
            System.out.println();
            System.out.println(salaryList.getItem(x));
            System.out.println();
        }
   }

    //Method Name: listCommission
    //Parameters: none
    //Return value(s): none
    //Partners: None
    //Iterates through the array and lists all instances of CommissionEmployees, outputting "There are none." if there are no CommissionEmployees in the array
    public void listCommission()
    {
        for (int x = 0; x < commissionList.lengthIs(); x++)
        {
            System.out.println();
            System.out.println(commissionList.getItem(x));
            System.out.println();
        }
   }

    //Method Name: resetWeek
    //Parameters: none
    //Return value(s): none
    //Partners: None
    //Resets the week for each employee in the array
    public void resetWeek()
    {
        for (int k = 0; k < employees.lengthIs(); k++)
        {
            employees.getItem(k).resetWeek();
        }
    }

    //Method Name: calculatePayout
    //Parameters: none
    //Return value(s): double
    //Partners: None
    //Calculates the weekly pay for each employee in the array
    public double calculatePayout()
    {
        double totalPay = 0;
        for (int x = 0; x < employees.lengthIs(); x++)
        {
            totalPay = totalPay + employees.getItem(x).calculateWeeklyPay();
        }
        return totalPay;
    }

    //Method Name: getIndex
    //Parameters: int empNum
    //Return value(s): int
    //Partners: None
    //Returns the index value of the employee whose employee number equals the given employee number, returning -1 if the employee doesn't exist
    public int getIndex(int empNum)
    {
        Employee lookEmployee;
        int lookEmpnum;        

        if (employees.lengthIs() == 0)
        {
            return -1;
        }
        for ( int x = 0; x < employees.lengthIs(); x++ )
        {
            lookEmployee = employees.getItem(x);

            lookEmpnum = lookEmployee.getEmployeeNumber();
            if ( lookEmpnum == empNum )
                return x;
        }
        return -1;
    }

    //Method Name: annualRaises
    //Parameters: none
    //Return value(s): none
    //Partners: None
    //Gives each employee their annual raises
    public void annualRaises()
    {
        for (int t = 0; t < employees.lengthIs(); t++)
        {
            employees.getItem(t).annualRaise();
        }
    }

    //Method Name: holidayBonuses
    //Parameters: none
    //Return value(s): double
    //Partners: None
    //Applies holiday bonuses to each employee
    public double holidayBonuses()
    {
        double holidayTotal = 0;
        for (int x = 0; x < employees.lengthIs(); x++)
        {
            System.out.printf("%n%sHoliday Bonus: %.2f%n%n", employees.getItem(x).toString(), employees.getItem(x).holidayBonus());
            holidayTotal = holidayTotal + employees.getItem(x).holidayBonus();
        }
        return holidayTotal;
    }

    //Method Name: increaseHours
    //Parameters: int index; double amount
    //Return value(s): none
    //Partners: None
    //Increases the hours worked for the employee at the given index value
    public void increaseHours(int index, double amount)
    {
        Employee d = employees.getItem(index);
        boolean hourly = false;
        if (d instanceof HourlyEmployee)
        {
            hourly = true;
        }
        if (hourly == false)
        {
            System.out.println();
            System.out.println("Can't add hours to non-HourlyEmployee.");
            System.out.println();
            return;
        }
        HourlyEmployee em = (HourlyEmployee) employees.getItem(index);
        em.increaseHours( amount );
    }

    //Method Name: increaseSales
    //Parameters: int index; double amount
    //Return value(s): none
    //Partners: None
    //Increases the sales for commission employees by a specified amount
    public void increaseSales(int index, double amount)
    {
        CommissionEmployee em = (CommissionEmployee) employees.getItem(index);
        em.increaseSales(amount);
    }

    //Method Name: findAllBySubstring
    //Parameters: String find
    //Return value(s): Employee[]
    //Partners: None
    //Returns an array of all the employees in the EmployeeManager system that contain the substring passed.
    public Employee[] findAllBySubstring(String find)
    {
        Employee[] empMatch = new Employee[employees.lengthIs()];
        int k; 
        int m = 0;
        // cycles thru all the employee names looking for a match
        for( int i = 0; i < empMatch.length; i++ )
        {
            //k is zero - nomatch
            //k is one - match
            k = RabinKarp( employees.getItem(i).getFirstName()+employees.getItem(i).getLastName(), find );
            if ( k > 0) empMatch[m++] = employees.getItem(i);
        }
        return empMatch;
    }

    //Method Name: RabinKarp
    //Parameters: String name; String find
    //Return value(s): int
    //Partners: None
    //Does the preprocessing of finding the hash for the substring. Calls upon linearSearchRecursive to determine if the substring hash is in the collection of hashes and returns the result.
    private int RabinKarp(String name, String find)
    {
        int length = find.length();
        int findHash = stringHash(find);

        int[] hashes = new int[(name.length() - find.length()) +1];
        //change 0 for pos to (name.length() - 1) to get last character in string
        RabinKarpHashes( name, hashes, (name.length() - find.length()), length);

        return linearSearchRecursive(hashes, findHash, 0);
    }

    //Method Name: stringHash
    //Parameters: String s
    //Return value(s): int
    //Partners: None
    //Given a string, will return the hash value of that entire string, using a base 26 number system to create the hash.
    private int stringHash(String s)
    {
        char[] characters = new char[s.length()];
        int[] hash = new int[s.length()];
        int sum = 0;

        //Goes through the string given and assigns each character in that string a corresponding spot in the array characters
        for (int x = 0; x < s.length(); x++)
        {
            characters[x] = s.charAt(x);
        }

        //Goes through the newly created array of characters and turns it into an array of ints 
        //according to the charNumericValue method defined below; then hashes the ints using the base 26 number system
        for (int y = 0; y < characters.length; y++)
        {
            hash[y] = charNumericValue(characters[y]);
            hash[y] = hash[y] * ((int) Math.pow(26, (s.length() - (y+1))));
        }

        //Adds up all the ints in the array hash, giving a unique hash value for strings of the same size
        for (int p = 0; p < hash.length; p++)
        {
            sum += hash[p];
        }

        return sum;
    }

    //Method Name: charNumericalValue
    //Parameters: char c
    //Return value(s): int
    //Partners: None
    //This method returns the numeric value of a character and throws an InvalidCharacterException if the a letter is not passed.
    private int charNumericValue(char c)
    {
            switch (c)
            {
                case 'A': case 'a':
                    c = 0;
                    break;

                case 'B': case 'b':
                    c = 1;
                    break;

                case 'C': case 'c':
                    c = 2;
                    break;

                case 'D': case 'd':
                    c = 3;
                    break;

                case 'E': case 'e':
                    c = 4;
                    break;

                case 'F': case 'f':
                    c = 5;
                    break;

                case 'G': case 'g':
                    c = 6;
                    break;

                case 'H': case 'h':
                    c = 7;
                    break;

                case 'I': case 'i':
                    c = 8;
                    break;

                case 'J': case 'j':
                    c = 9;
                    break;

                case 'K': case 'k':
                    c = 10;
                    break;

                case 'L': case 'l':
                    c = 11;
                    break;

                case 'M': case 'm':
                    c = 12;
                    break;

                case 'N': case 'n':
                    c = 13;
                    break;

                case 'O': case 'o':
                    c = 14;
                    break;

                case 'P': case 'p':
                    c = 15;
                    break;

                case 'Q': case 'q':
                    c = 16;
                    break;

                case 'R': case 'r':
                    c = 17;
                    break;

                case 'S': case 's':
                    c = 18;
                    break;

                case 'T': case 't':
                    c = 19;
                    break;

                case 'U': case 'u':
                    c = 20;
                    break;

                case 'V': case 'v':
                    c = 21;
                    break;

                case 'W': case 'w':
                    c = 22;
                    break;

                case 'X': case 'x':
                    c = 23;
                    break;

                case 'Y': case 'y':
                    c = 24;
                    break;

                case 'Z': case 'z':
                    c = 25;
                    break;
                default: throw new InvalidCharacterException(c);
            }
        return c;
    }

    //Method Name: RabinKarpHashes
    //Parameters: String s; int[] hashes; int pos; int length
    //Return value(s): int
    //Partners: None
    //Finds the hash values of all substrings of size length in the string s, starting at index pos and down; these values are stored in the passed hashes array.
    private int RabinKarpHashes(String s, int[] hashes, int pos, int length)
    {
        //pos = name.length() - length
        //length = find.length()
        int base = 26;

        int previousHash;
        int previousCharacter;
        int newCharacter;

        if (pos < 0)
            return 1;

        if (pos >= (s.length() - length))
        {
            hashes[pos] = stringHash(s.substring( pos ));
            return RabinKarpHashes( s, hashes, pos - 1, length);
        } 
        else
        {
            previousHash = hashes[pos + 1];
            previousCharacter = charNumericValue( s.charAt( pos + length ) );
            newCharacter = charNumericValue( s.charAt( pos ) ) * (((int) Math.pow(base, (length - 1))) );
            hashes[pos] = ((previousHash - previousCharacter) / base) + newCharacter;
            return RabinKarpHashes( s, hashes, pos - 1, length);
         }
    }

    //Method Name: linearSearchRecursive
    //Parameters: int[] nameHashes; int findHash; int pos
    //Return value(s): int
    //Partners: None
    //Returns the position of key in the data array, or -1 if not found.
    private int linearSearchRecursive(int[] nameHashes, int findHash, int pos)
    {

        if ( pos >= nameHashes.length )
            return 0;

        if ( nameHashes[pos] == findHash )
            return 1;

        return linearSearchRecursive( nameHashes, findHash, pos + 1 );
    }

    //Method Name: sort
    //Parameters: none
    //Return value(s): none 
    //Partners: None
    //sorts the ArrayList and LinkedList items
    public void sort()
    {
        employees.sort();
        if (!hourlyList.isEmpty())
            hourlyList.sort();
        if (!salaryList.isEmpty())
            salaryList.sort();
        if (!commissionList.isEmpty())
            commissionList.sort();
    }

    //Method Name: addRequest
    //Parameters: int empNum
    //Return value(s): boolean
    //Partners: None
    //Add the passed employee number's corresponding employee to the vacationRequest queue
    public boolean addRequest(int empNum)
    {
        int add = -1;
        for (int x = 0; x < employees.lengthIs(); x++)
        {
            if (employees.getItem(x).getEmployeeNumber() == empNum)
                add = x;
        }
        if (add >= 0)
        {
            vacationRequests.enqueue(employees.getItem(add));
            return true;
        }
        return false;
    }

    //Method Name: viewNextRequest
    //Parameters: None
    //Return value(s): None
    //Partners: None
    //Views the next vacation request in the queue (at the front of the line)
    public Employee viewNextRequest()
    {
        if (vacationRequests.isEmpty() == true)
            return null;
        return vacationRequests.peek();
    }

    //Method Name: grantNextRequest
    //Parameters: None
    //Return value(s): Employee
    //Partners: None
    //Grants the next vacation request (front of the line) and subsequently removes that employee from the queue
    public Employee grantNextRequest()
    {
        if (vacationRequests.isEmpty() == false)
        {
            Employee grant = vacationRequests.peek();
            vacationRequests.dequeue();
            return grant;
        }
        return null;
    }

    //Method Name: outputRequests
    //Parameters: None
    //Return value(s): None
    //Partners: None
    //Prints all the employees currently in the vacationRequest queue
    public void outputRequests()
    {
        if (vacationRequests.isEmpty() == false)
            System.out.println(vacationRequests.toString());
        else
            System.out.println("No vacation requests");
    }

    //Method Name: loadEmployees
    //Parameters: String employeeFile, requestFile
    //Return value(s): boolean
    //Partners: None
    //Loads the employees form the employeeFile and requestFile from saveEmployees
    public boolean loadEmployees(String employeeFile, String requestFile)
    {
        employees.clear();
        hourlyList.clear();
        salaryList.clear();
        commissionList.clear();
        vacationRequests.clear();

        //Open and read from the employee file
        try
        {
            ObjectInputStream load = new ObjectInputStream(new FileInputStream(employeeFile));

            //Read in objects from the file and write them to their respective data structure
            try
            {
                Employee y;
                while (true)
                {
                    y = (Employee) load.readObject();

                    employees.addItem(y);

                    if (y instanceof HourlyEmployee)
                        hourlyList.insertAtBack(y);

                    if(y instanceof SalaryEmployee)
                        salaryList.insertAtBack(y);

                    if(y instanceof CommissionEmployee)
                        commissionList.insertAtBack(y);
                }

            }
            catch(EOFException EO)
            {
            }
            catch(ClassNotFoundException CN)
            {
                return false;
            }
            catch(IOException IOE)
            {
                return false;
            }
            catch(MaximumCapacityException mc)
            {
                System.err.println("Maximum Capacity Reached");
                return true;
            }

            //close the file
            try
            {
                if(load != null)
                    load.close();
            }
            catch(IOException IOE)
            {
            }
        }
        catch (IOException IO)
        {
            return false;
        }

        //Open the vacation requests file
        try
        {
            int eVRequest = 0;

            Scanner input = new Scanner(new File(requestFile));

            if (!(input.hasNext()))
                return true;

            while (true)
            {
                eVRequest = input.nextInt();
                for (int y = 0; y < employees.lengthIs(); y++)
                {
                    if (employees.getItem(y).getEmployeeNumber() == eVRequest)
                        vacationRequests.enqueue(employees.getItem(y));
                }

                if (!(input.hasNext()))
                {
                    input.close();
                    return true;
                }
            }
        }
        catch (FileNotFoundException FNF)
        {
            return false;
        }
        catch (NoSuchElementException e)
        {
        }

        return false;
    }

    //Method Name: saveEmployees
    //Parameters: String employeeFile, requestfile
    //Return value(s): boolean
    //Partners: None
    //Saves all the employees in the employees ArrayList and the vacationRequests Queue
    public boolean saveEmployees(String employeeFile, String requestFile)
    {
        //Save employees
        try
        {
            ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(employeeFile));

            try
            {
                for (int x = 0; x < employees.lengthIs(); x++)
                {
                    save.writeObject(employees.getItem(x));
                    save.flush();
                }
            }
            catch (IOException IOE)
            {
                System.out.println("Error writing to file");
            }

            try
            {
                if (save != null)
                    save.close();
            }
            catch (IOException I)
            {
            }
        }
        catch (IOException IO)
        {
        }

        //Save vacation requests
        try
        {
            Formatter queue = new Formatter(requestFile);
            try
            {
                Employee u = null;
                for (int x = 0; x < vacationRequests.lengthIs(); x++)
                {
                    u = (Employee) vacationRequests.dequeue();
                    queue.format("%d  ", u.getEmployeeNumber());
                    vacationRequests.enqueue(u);
                    queue.flush();
                }
                return true;
            }
            catch (FormatterClosedException FC)
            {
            }
            finally
            {
                queue.close();
            }
        }
        catch (SecurityException SE)
        {
        }
        catch (FileNotFoundException FN)
        {
        }
        return false;
    }

    //Method Name: processUpdates
    //Parameters: String fileName
    //Return value(s): boolean
    //Partners: None
    //Reads in the updates in the given update file and applies them to the employees
    public boolean processUpdates(String fileName)
    {
        try
        {
            Scanner update = new Scanner(new File(fileName));

            while (true)
            {
                int en = 0;
                double amount = 0.0;

                en = update.nextInt();
                amount = update.nextDouble();

                if (amount < 0.0 || en < 10000 || en > 99999)
                {
                    en = 0;
                    amount = 0.0;
                }

                if (en > 0 && amount > 0)
                {
                    for (int y = 0; y < employees.lengthIs(); y++)
                    {
                        Employee l = employees.getItem(y);
                        if (l.getEmployeeNumber() == en)
                        {
                            if (l instanceof SalaryEmployee)
                                break;
                            if (l instanceof HourlyEmployee)
                                ((HourlyEmployee) l).increaseHours(amount);
                            if (l instanceof CommissionEmployee)
                                ((CommissionEmployee) l).increaseSales(amount);
                        }
                    }
                }
                if (!(update.hasNext()))
                {
                    update.close();
                    return true;
                }
            }
        }
        catch (FileNotFoundException e)
        {
        }
        catch (NoSuchElementException n)
        {
        }
        return false;
    }
}
