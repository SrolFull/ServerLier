import java.io.IOException;
import java.net.*;
import java.time.LocalTime;

public class Client {
    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buf;

    public Client() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public String sendMsg(String msg) throws IOException {
        buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf,buf.length,address,123);
        socket.send(packet);
        packet = new DatagramPacket(LocalTime.now().toString().getBytes(),LocalTime.now().toString().getBytes().length);
        socket.receive(packet);
        return new String(
                packet.getData(),0,packet.getLength()
        );
    }
    public void close(){
        socket.close();
    }
}
