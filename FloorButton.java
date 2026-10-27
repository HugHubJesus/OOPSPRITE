import java.awt.*;

// ปุ่มบนพื้น (ด่านเล่น 2 คน) มีคนยืนทับอยู่ = ปุ่มถูกกด
public class FloorButton extends GameObject {
    private boolean pressed = false;

    public FloorButton(int x, int y) {
        super(x, y, 40, 10);
    }

    public boolean isPressed() {
        return pressed;
    }

    // เช็คว่าไก่ตัวเรา หรือไก่เพื่อน ยืนทับอยู่มั้ย (friend = null คือไม่มีเพื่อนในด่านนี้)
    public void check(Player player, Player friend) {
        pressed = player.getBounds().intersects(bounds);
        if (friend != null && friend.getBounds().intersects(bounds)) {
            pressed = true;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if (pressed) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.RED);
        }
        g.fill(bounds);
    }
}
