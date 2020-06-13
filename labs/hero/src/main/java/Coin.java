import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element
{
   public Coin(int x, int y, String s) {
      super(x, y, s);
   }


   @Override
   public void draw(TextGraphics graphics) {
      graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
      graphics.enableModifiers(SGR.BOLD, SGR.BORDERED, SGR.FRAKTUR);
      graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), getSymbol());
   }


}
