import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ChatServer {
    private static final int PORT = 12345;
    private static final Map<String, List<ClientHandler>> groups = new HashMap<>();
    private static List<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService executor = Executors.newCachedThreadPool();


    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado en el puerto " + PORT);

        while (true)
        {
            Socket socket = serverSocket.accept();
            ClientHandler client = new ClientHandler(socket);
            clients.add(client);
            executor.execute(client);
        }
    }

    public static void broadcastToGroup(String groupName, String message)
    {
        List<ClientHandler> groupMembers = groups.get(groupName);
        if (groupMembers != null)
        {
            for (ClientHandler member : groupMembers)
                member.sendMessage(message);
        }
    }

    public static void addToGroup(String groupName, ClientHandler client)
    {
        System.out.println("Adding " + client.getName() + " to group " + groupName);
        groups.computeIfAbsent(groupName, k -> new ArrayList<>()).add(client);
    }

    public static String getGroups() {
        System.out.println("Grupos actuales: " + groups.keySet());
        if (groups.isEmpty()) {
            return "";
        }
        return String.join(", ", groups.keySet()) + ";";
    }

    public static void removeFromGroup(String groupName, ClientHandler client)
    {
        List<ClientHandler> groupMembers = groups.get(groupName);
        if (groupMembers != null)
            groupMembers.remove(client);
    }
}
