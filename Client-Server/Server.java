import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5500);
        System.out.println("Server is running on port 5500");
        Socket s = ss.accept();
        System.out.println("Client connected");

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        System.out.println(br.readLine());
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        for (int i = 0; i < 9; i++) {
            pw.println("Response" + i);
        }
        ss.close();
    }
}