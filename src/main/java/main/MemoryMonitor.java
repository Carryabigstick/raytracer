package main;

public class MemoryMonitor extends Thread
{
    private volatile boolean running = true;


    @Override
    public void run()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

        while(running)
        {
            long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            double usedMB = used / (1024.0 * 1024.0);
            long max = Runtime.getRuntime().maxMemory();
            double maxMB = max / (1024.0 * 1024.0);

            System.out.printf("\rUsed memory: %-7.2f / %-5.2f MB", usedMB,maxMB);
            System.out.flush();

            try
            {
                Thread.sleep(50);
            } catch (InterruptedException e)
            {
                running = false;
            }

        }

    }

    public void stopMonitoring()
    {
        running = false;
        this.interrupt();
    }


}
