
package decorator;


public class pizzaNapolitana implements IPizza{

    @Override
    public String getDescripcion() {
        return "Pizza Napolitana";
    }

    @Override
    public int getPrecio() {
         return 7500;
    }
    
}
