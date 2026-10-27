import java.awt.*;

// ประตูทางออก เดินชนแล้วผ่านด่าน
// กับดัก: ถ้า runToX ไม่เท่ากับ x พอผู้เล่นเข้าใกล้ ประตูจะหนีไปอยู่ที่ runToX
public class Door extends GameObject {
    private int runToX;

    public Door(int x, int y, int runToX) {
        super(x, y, 40, 60);
        this.runToX = runToX;
    }

    @Override
    public void update(Player player) {
        if (Math.abs(player.getBounds().x - bounds.x) < 120) {
            bounds.x = runToX;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(0, 150, 0));   // เดี๋ยวค่อยเปลี่ยนเป็นรูป
        g.fill(bounds);
    }
}
