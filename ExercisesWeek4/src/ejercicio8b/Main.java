package ejercicio8b;

public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        Thread producer1 = new Thread(new ProducerThread("Geisha", 9, container));
        Thread customer = new Thread(new CustomerThread(container));

        producer1.start();
        customer.start();
    }
}
