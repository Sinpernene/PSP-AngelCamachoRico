package ejercicio7;

public class Vehiculo {
    private Motor motor = null;
    private Bateria bateria = null;
    private Carroceria carroceria = null;

    public synchronized void ensamblarMotor(Motor motor) {
        if (carroceria != null)
            this.motor = motor;
        else
        {
            try {
                System.out.println("Esperando a la carrocería para ensamblar el motor");
                wait();
                if (carroceria != null) {
                    this.motor = motor;
                } else {
                    System.out.println("Error, no se puede ensamblar el motor sin carrocería");
                }
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, failed to wait for carrocería");
            }
        }
    }

    public synchronized void ensamblarBateria(Bateria bateria) {
        if (carroceria != null)
            this.bateria = bateria;
        else {
        try {
            System.out.println("Esperando  a la carrocería para ensamblar batería");
            wait();
            if (carroceria != null) {
                this.bateria = bateria;
            } else {
                System.out.println("Error, no se puede ensamblar la batería sin carrocería");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted, failed to wait for carrocería");
        }
        }
    }

    public synchronized void ensamblarCarroceria(Carroceria carroceria) {
        this.carroceria = carroceria;
        notifyAll();
    }

    public Motor getMotor() {
        return motor;
    }

    public Bateria getBateria() {
        return bateria;
    }

    public Carroceria getCarroceria() {
        return carroceria;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "motor=" + motor +
                ", bateria=" + bateria +
                ", carroceria=" + carroceria +
                '}';
    }
}
