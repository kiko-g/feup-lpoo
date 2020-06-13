import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element
{
   private Position position;
   private String symbol;

   public Element(int x, int y, String s) {
      position = new Position(x, y);
      symbol = s;
   }

   public void setPosition(Position p) { position.setPosition(p); }
   public Position getPosition() { return position; }
   public String getSymbol() { return symbol; }
   public void setSymbol(String symbol) { this.symbol = symbol; }


   public abstract void draw(TextGraphics graphics);
}