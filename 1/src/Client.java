import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        final Socket socket = new Socket("127.0.0.1", 25000);

        try (Scanner scanner = new Scanner(System.in);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Enter n-th Fibonacci number");
            int n = scanner.nextInt();

            out.println(n);
            System.out.println(in.readLine());
        }
    }
}
