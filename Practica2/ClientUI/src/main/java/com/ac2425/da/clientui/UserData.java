package com.ac2425.da.clientui;

public class UserData
{
    private static String username;
    private static String groupname;

    public static String getUsername()
    {
        return username;
    }

    public static void setUsername(String username)
    {
        UserData.username = username;
    }

    public static String getGroupname()
    {
        return groupname;
    }

    public static void setGroupname(String groupname)
    {
        UserData.groupname = groupname;
    }
}
