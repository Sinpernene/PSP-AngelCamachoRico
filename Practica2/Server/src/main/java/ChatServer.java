import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public static void broadcastToGroup(String groupName, String message, ClientHandler sender)
    {
        List<ClientHandler> groupMembers = groups.get(groupName);
        if (groupMembers != null)
        {
            System.out.println("Broadcasting message to group " + groupName + " from " + sender.getName());
            for (ClientHandler member : groupMembers)
            {
                if (member != sender)
                    member.sendMessage(sender.getName() + ": " + message);
            }
        }
    }

    public static void addToGroup(String groupName, ClientHandler client)
    {
        System.out.println("Adding " + client.getName() + " to group " + groupName);
        groups.computeIfAbsent(groupName, k -> new ArrayList<>()).add(client);
        for (ClientHandler member : groups.get(groupName))
            member.sendMessage(client.getName() + " se ha unido al grupo.");
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
        {
            groupMembers.remove(client);
            for (ClientHandler member : groupMembers)
                member.sendMessage(client.getName() + " ha salido del grupo.");
            if (groupMembers.isEmpty())
            {
                System.out.println("Removing group " + groupName);
                groups.remove(groupName);
            }
        }
        System.out.println("Removing " + client.getName() + " from group " + groupName);
    }
}
