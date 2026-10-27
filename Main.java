import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String[] modes = {"Single Player", "Host Game (Server)", "Join Game (Client)"};
        int choice = JOptionPane.showOptionDialog(null, "Select game mode", "Cool Head Game",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, modes, modes[0]);
        if (choice == -1) return;   // กดปิดหน้าต่าง

        Network network = null;
        try {
            if (choice == 1) {
                network = new Network(null);   // รอเพื่อนต่อเข้ามา (ดูข้อความในคอนโซล)
            } else if (choice == 2) {
                String ip = JOptionPane.showInputDialog("Enter host (Server) IP address", "127.0.0.1");
                if (ip == null) return;
                network = new Network(ip);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot connect: " + e.getMessage());
            return;
        }
        new GameFrame(network);
    }
}
