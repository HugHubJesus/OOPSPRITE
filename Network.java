import java.io.*;
import java.net.*;

// ส่ง/รับตำแหน่งไก่กับเพื่อน ข้อความ 1 บรรทัด = "ด่าน x y จำนวนครั้งที่ตาย"
public class Network {
    private static final int PORT = 50101;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    // ข้อมูลไก่เพื่อนล่าสุด (friendLevel = 0 คือเพื่อนยังไม่มา หรือหลุดไปแล้ว)
    public int friendLevel = 0;
    public int friendX, friendY;
    public int friendDeaths = 0;

    // ip = null คือเปิดห้องเป็น Server, มี ip คือเข้าห้องเป็น Client
    public Network(String ip) throws IOException {
        if (ip == null) {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server เปิดรอที่พอร์ต " + PORT + " ...");
            socket = serverSocket.accept();            // รอจนเพื่อนต่อเข้ามา
        } else {
            socket = new Socket(ip, PORT);             // ต่อไปหา Server
        }
        System.out.println("เชื่อมต่อเพื่อนแล้ว");

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Thread คอยรับตำแหน่งเพื่อนตลอดเวลา
        Thread receiver = new Thread(new Runnable() {
            public void run() {
                try {
                    String line;
                    while ((line = in.readLine()) != null) {
                        String[] part = line.split(" ");
                        friendX = Integer.parseInt(part[1]);
                        friendY = Integer.parseInt(part[2]);
                        friendDeaths = Integer.parseInt(part[3]);
                        friendLevel = Integer.parseInt(part[0]);
                    }
                } catch (IOException e) {
                }
                System.out.println("--- เพื่อนออกจากเกม ---");
                friendLevel = 0;
            }
        });
        receiver.start();
    }

    public void send(int level, int x, int y, int deaths) {
        out.println(level + " " + x + " " + y + " " + deaths);
    }
}
