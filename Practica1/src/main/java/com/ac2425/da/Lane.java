package com.ac2425.da;

import java.util.concurrent.locks.ReentrantLock;

public class Lane
{
    private final int id;
    private final Runner[] positions = new Runner[400000];
    private final ReentrantLock lock = new ReentrantLock();

    public Lane(int id)
    {
        this.id = id;
    }

    public void addRunner(Runner runner, int position)
    {
        lock.lock();
        try
        {
            if (positions[position] == null)
                positions[position] = runner;
        }
        finally
        {
            lock.unlock();
        }
    }

    public boolean advanceRunner(int idRunner) {
        lock.lock();
        try
        {
            for (int i = 0; i < positions.length; i++)
            {
                Runner runner = positions[i];
                if (runner != null && runner.getId() == idRunner)
                {
                    if (i + 1 < positions.length && positions[i + 1] == null)
                    {
                        positions[i + 1] = runner;
                        positions[i] = null;
                        return true;
                    }
                    return false;
                }
            }
            return false;
        }
        finally
        {
            lock.unlock();
        }
    }

    public Runner canPassBaton(int idRunner)
    {
        lock.lock();
        try
        {
            for (int i = 0; i < positions.length; i++)
            {
                if (positions[i] != null && positions[i].getId() == idRunner)
                    return (i + 1 < positions.length) ? positions[i + 1] : null;
            }
            return null;
        }
        finally
        {
            lock.unlock();
        }
    }

    public int getPosition(int idRunner)
    {
        lock.lock();
        try
        {
            for (int i = 0; i < positions.length; i++)
            {
                if (positions[i] != null && positions[i].getId() == idRunner)
                    return i;
            }
            return -1;
        }
        finally
        {
            lock.unlock();
        }
    }

    public int getId()
    {
        return id;
    }
}
