package lab6.com.client;

import lab6.com.client.handlers.ClientListener;

/**
 * Класс точка входа в програму
 */
public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void greet() {
        System.out.println("Приложение работает, добро пожаловать!\n"
                + "Вызовите \"help\", чтобы увидеть список команд.");
    }

    public static void main(String[] args) {
        ClientListener listener = new ClientListener(System.in);
        greet();
        listener.startListen();
    }
}
