package com.ac2425.da;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main
{
    public static void main(String[] args)
    {
        Lane[] lanes = new Lane[4];
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Integer> finalPositions = new ArrayList<>();

        for (int i = 0; i < 4; i++)
        {
            int idLane = i;
            lanes[idLane] = new Lane(idLane);
            executor.submit(() -> {
                try {
                    Runner[] runners = new Runner[4];
                    Thread[] runnerThreads = new Thread[4];
                    for (int j = 0; j < 4; j++)
                    {
                        boolean hasBaton = (j == 0);
                        runners[j] = new Runner(j, lanes[idLane], hasBaton);
                        lanes[idLane].addRunner(runners[j], j * 100000);
                        runnerThreads[j] = new Thread(runners[j]);
                        runnerThreads[j].start();
                    }
                    for (Thread hilo : runnerThreads)
                        hilo.join();
                    synchronized (finalPositions)
                    {
                        finalPositions.add(idLane);
                        finalPositions.notify();
                        System.out.printf("Carril %d ha finalizado la carrera. Notificamos a hilo principal.%n", idLane);
                    }
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            });
        }
        executor.shutdown();
        synchronized (finalPositions)
        {
            while (true)
            {
                try
                {
                    if (!finalPositions.isEmpty())
                        System.out.printf("PODIUM: Posición %dº para el equipo de carril %d%n", finalPositions.size(), finalPositions.get(finalPositions.size() - 1));
                    if (finalPositions.size() == 4)
                        break;
                    finalPositions.wait();
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}