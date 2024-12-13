package ejercicio8b;

public class Capsule
{
    private String variety;
    private int intensity;

    public Capsule(String variety, int intensity)
    {
        if (intensity < 0 || intensity > 9)
            throw new IllegalArgumentException("La intensidad debe estar entre 0 y 0");
        if (variety == null || variety.isEmpty())
            throw new IllegalArgumentException("La variedad no puede ser nula o vacía");
        this.variety = variety;
        this.intensity = intensity;
    }

    public String getVariety()
    {
        return variety;
    }

    public int getIntensity()
    {
        return intensity;
    }

    public void setVariety(String variety)
    {
        if (variety == null || variety.isEmpty())
            throw new IllegalArgumentException("La variedad no puede ser nula o vacía");
        this.variety = variety;
    }

    public void setIntensity(int intensity)
    {
        if (intensity < 0 || intensity > 9)
            throw new IllegalArgumentException("La intensidad debe estar entre 0 y 0");
        this.intensity = intensity;
    }
}
