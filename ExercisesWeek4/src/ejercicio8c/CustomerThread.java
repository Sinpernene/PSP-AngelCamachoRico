package ejercicio8c;

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
            try
            {
                while (container.size() < 6)
                {
                    synchronized (container)
                    {
                        container.wait();
                    }
                }
                System.out.println("Hilo Consumidor: Creando caja con 6 cápsulas");
                for (int i = 0; i < 6; i++)
                {
                    container.removeCapsule();
                }
                Thread.sleep(random.nextInt(2001) + 1000);
                System.out.println("Hilo Consumidor: Caja creada");
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
