package ejercicio8;

import java.util.Random;

/*Habrá un hilo productor encargado de producir cápsulas de café con una frecuencia entre
500 y 1000 milisegundos. El nombre de la variedad de café y su intensidad se pasarán
como parámetros del constructor del hilo y todas las cápsulas que fabrique tendrán esas
características.*/

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
            try
            {
                Thread.sleep(random.nextInt(501) + 500);
                Capsule capsule = new Capsule(variety, intensity);
                container.addCapsule(capsule);
                System.out.println("Hilo Productor: Se ha fabricado una cápsula. Total en el contenedor: " + container.size());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
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
