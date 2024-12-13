package ejercicio8;

import java.util.ArrayList;

public class Container
{
    private ArrayList<Capsule> capsules = new ArrayList<>();

    public synchronized void addCapsule(Capsule capsule)
    {
        if (capsule == null)
            throw new IllegalArgumentException("The capsule cannot be null");
        capsules.add(capsule);
    }

    public synchronized Capsule removeCapsule()
    {
        if (capsules.isEmpty())
            return null;
        return capsules.remove(0);
    }

    public synchronized int size()
    {
        return capsules.size();
    }
}
