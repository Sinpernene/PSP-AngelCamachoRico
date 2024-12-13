package com.ac2425.da;

public class Runner implements Runnable
{
    private final int id;
    private final Lane lane;
    private boolean hasBaton;
    private final Object monitor = new Object();

    public Runner(int id, Lane lane, boolean hasBaton)
    {
        this.id = id;
        this.lane = lane;
        this.hasBaton = hasBaton;
    }

    public int getId()
    {
        return id;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                synchronized (monitor)
                {
                    while (!hasBaton)
                    {
                        System.out.printf("Carril %d Corredor %d: Esperando el testigo%n", lane.getId(), id);
                        monitor.wait();
                    }
                    System.out.printf("Carril %d Corredor %d: He recibido el testigo y empiezo a correr%n", lane.getId(), id);
                }
                while (hasBaton)
                {
                    if (lane.advanceRunner(id))
                    {
                        Runner next = lane.canPassBaton(id);
                        if (next != null)
                        {
                            System.out.printf("Carril %d Corredor %d: Pierdo el testigo%n", lane.getId(), id);
                            next.setHasBaton(true);
                            hasBaton = false;
                        }
                    }
                    else
                    {
                        hasBaton = false;
                    }
                }
                System.out.printf("Carril %d Corredor %d: He terminado de correr. Posición final: %d%n", lane.getId(), id, lane.getPosition(id));
                break;
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void setHasBaton(boolean hasBaton)
    {
        synchronized (monitor)
        {
             this.hasBaton = hasBaton;
             monitor.notify();
             System.out.printf("Carril %d Corredor %d: Despierto al siguiente corredor%n", lane.getId(), id);
        }
    }
}
