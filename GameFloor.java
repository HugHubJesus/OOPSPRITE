import java.awt.*;

public class GameFloor extends GameObject {
    private boolean solid = true;   // false = สะพานที่ยังไม่โผล่ เหยียบไม่ได้ ไม่วาด

    public GameFloor(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    @Override
    public void draw(Graphics2D g) {
        if (!solid) return;
        g.setColor(new Color(139, 69, 19)); //สีน้ำตาลลลลล
        g.fill(bounds);
    }
}
