import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

    public static String deleteSpaces(String word) {
        return word.replace(" ", "");
    }


    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress("localhost", 25000));


        try (SocketChannel client = server.accept()) {
            final ByteBuffer inputBuffer = ByteBuffer.allocate(1 << 10);
            final ByteBuffer out = ByteBuffer.allocate(2 << 10);

            while (client.isConnected()) {
                int bytesCount = client.read(inputBuffer);

                if (bytesCount == -1) break;

                String msg = new String(inputBuffer.array(), 0, bytesCount).trim();

                if (msg.equalsIgnoreCase("end")) break;

                out.put(ByteBuffer.wrap(deleteSpaces(msg).getBytes()));
                inputBuffer.clear();
            }
            out.clear();
            client.write(out);
        }
    }
}
