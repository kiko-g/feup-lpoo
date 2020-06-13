public class StringReplacer implements StringTransformer
{
   private char in;
   private char out;

   public StringReplacer(char in, char out) {
      this.in = in;
      this.out = out;
   }

   public void execute(StringDrink drink) {
        StringBuffer buff = new StringBuffer();

        for (int i = 0; i < drink.getText().length(); i++) {
            char c = drink.getText().charAt(i);
            if (c == in) buff.append(out);
            else buff.append(c);
        }

        drink.setText(buff.toString());
   }
}
