package exercice2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el número de hilos a crear: ");
        int n1 = scanner.nextInt();

        if (n1 < 1)
        {
            System.out.println("El número de hilos debe ser mayor o igual a 1");
            return;
        }

        for (int i = 0; i < n1; i++)
        {
            Thread thread = new Thread(new Exercice2Runnable());
            thread.setName("Hilo " + (i + 1));
            thread.start();
        }
    }
}

