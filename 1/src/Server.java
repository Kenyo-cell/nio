import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static int fibonacci(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        int[] series = new int[n + 1];
        series[0] = 0;
        series[1] = 1;

        for (int i = 2; i <= n; i++) {
            series[i] = series[i - 1] + series[i - 2];
        }

        return series[n];
    }

    public static void main(String[] args) throws IOException {
        final ServerSocket server = new ServerSocket(25000);

        while (true) {
            try (Socket client = server.accept();
                 PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))
            ) {
                if (client.isConnected()) {
                    System.out.println("Server reading");

                    int n = Integer.parseInt(in.readLine());
                    System.out.println("Server get from client: " + n);
                    out.println(fibonacci(n));
                }
            }
        }
    }
}
