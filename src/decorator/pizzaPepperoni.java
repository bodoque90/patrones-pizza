
package decorator;


public class pizzaPepperoni implements IPizza{

    @Override
    public String getDescripcion() {
         return "Pizza de pepperoni";
    }

    @Override
    public int getPrecio() {
       return 8400;
    }
    
}
