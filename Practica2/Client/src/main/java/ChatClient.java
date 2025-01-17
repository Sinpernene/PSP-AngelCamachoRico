import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa tu nombre de usuario: ");
        String username = scanner.nextLine();
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {
            System.out.println("Conectado al servidor de chat.");
            Thread listener = new Thread(() -> {
                String serverMessage;
                try
                {
                    while ((serverMessage = in.readLine()) != null)
                    {
                        System.out.println(serverMessage);
                    }
                }
                catch (IOException e)
                {
                    System.err.println("Conexión cerrada.");
                }
            });
            listener.start();
            while (true)
            {
                String userMessage = scanner.nextLine();
                out.println(username + ": " + userMessage);
            }
        }
        catch (IOException e)
        {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
