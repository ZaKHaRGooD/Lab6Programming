package lab6.com.client.handlers;

import lab6.com.client.CommandListener;
import lab6.com.common.networkhub.Request;
import lab6.com.common.networkhub.Response;
import lab6.com.common.networkhub.Serializer;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 */
public class ClientListener {

    private final int SERVER_PORT = 5432;
    private boolean performanceStatus;
    private InputStream stream;

    public ClientListener(InputStream stream) {
        this.stream = stream;
        performanceStatus = true;
    }

    public void startListen() {

        DatagramSocket clientSocket = null;
        InetAddress IPAddress = null;
        try {
            clientSocket = new DatagramSocket();
            IPAddress = InetAddress.getByName("localhost");
        } catch (SocketException ex) {
            System.out.println("ClientListener: SocketException");
        } catch (UnknownHostException ex) {
            System.out.println("ClientListener: адреса не существует");
        }

        CommandListener commandListener = new CommandListener(stream);
        Serializer serializer = new Serializer();
        while (performanceStatus) {
            try {
                String[] command = commandListener.readCommand();

                if ("exit".equals(command[0])) {
                    performanceStatus = false;
                    continue;
                }

                ArrayList<Object> args = null;
                args.addAll(Arrays.asList(command).subList(1, command.length));

                // мутим реквест
                Request request = new Request(command[0], args);
                byte[] send = serializer.serialize(request);
                DatagramPacket dpRequest = new DatagramPacket(send, send.length, IPAddress, SERVER_PORT);
                clientSocket.send(dpRequest);

                // получаем ответ
                byte[] receive = new byte[1024];
                DatagramPacket dpResponse = new DatagramPacket(receive, receive.length);
                clientSocket.receive(dpResponse);
                Response response = (Response) serializer.deserialize(dpResponse.getData());
                System.out.println("Ответ от сервера " + response.getMessage());
            } catch (IOException ex) {
                System.out.println("ClientListener: IOException");
            }
        }
        clientSocket.close();
    }
}
