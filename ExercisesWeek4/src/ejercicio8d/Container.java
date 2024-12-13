package ejercicio8d;

import java.util.ArrayList;

public class Container
{
    ArrayList<Capsule> capsules = new ArrayList<>();

    public synchronized void addCapsule(Capsule capsule)
    {
        if (capsule == null)
            throw new IllegalArgumentException("The capsule cannot be null");
        capsules.add(capsule);
        notifyAll();
    }

    public synchronized Capsule removeCapsule()
    {
        if (capsules.isEmpty())
            return null;
        Capsule capsule = capsules.remove(0);
        notifyAll();
        return capsule;
    }

    public synchronized int size()
    {
        return capsules.size();
    }
}
