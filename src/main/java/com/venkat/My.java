package com.venkat;

import java.util.Scanner;

public class My {

    public static void main(String[] args) {
        String input = "1 2";

        String[] v = input.split(" ");
        System.out.println(Integer.parseInt(v[0]));
        System.out.println(Integer.parseInt(v[1]));
        System.out.println("Enter a number");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int sum = sumOfDigits(number);
        System.out.println("Sum is " + sum);

        myString();
    }

    private static void myString(){
        String str = "Some testing text, Venkat is another test message, Venkat which is not important.";
        String[] strSplit = str.split("Venkat");

        System.out.println(strSplit.length);
    }

    private static int sumOfDigits(int number){
        int sum = 0;
        while(number > 0){
            sum = sum + number % 10;
            number = number / 10;
        }
        return sum;
    }
}
