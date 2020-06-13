public class StringInverter implements StringTransformer
{
   public StringInverter() {
   }

   public void execute(StringDrink drink) {
      StringBuffer buff = new StringBuffer(drink.getText());
      buff.reverse();
      drink.setText(buff.toString());
   }
}