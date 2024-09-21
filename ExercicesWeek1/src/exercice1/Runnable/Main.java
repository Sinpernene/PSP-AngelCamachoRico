package exercice1.Runnable;

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

        Runnable runnable = new Exercice1Runnable(n1, n2);
        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("El hilo se ha lanzado");
    }
}

class Exercice1Runnable implements Runnable
{
    private final int n1;
    private final int n2;

    public Exercice1Runnable(int n1, int n2)
    {
        this.n1 = n1;
        this.n2 = n2;
    }

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
}