public class StringCaseChanger implements StringTransformer
{
   public StringCaseChanger() {
   }

   public void execute(StringDrink drink) {
      StringBuffer buff = new StringBuffer();
      for (int i = 0; i < drink.getText().length(); i++) {
         char c = drink.getText().charAt(i);
         if (Character.isLowerCase(c)) c = Character.toUpperCase(c);
         else c = Character.toLowerCase(c);
         buff.append(c);
      }
      drink.setText(buff.toString());
   }
}