package com.venkat;

import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class My {

    private static void myEscape(){
        String str = "\"Venkatram\"";
    }

    private static  void mergedMapEg(){
        Stream<String[]>
                str = Stream
                .of(new String[][] { { "GFG", "GeeksForGeeks" },
                        { "g", "geeks" },
                        { "GFG", "geeksforgeeks" } });

        // Get Map from String
        // using toMap() method
        Map<String, String>
                map = str
                .collect(Collectors
                        .toMap(p -> p[0], p -> p[1], (s, a) -> s + ", " + a));

        // Print the Map
        System.out.println("Map:" + map);
        //Map:{g=geeks, GFG=GeeksForGeeks, geeksforgeeks}
    }

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
