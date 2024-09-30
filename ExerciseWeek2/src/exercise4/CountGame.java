package exercise4;

public class CountGame implements Runnable
{
    private int count = 1;
    private final int maxCount;
    private volatile boolean isRunning = true;

    public CountGame(int maxCount)
    {
        if (maxCount < 10 || maxCount > 20)
            throw new IllegalArgumentException("maxCount must be between 10 and 20");
        this.maxCount = maxCount;
    }

    @Override
    public void run()
    {
        try
        {
            while (isRunning)
            {
                if (count <= 5)
                    System.out.println(count);
                Thread.sleep(1000);
                count++;
            }
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public int tryResult()
    {
        isRunning = false;
        if (count == maxCount)
            return maxCount;
        return count;
    }
}
