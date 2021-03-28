import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        SocketChannel socket = SocketChannel.open();
        socket.connect(new InetSocketAddress("localhost", 25000));

        try (Scanner scanner = new Scanner(System.in)) {
            String msg;
            while (true) {
                System.out.println("Enter new line or end");
                msg = scanner.nextLine();

                socket.write(ByteBuffer.wrap(msg.getBytes()));
                if (msg.equalsIgnoreCase("end")) break;
            }
            ByteBuffer buffer = ByteBuffer.allocate(2 << 10);
            int bytesCount = socket.read(buffer);
            System.out.println(bytesCount);
            System.out.println("Server: " + new String(buffer.array(), 0, bytesCount).trim());
        }
    }
}
