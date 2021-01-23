import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private DataInputStream dis;
    private DataOutputStream dos;
    private FileInputStream fis;
    private String target;
    private String fileName;
    private Socket socket;
    private ServerSocket serverSocket;

    public Server() {
        run(50126);
    }

    private void run(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is starting up...");
            System.out.println("Servers waiting for connection...");
            socket = serverSocket.accept();
            System.out.println("Client connected: " + socket);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

            while (true) {
                target = dis.readUTF();
                System.out.println(target);
                fileName = dis.readUTF();
                System.out.println(fileName);
                String fullPath = target + fileName;
                System.out.println(fullPath);

                fis = new FileInputStream(fullPath);
                int read;
                byte[] buffer = new byte[8];
                while ((read = fis.read(buffer)) != -1) {
                    dos.write(buffer, 0, read);
                }
                dos.flush();
            }

        } catch (IOException ex) {
            throw new RuntimeException("SWW");
        }
    }
}