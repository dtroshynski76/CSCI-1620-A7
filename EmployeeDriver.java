//Name: Donovan Troshynski
//Class: 1620-002
//Program #: 6
//Due Date: 10 November 2015
//Honor Pledge: On my honor as a student of the Univeristy of Nebraska at Omaha,I have neither given nor received unauthorized help on this homework assignment.
//NAME: Donovan Troshynski


//Partners: NONE
//This assignment will implement everything from the first assignment along with an employee manager system which the user can operate to add, delete, or alter different types of employees.

import java.util.Scanner;

import employeeType.subTypes.HourlyEmployee;
import employeeType.subTypes.SalaryEmployee;
import employeeType.subTypes.CommissionEmployee;
import employeeType.employee.Employee;

import exceptions.InvalidCharacterException;
import java.util.InputMismatchException;
import exceptions.InvalidEmployeeNumberException;

public class EmployeeDriver
{
    static Scanner in = new Scanner(System.in);
    static boolean retry = false;

    public static int menu(String... options)
    {
        int choice = 0;
        for(int line = 0; line < options.length; line++)
            System.out.printf("%d. %s\n", line+1,options[line]);
        do
        {
            while (!retry)
            {
                try
                {
                    System.out.print("Enter Choice: ");
                    choice = in.nextInt();
                    retry = true;
                }
                catch (InputMismatchException IME) 
                {
                    in.nextLine();
                }
            }
            retry = false;
        }while(!(choice > 0 && choice <= options.length));

        retry = false;
        return choice;
    }

    public static void main(String args[])
    {
        boolean valid = false;
        boolean valid1 = false;
        boolean valid2 = false;
        boolean valid3 = false;
        boolean valid4 = false;
        boolean valid5 = false;
        boolean valid6 = false;
 
        int mainInput;//Input for main menu
        int subInput1;//Input for submenu
        int subInput2;//Input for sub-submenu
        int en = 0;          //Inputting an employee number
        int index;
        double amount = 0.0;
        String file = "";
        EmployeeManager em = new EmployeeManager(); //The EmployeeManager object

        //Main control loop, keep coming back to the
        //Main menu after each selection is finished
        if(em.loadEmployees("employees.ser", "requests.dat") == true)
        {
//            em.loadEmployees("employees.ser", "requests.dat");
            System.out.println("Employees Loaded");
        }
        else
            System.out.println("Employees Not Loaded");
        do
        {
            //This is the main menu. Displays menu 
            //and asks for a choice, validates that
            //what is entered is a valid choice
            System.out.println("\n\nMain Menu\n");
            em.listAll();
            mainInput = menu("Employee Submenu", "Add Employee", "Remove Employee", "Calculate Weekly Payout", "Calculate Bonus", "Annual Raises", "Reset Week", "Find Employee", "Sort Employees", "View Vacation Requests", "Add Vacation Request", "Grant Vacation Request", "Employee Updates", "Quit");
            //Perform the correct action based upon Main menu input
            switch(mainInput)
            {
                //Employee Submenu
                case 1:
                    do
                    {
                        subInput1 = menu("Hourly Employees", "Salary Employee", "Commission Employees", "Back");
                        switch(subInput1)
                        {
                            case 1:
                                em.listHourly();
                                do
                                {
                                    subInput2 = menu("Add Hours", "Back");
                                    if( subInput2 == 1)
                                    {
                                        while (!valid1) 
                                        {
                                            try {
                                                System.out.print("Employee Number: ");
                                                en = in.nextInt();
                                                valid1 = true;
                                            }
                                            catch (InputMismatchException IME)
                                            {
                                                in.nextLine();
                                            }
                                        }
                                        valid1 = false;
                                        index = em.getIndex(en);
                                        if(index != -1)
                                        {
                                            while (!valid2)
                                            {
                                                try {
                                                    System.out.print("Enter Hours: ");
                                                    amount = in.nextDouble();
                                                    valid2 = true;
                                                }
                                                catch (InputMismatchException IME)
                                                {
                                                    in.nextLine();
                                                }
                                            }
                                            valid2 = false;
                                            em.increaseHours(index, amount);
                                        }
                                        else
                                        {
                                            System.out.println();
                                            System.out.println("Employee not found!");
                                        }
                                    }
                                }while(subInput2 != 2);
                                break;
                            case 2:
                                em.listSalary();
                                subInput2 = menu("Back");
                                break;
                            case 3:
                                em.listCommission();
                                do
                                {
                                    subInput2 = menu("Add Sales", "Back");
                                    if( subInput2 == 1)
                                    {
                                        while (!valid3)
                                        {
                                            try
                                            {
                                                System.out.print("Employee Number: ");
                                                en = in.nextInt();
                                                valid3 = true;
                                            }
                                            catch (InputMismatchException IME)
                                            {
                                                in.nextLine();
                                            }
                                        }
                                        valid3 = false;
                                        index = em.getIndex(en);
                                        if(index != -1)
                                        {
                                            while (!valid4)
                                            {
                                                try
                                                {
                                                    System.out.print("Enter Sales: ");
                                                    amount = in.nextDouble();
                                                    valid4 = true;
                                                }
                                                catch (InputMismatchException IME)
                                                {
                                                    in.nextLine();
                                                }
                                            }
                                            valid4 = false;
                                            em.increaseSales(index, amount);
                                        }
                                        else
                                        {
                                            System.out.println("Employee not found!");
                                        }
                                    }
                                }while(subInput2 != 2);
                                break;
                        }
                    }while(subInput1 != 4);
                    break;
                    //Add Employee
                case 2:
                    String fn, ln;
                    char mi, g, f;
                    boolean ft = true;
                    subInput1 = menu("Hourly", "Salary", "Commission");
                    System.out.print("Enter Last Name: ");
                    ln = in.next();
                    System.out.print("Enter First Name: ");
                    fn = in.next();
                    System.out.print("Enter Middle Initial: ");
                    mi = in.next().charAt(0);
                    System.out.print("Enter Gender: ");
                    g = in.next().charAt(0);

                    System.out.print("Enter Employee Number: ");
                    en = in.nextInt();

                    System.out.print("Full Time? (y/n): ");
                    f = in.next().charAt(0);
                    if(f == 'n' || f == 'N')
                    {
                        ft = false;
                    }
                    while (!valid5)
                    {
                        try {
                            if(subInput1 == 1)
                            {
                                System.out.print("Enter wage: ");
                            }
                            else if(subInput1 == 2)
                            {
                                System.out.print("Enter salary: ");
                            }
                            else
                            {
                                System.out.print("Enter rate: ");
                            }
                            amount = in.nextDouble();
                            valid5 = true;
                        }
                        catch (InputMismatchException IME)
                        {
                            in.nextLine();
                        }
                    }
                    valid5 = false;

                    Scanner x = new Scanner(System.in);
                    while (!valid)
                    { 
                        try
                        {
                            em.addEmployee(subInput1, fn, ln , mi, g, en, ft, amount);
                            valid = true;
                        }
                        catch (InvalidEmployeeNumberException IE)
                        {
                            System.out.print("Invalid Employee Number, please re-enter: ");
                            en = x.nextInt();
                        }
                    }
                    valid = false;
                    break;

                    //Remove Employee
                case 3:
                    while (!valid6)
                    {
                        try
                        {
                            System.out.print("Enter Employee Number to Remove: ");
                            en = in.nextInt();
                            valid6 = true;
                        }
                        catch (InputMismatchException IME)
                        {
                            in.nextLine();
                        }
                    }
                    valid6 = false;
                    index = em.getIndex(en);
                    em.removeEmployee(index);
                    break;

                    //Calculate Weekly Payout
                case 4:
                    System.out.println();
                    System.out.printf("Total weekly payout is %.2f\n", em.calculatePayout());
                    break;

                    //Calculate Bonus
                case 5:
                    amount = em.holidayBonuses();
                    System.out.println();
                    System.out.printf("Total holiday bonus payout is %.2f\n", amount);
                    break;

                    //Apply Annual Raises
                case 6:
                    em.annualRaises();
                    System.out.println();
                    System.out.println("Annual Raises applied.");
                    break;

                    //Reset the weeks values
                case 7:
                    em.resetWeek();
                    System.out.println();
                    System.out.println("Weekly values reset.");
                    break;

                    //Find Employee
                case 8:
                    System.out.print("Enter substring of Employee name: ");
                    String substring = in.next();
                    Employee[] ret = null;
                    try
                    {
                        ret = em.findAllBySubstring(substring);
                    }
                    catch(InvalidCharacterException ICE)
                    {
                        System.out.println("Invalid character found in search");
                        break;
                    }
                    System.out.println();
                    System.out.println("Matches found:");
                    if(ret != null)
                        for(int i = 0; i < ret.length && ret[i] != null; i++)
                            System.out.println(ret[i]);
                    break;

                //Sort Employees
                case 9:
                    em.sort();
                    System.out.println();
                    System.out.println("Employees sorted.");
                    break;

                //View vacation requests
                case 10:
                    em.outputRequests();
                    if (em.viewNextRequest() != null)
                    {
                        System.out.print(em.viewNextRequest());
                        System.out.print("will receive next request");
                    }
                    break;

                //Add vacation request
                case 11:
                    System.out.print("Enter Employee Number for request: ");
                    en = in.nextInt();
                    if (em.getIndex(en) >= 0)
                    {
                        em.addRequest(en);
                        System.out.println("Employee " + en + " added to vacation queue");
                    }
                    else
                        System.out.println("No such Employee");
                    break;

                //Grant vacation request
                case 12:
                    if (em.viewNextRequest() != null)
                    {
                        System.out.print(em.viewNextRequest());
                        System.out.print("granted vacation request");
                        em.grantNextRequest();
                    }
                    else
                    {
                        System.out.print("No vacation requests");
                        System.out.println();
                    }
                    break;

                case 13:
                    System.out.print("Enter name of update file: ");
                    file = in.next();
                    if (em.processUpdates(file) == true)
                    {
                        System.out.print("Updates processed successfully");
                    }
                    else
                        System.out.println("Updates not processed");
                    break;

                //Exit
                case 14:
                    System.out.println("\nThank you for using the Employee Manager!\n");

                    if (em.saveEmployees("employees.ser", "requests.dat") == true)
                    {
                        System.out.println("Employees stored");
                    }
                    else
                        System.out.println("Employees not stored");
            }
        }while(mainInput != 14);
    }
}
