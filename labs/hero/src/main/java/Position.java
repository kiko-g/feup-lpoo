public class Position
{
   private int x;
   private int y;

   public Position(int x0, int y0){
      this.x = x0;
      this.y = y0;
   }

   public void setPosition(Position p){
      this.x = p.x;
      this.y = p.y;
   }

   public int getX() { return x; }
   public int getY() { return y; }

   @Override
   public boolean equals(Object o)
   {
      if(o == null) return false;
      if(this == o) return true;
      if(getClass() != o.getClass()) return false;

      Position p = (Position)o;
      return (p.getX() == x && p.getY() == y);
   }

}
