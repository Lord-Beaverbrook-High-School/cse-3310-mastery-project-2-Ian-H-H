/*
I used these videos to learn about converting base-2 and base-10 numbers.
https://www.youtube.com/watch?v=L4djvKA2Zt0
https://www.youtube.com/watch?v=VLflTjd3lWA
*/

/*
I will input different values to check if my code can handle error input.
For example, my code will be able to detect: negative numbers, string input, binary input other than 0 and 1

After testing, this class will not work if the decimal that is being converted or convert from a binary exceed int store limit, which is -2147483648 to 2147483647
When convert from binary to decimal, no matter how large the binary is, it will return 2147483647 at max
When convert from decimal to binary, error will return when the input decimal is greater than 2147483647
*/

import java.util.Scanner;
import java.lang.Math;

public class Main
{
    public static void main(String[] args)
    {
        int choice = userchoice();
        switch(choice)
        {
            case 1:
                System.out.println(binToDec(generateBinary()));
                break;
            case 2:
                System.out.println(decToBin(generateDecimal()));
                break;
        }

    }
    // ask user what they want to do
    public static int userchoice()
    {
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        while (true)
        {
            try
            {
                System.out.println("If you would like to convert binary to decimal, please enter 1.");
                System.out.println("If you would like to convert decimal to binary, please enter 2.");
                System.out.print("Enter your choice: ");
                choice = scan.nextInt();
                if ((choice == 1) || (choice == 2))
                    break;
                else
                    System.out.println("Invalid input, try again.\n");
            }
            catch (Exception e)
            {
                System.out.println("Invalid input, try again.\n");
                scan.nextLine();
            }
        }
        return choice;
    }

    // user input binary
    public static String generateBinary()
    {
        String input = "";
        int test = -1;
        Boolean condition = true;
        Scanner scan = new Scanner(System.in);
        // user input binary
        System.out.print("Enter the binary that you want to convert to decimal: ");
        input = scan.nextLine();
        // test if the input is a binary
        String[] array = input.split("");
        for (int i = 0; i < input.length(); i++)
        {
            if ((array[i].equals("0") == false) && (array[i].equals("1")) == false)
            {
                i = input.length();
                System.out.println("invalid input, binary entered contains numbers other than 0 and 1");
                System.out.println("");
                return generateBinary();
            }
        }

        return input;
    }

    // user input decimal
    public static int generateDecimal ()
    {
        Scanner scan = new Scanner(System.in);
        // return -1 if something went wrong
        int result = -1;
        while (true)
        {
            try
            {
                System.out.print("Enter the decimal that you want to convert to binary: ");
                result = scan.nextInt();
                // throw if result is a negative number
                if (result < 0)
                    throw new Exception();
                break;
            }
            catch (Exception e)
            {
                System.out.println("Invalid input, try again.\n");
                scan.nextLine();
            }
        }
        return result;
    }

    // convert binary to decimal
    public static int binToDec(String binary)
    {
        String[] binaryList = binary.split("");
        // convert
        return binToDecProcess(binaryList, 0, 0);
    }

    // process of converting
    public static int binToDecProcess (String[] binaryList, int count, int result)
    {
        if (binaryList[count].equals("1"))
            result += Math.pow(2, (binaryList.length - 1 - count));
        count ++;
        if ((count < binaryList.length))
            return binToDecProcess(binaryList, count, result);
        return result;
    }

    // convert decimal to binary
    public static String decToBin (int decimal)
    {
        // determine the highest power, also how long the binary will be
        double powerOfTwo = 0;
        int power = 0;
        while (decimal >= powerOfTwo)
        {
            powerOfTwo = Math.pow(2, power);
            power ++;
        }
        if (power == 0)
            return "0";
        String[] binaryList = new String[power - 1];
        // convert
        return decToBinProcess(binaryList, power - 2, decimal);
    }

    // process of converting
    public static String decToBinProcess (String[] binaryList, int power, int decimal)
    {
        if (power >= 0)
        {
            int subtract = (int)(decimal - Math.pow(2, power));
            if (subtract >= 0)
            {
                binaryList[binaryList.length - 1 - power] = "1";
                decToBinProcess(binaryList, power - 1, subtract);
            }
            else
            {
                binaryList[binaryList.length - 1 - power] = "0";
                decToBinProcess(binaryList, power - 1, decimal);
            }
        }
        String result = "";
        for (String i : binaryList)
            result += i;
        return result;
    }
}

// https://github.com/Lord-Beaverbrook-High-School/cse-3310-mastery-project-2-Ian-H-H