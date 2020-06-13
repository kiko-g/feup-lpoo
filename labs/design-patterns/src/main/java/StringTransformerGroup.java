import java.util.List;
// import java.util.ListIterator;

public class StringTransformerGroup implements StringTransformer {
   private List<StringTransformer> transformers;

   public StringTransformerGroup(List<StringTransformer> transformers) {
      this.transformers = transformers;
   }

   public void execute(StringDrink drink) {
      for (StringTransformer transformer : transformers) {
         transformer.execute(drink);
      }
   }
}