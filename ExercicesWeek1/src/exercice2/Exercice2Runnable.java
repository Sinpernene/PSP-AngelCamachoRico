package exercice2;

import java.util.Random;

public class Exercice2Runnable implements Runnable
{
    private final String threadName;
    public Exercice2Runnable(String threadName) throws IllegalArgumentException
    {
        if (threadName == null || threadName.isEmpty())
            throw new IllegalArgumentException();
        this.threadName = threadName;
    }

    @Override
    public void run()
    {
        Random random = new Random();
        int primesCount = random.nextInt(100) + 1;
        threadStartMessage(primesCount);
        for (int i = 0; i < primesCount; i++)
        {
            try
            {
                i = getNextPrimeNumber(i);
                threadMessage(i);
                Thread.sleep(random.nextInt(501) + 500);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }

    private int getNextPrimeNumber(int number)
    {
        do number++;
        while (!isPrime(number));
        return number;
    }

    private boolean isPrime(int number)
    {
        if (number <= 1)
            return false;
        if (number <= 3)
            return true;
        if (number % 2 == 0 || number % 3 == 0)
            return false;
        for (int i = 5; i * i <= number; i += 6)
        {
            if (number % i == 0 || number % (i + 2) == 0)
                return false;
        }
        return true;
    }

    private void threadStartMessage(int n) throws IllegalArgumentException
    {
        if (n < 0)
            throw new IllegalArgumentException();
        System.out.println(threadName + ": Mostrando números primos hasta hasta el " + n);
    }

    private void threadMessage(int n) throws IllegalArgumentException
    {
        if (n < 0)
            throw new IllegalArgumentException();
        System.out.println(threadName + ": " + n);
    }

    public String getThreadName()
    {
        return threadName;
    }
}
