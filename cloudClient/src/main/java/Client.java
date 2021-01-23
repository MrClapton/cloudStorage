import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {

    private DataInputStream dis;
    private DataOutputStream dos;
    private FileOutputStream fos;
    private String fileName;
    private String target;
    private String destination;
    private Socket socket;
    private Scanner scanner;

    public Client() {
        connect("localhost", 50126);
    }

    private void connect(String adress, int port) {
        try {
            socket = new Socket(adress, port);
            System.out.println("Server is starting up...");
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

            System.out.println("Укажите путь до файла, который хотите скачать:");
            target = "C:\\Repositories\\cloudStorage\\testFile\\";
            System.out.println(target);

            System.out.println("укажите путь, куда хотите сохранить файл:");
            destination = "C:\\Repositories\\cloudStorage\\testFile\\";
            System.out.println(destination);

            System.out.println("yкажите имя файла:");
            fileName = "test1.txt";
            System.out.println(fileName);

            dos.writeUTF(target);
            dos.writeUTF(fileName);
            fos = new FileOutputStream(destination+fileName);
            int read;
            byte[] buffer = new byte[8];
            while((read = dis.read(buffer)) !=-1) {
                fos.write(buffer, 0, read);
            }
            fos.flush();
            System.out.println("operation successfully completed");

        }catch(IOException ex) {
            throw new RuntimeException("SWW", ex);
        }
    }
}