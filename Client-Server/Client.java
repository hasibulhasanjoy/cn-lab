import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5500);
        System.out.println("connected to the server");
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        pw.println("HI");
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        for (int i = 0; i < 9; i++) {
            System.out.println(br.readLine());
        }
        s.close();
    }
}
