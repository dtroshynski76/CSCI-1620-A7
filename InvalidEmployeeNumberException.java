package exceptions;

public class InvalidEmployeeNumberException extends Exception
{
    public InvalidEmployeeNumberException(int num)
    {
        super(num + " is not a valid EmployeeNumber\n");
    }
}
