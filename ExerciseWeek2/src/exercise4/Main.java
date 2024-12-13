package exercise4;

import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Random random = new Random();
            var randomNumber = random.nextInt(10, 20);
            CountGame game = new CountGame(randomNumber);
            System.out.println("Pulsa enter cuando creas que el contador ha llegado a " + randomNumber);
            Thread thread = new Thread(game);
            thread.start();

            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

            int result = game.tryResult();
            thread.join();
            if (result == randomNumber)
                System.out.println("¡Lo has conseguido!");
            else
                System.out.println("Vuelve a intentarlo, has detenido el contador en " + result);
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }
}