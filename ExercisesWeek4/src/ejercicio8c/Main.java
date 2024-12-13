package ejercicio8c;

public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        Thread producer1 = new Thread(new ProducerThread("Geisha", 9, container));
        Thread producer2 = new Thread(new ProducerThread("Geisha", 9, container));
        Thread producer3 = new Thread(new ProducerThread("Geisha", 9, container));
        Thread producer4 = new Thread(new ProducerThread("Geisha", 9, container));
        Thread customer = new Thread(new CustomerThread(container));

        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        customer.start();
    }
}
