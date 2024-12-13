package ejercicio8;

import java.util.Random;

public class CustomerThread implements Runnable
{
    private final Container container;

    public CustomerThread(Container container)
    {
        if (container == null)
            throw new IllegalArgumentException("The container cannot be null");
        this.container = container;
    }

    @Override
    public void run()
    {
        Random random = new Random();
        while (!Thread.currentThread().isInterrupted())
        {
            if (container.size() >= 6)
            {
                System.out.println("Hilo Consumidor: Creando caja con 6 cápsulas");
                synchronized (container)
                {
                    for (int i = 0; i < 6; i++)
                        container.removeCapsule();
                }
                try
                {
                    Thread.sleep(random.nextInt(1001) + 3000);
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            else
            {
                try
                {
                    wait(3000);
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}
