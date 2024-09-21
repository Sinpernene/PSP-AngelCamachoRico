package exercice1.Anonymous;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el primer número (n1): ");
        int n1 = scanner.nextInt();
        System.out.print("Introduce el segundo número (n2): ");
        int n2 = scanner.nextInt();

        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = n1; i <= n2; i++)
                {
                    System.out.println(i);
                    try
                    {
                        Thread.sleep(ThreadLocalRandom.current().nextInt(1, 1001));
                    }
                    catch (InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        thread.start();

        System.out.println("El hilo se ha lanzado");
    }
}
