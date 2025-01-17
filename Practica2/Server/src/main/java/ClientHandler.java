import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

class ClientHandler implements Runnable
{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String name;

    public ClientHandler(Socket socket) throws IOException
    {
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run()
    {
        try
        {
            String message;
            while ((message = in.readLine()) != null)
            {
                System.out.println("Mensaje recibido: " + message);
                if (message.startsWith("/groups"))
                {
                    System.out.println("Obteniendo lista de grupos");
                    sendMessage(ChatServer.getGroups());
                }
                else if (message.startsWith("/group"))
                {
                    System.out.println("Broadcast a grupo");
                    String[] parts = message.split(" ", 3);
                    if (parts.length == 3)
                        ChatServer.broadcastToGroup(parts[1], parts[2]);
                }
                else if (message.startsWith("/join"))
                {
                    System.out.println("Unir al grupo");
                    String[] parts = message.split(" ", 2);
                    if (parts.length == 2)
                        ChatServer.addToGroup(parts[1], this);
                }
                else if (message.startsWith("/username"))
                {
                    System.out.println("Configurando nombre de usuario");
                    String[] parts = message.split(" ", 2);
                    if (parts.length == 2)
                        setName(parts[1]);
                }

                else
                {
                    System.out.println("Comando no reconocido: " + message);
                }
            }
        }
        catch (SocketException e)
        {
            System.err.println("Conexión reiniciada por cliente: " + e.getMessage());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message)
    {
        System.out.println("Sending message to " + name + ": " + message);
        out.println(message);
    }

    private void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
