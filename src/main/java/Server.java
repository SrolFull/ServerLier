import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Server extends Thread {
    private File file = new File("C:\\Users\\srolf\\IdeaProjects\\Task2\\src\\main\\java\\data.txt");
    private DatagramSocket socket;
    private boolean running = false;
    private byte[] buf = new byte[256];
    private long adding_time;

    public Server() throws SocketException, FileNotFoundException {
        socket = new DatagramSocket(123);
        Scanner scanner = new Scanner(file);
        adding_time = scanner.nextInt();
    }

    public void run() {
        running = true;
        while(running){
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                buf = LocalTime.now().plusSeconds(adding_time).toString().getBytes();
                packet = new DatagramPacket(buf, buf.length, address, port);
                String received = new String(packet.getData(), 0, packet.getLength());
                if (received.equals("End")) {
                    close();
                    continue;
                }
                socket.send(packet);
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }
    public void close(){
        running = false;
    }
}
