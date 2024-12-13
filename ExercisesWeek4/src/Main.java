import ejercicio8b.Container;
import ejercicio8b.CustomerThread;
import ejercicio8b.ProducerThread;

public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        Thread producer = new Thread(new ProducerThread("Geisha", 9, container));
        Thread customer = new Thread(new CustomerThread(container));

        producer.start();
        customer.start();
    }

}