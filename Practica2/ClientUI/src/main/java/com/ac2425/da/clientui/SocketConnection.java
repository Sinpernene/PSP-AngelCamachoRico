package com.ac2425.da.clientui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketConnection
{
    private static PrintWriter out;
    private static BufferedReader in;

    public static void initialize(Socket socket)
    {
        if (socket == null)
            return;
        if (out != null || in != null)
            close();
        try
        {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static PrintWriter getOut()
    {
        return out;
    }

    public static BufferedReader getIn()
    {
        return in;
    }


    public static void close()
    {
        try
        {
            out.close();
            in.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
