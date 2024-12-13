package ejercicio8d;

import java.util.Random;

public class ProducerThread implements Runnable
{
    private String variety;
    private int intensity;
    private final Container container;

    public ProducerThread(String variety, int intensity, Container container)
    {
        if (intensity < 0 || intensity > 9)
            throw new IllegalArgumentException("La intensidad debe estar entre 0 y 0");
        if (variety == null || variety.isEmpty())
            throw new IllegalArgumentException("La variedad no puede ser nula o vacía");
        if (container == null)
            throw new IllegalArgumentException("Container cannot be null");
        this.variety = variety;
        this.intensity = intensity;
        this.container = container;
    }

    @Override
    public void run()
    {
        Random random = new Random();
        while (true)
        {
            if (container.size() < 100)
            {
                try
                {
                    Thread.sleep(random.nextInt(501) + 500);
                    Capsule capsule = new Capsule(variety, intensity);
                    container.addCapsule(capsule);
                    System.out.println("Hilo Productor: Se ha fabricado una cápsula. Total en el contenedor: " + container.size());
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public String getVariety()
    {
        return variety;
    }

    public int getIntensity()
    {
        return intensity;
    }
}
