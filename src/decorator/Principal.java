
package decorator;

public class Principal {

    public static void main(String[] args) {
        IPizza pizzaejemplo = new pizzaNapolitana();
        IPizza pizzaejem2 = new pizzaPepperoni();
        pizzaejem2 = new extraBarbeque(pizzaejem2);
        System.out.println(pizzaejem2.getDescripcion()+" + "+pizzaejem2.getPrecio());
    }
    
}
