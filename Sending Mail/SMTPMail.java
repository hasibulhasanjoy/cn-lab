import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Base64;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class SMTPMail {

    private static DataOutputStream dataOutputStream;
    private static BufferedReader bufferedReader;

    private static void sendCommand(String command) throws Exception {
        dataOutputStream.writeBytes(command + "\r\n");
        Thread.sleep(1000);
        System.out.println("Client: " + command);
    }

    private static void getResponse(int count) throws Exception {
        for (int i = 0; i < count; i++) {
            System.out.println("Server: " + bufferedReader.readLine());
        }
    }

    private static String getMacAddress() throws Exception {
        InetAddress ip = InetAddress.getLocalHost();
        return ip.toString();
    }

    public static void main(String[] args) throws Exception {
        String username = new String(Base64.getEncoder().encode(Constant.user.getBytes()));
        String password = new String(Base64.getEncoder().encode(Constant.pass.getBytes()));
        SSLSocket sslSocket = (SSLSocket) SSLSocketFactory.getDefault().createSocket(
                Constant.smtpServer,
                Constant.smtpPort);
        dataOutputStream = new DataOutputStream(sslSocket.getOutputStream());
        bufferedReader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
        sendCommand("EHLO " + Constant.smtpServer);
        getResponse(9);
        sendCommand("AUTH LOGIN");
        getResponse(1);
        sendCommand(username);
        getResponse(1);
        sendCommand(password);
        getResponse(1);
        sendCommand("MAIL FROM:<" + Constant.user + ">");
        getResponse(1);
        sendCommand("RCPT TO:<hasibulhasanjoy.007@gmail.com>");
        getResponse(1);
        sendCommand("DATA");
        getResponse(1);
        sendCommand("FROM: " + Constant.user);
        sendCommand("TO: hasibulhasanjoy.007@gmail.com");
        sendCommand("Subject: Testing Email");
        sendCommand("This is a testing Email.");
        sendCommand(getMacAddress());
        sendCommand(".");
        getResponse(1);
        sendCommand("QUIT");
        getResponse(1);
    }

}