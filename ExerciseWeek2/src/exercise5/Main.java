package exercise5;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Indica cada cuántos segundos quieres que se guarde el saludo:");
        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();
        scanner.nextLine();
        Thread thread = new Thread(() -> {
            try(FileOutputStream fos = new FileOutputStream("saludo.txt"))
            {
                while (true)
                {
                    fos.write("¡Hola mundo!\n".getBytes(StandardCharsets.UTF_8));
                    Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
                }
            }
            catch (Exception e)
            {
                System.out.println("Hilo interrumpido\n¡Adiós!");
            }
        });
        thread.start();
        System.out.println("Pulsa enter para interrumpir el hilo:");
        scanner.nextLine();
        thread.interrupt();
        System.out.println("Interrumpiendo hilo");
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
