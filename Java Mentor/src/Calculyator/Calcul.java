package Calculyator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Calcul {

        public static void main(String[] args) {

            Converter con = new Converter();
            String a;
            String b;
            String operation;
            int num1 = 0;
            int num2 = 0;
            int arab = 0;
            int rom = 0;

            String[] arabskie = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
            String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

            System.out.println("Введите математическое выражение состоящее из двух натуральных чисел" +
                    " от 1 до 10 (арабскими) или от I до X (римскими), \nи знака операции (+,-,*,/) между ними," +
                    " в одну строку" + "\nНе допускается использовать арабские и римские цифры вместе.");

            while (true) {
                Scanner scanner = new Scanner(System.in);
                a = scanner.next();
                if(a.equals("exit"))
                    break;
                for (int i = 0; i < 10; i++) {
                    if (a.equals(arabskie[i])) {
                        num1 = con.toArab(a);
                        arab++;
                    }
                    if (a.equals(roman[i])) {
                        num1 = con.toRom(a);
                        rom++;
                    }
                }
                operation = scanner.next();
                b = scanner.next();
                for (int i = 0; i < 10; i++) {
                    if (b.equals(arabskie[i])) {
                        num2 = con.toArab(b);
                        arab++;
                    }
                    if (b.equals(roman[i])) {
                        num2 = con.toRom(b);
                        rom++ ;
                    }
                }
                if (operation.equals("+") && arab == 2) {
                    System.out.println(num1 + num2);
                }
                else if (operation.equals("+") && rom == 2) {
                    System.out.println(con.arabicToRoman(num1 + num2));
                }
                else if (operation.equals("-") && arab == 2) {
                    System.out.println(num1 - num2);
                }
                else if (operation.equals("-") && rom == 2) {
                    System.out.println(con.arabicToRoman(num1 - num2));
                }
                else if (operation.equals("*") && arab == 2) {
                    System.out.println(num1 * num2);
                }
                else if (operation.equals("*") && rom == 2) {
                    System.out.println(con.arabicToRoman(num1 * num2));
                }
                else if (operation.equals("/") && arab == 2) {
                    System.out.println(num1 / num2);
                }
                else if (operation.equals("/") && rom == 2) {
                    System.out.println(con.arabicToRoman(num1 / num2));
                }
                else {
                    throw new IllegalArgumentException("Введены не верные значения, попробуйте еще раз?");
                }
                arab = 0;
                rom = 0;
                System.out.println("Еще пример? Если хотите завершить введите exit");
            }
        }
    }

    class Converter {
        public static int toRom(String v) {
            int n1 = 0;
            if (v.equals("I")){
                n1 = 1;
            }
            if (v.equals("II")) {
                n1 = 2;
            }
            if (v.equals("III")) {
                n1 = 3;
            }
            if (v.equals("IV")) {
                n1 = 4;
            }
            if (v.equals("V")) {
                n1 = 5;
            }
            if (v.equals("VI")) {
                n1 = 6;
            }
            if (v.equals("VII")) {
                n1 = 7;
            }
            if (v.equals("VIII")) {
                n1 = 8;
            }
            if (v.equals("IX")) {
                n1 = 9;
            }
            if (v.equals("X")) {
                n1 = 10;
            }
            return n1;
        }

        public static int toArab(String v) {
            int n2 = 0;
            if (v.equals("1")) {
                n2 = 1;
            }
            if (v.equals("2")) {
                n2 = 2;
            }
            if (v.equals("3")) {
                n2 = 3;
            }
            if (v.equals("4")) {
                n2 = 4;
            }
            if (v.equals("5")) {
                n2 = 5;
            }
            if (v.equals("6")) {
                n2 = 6;
            }
            if (v.equals("7")) {
                n2 = 7;
            }
            if (v.equals("8")) {
                n2 = 8;
            }
            if (v.equals("9")) {
                n2 = 9;
            }
            if (v.equals("10")) {
                n2 = 10;
            }
            return n2;
        }

        enum RomanNumeral {
            I(1), IV(4), V(5), IX(9), X(10),
            XL(40), L(50), XC(90), C(100),
            CD(400), D(500), CM(900), M(1000);

            private int value;

            RomanNumeral(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }

            public static List<RomanNumeral> getReverseSortedValues() {
                return Arrays.stream(values())
                        .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                        .collect(Collectors.toList());
            }
        }
        public static String arabicToRoman(int number) {
            if ((number <= 0) || (number > 4000)) {
                throw new IllegalArgumentException(number + " is not in range (0 - 4000)");
            }

            List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

            int i = 0;
            StringBuilder sb = new StringBuilder();

            while ((number > 0) && (i < romanNumerals.size())) {
                RomanNumeral currentSymbol = romanNumerals.get(i);
                if (currentSymbol.getValue() <= number) {
                    sb.append(currentSymbol.name());
                    number -= currentSymbol.getValue();
                }
                else {
                    i++;
                }
            }

            return sb.toString();
        }
    }

