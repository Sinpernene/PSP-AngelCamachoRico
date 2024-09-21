package exercice3;

import exercice2.Exercice2Runnable;

import java.util.List;
import java.util.Scanner;

public class Main
{
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

        List<ThreadWithRunnable> threads = new java.util.ArrayList<>();

        for (int i = 0; i < n1; i++)
        {
            Exercice2Runnable runnable = new Exercice2Runnable("Hilo " + (i + 1));
            Thread thread = new Thread(runnable);
            thread.start();
            threads.add(new ThreadWithRunnable(thread, runnable));
        }

        do
        {
            try
            {
                Thread.sleep(1000);
                for (ThreadWithRunnable threadWithRunnable : threads) {
                    Thread thread = threadWithRunnable.thread();
                    System.out.println(thread.getId() + " " + threadWithRunnable.getThreadName() + " " + thread.getState());
                }
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
        while (isAnyThreadAlive(threads));
    }

    private static boolean isAnyThreadAlive(List<ThreadWithRunnable> threads)
    {
        if (threads == null || threads.isEmpty())
            return false;
        for (ThreadWithRunnable threadWithRunnable : threads)
        {
            if (threadWithRunnable.thread().isAlive())
                return true;
        }
        return false;
    }
}