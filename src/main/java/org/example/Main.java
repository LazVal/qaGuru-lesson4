package org.example;

public class Main {
    public static void main(String[] args) {
       int a = 25;
       int b = 10;
       double c = 5.1;
       int sum = a + b;
       int subt = a - b;
       int mult = a * b;
       int div = a / b;
       System.out.println("Сумма: " + sum);
       System.out.println("Вычитание: " + subt);
       System.out.println("Умножение: " + mult);
       System.out.println("Деление: " + div);
       System.out.println();
       System.out.println("Арифметических операций над int и double:");
       System.out.println("Сумма: " + (a + c));
       System.out.println("Вычитание: " + (a - c));
       System.out.println("Умножение: " + (a * c));
       System.out.println("Деление: " + (a / c));

        System.out.println();
        System.out.println("логические операций");
        if (sum % 2 == 1){
            System.out.println("Сумма нечетная");
        }
        if (subt > div){
            System.out.println(div - subt);
        }
        if (b < a){
            System.out.println("b < a");
        }
        System.out.println();
        System.out.println("переполнение при арифметической операции");
        byte overflow = 127;
        overflow++;
        System.out.println(overflow);

    }
}