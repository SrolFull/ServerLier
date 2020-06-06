import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

public class Main {
    private static Client client;
    public static void main(String[] args) throws IOException {
        new Server().start();
        client = new Client();
        String echo = client.sendMsg("Time");
        System.out.println(echo);
        client.sendMsg("End");
    }
}
