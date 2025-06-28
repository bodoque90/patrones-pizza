
package decorator;


public class extraBarbeque extends ingredienteBase{
    public extraBarbeque(IPizza _base){
        super(_base);
    }

    @Override
    public String getDescripcion() {
        return this.base.getDescripcion() + " + extra barbeque";
    }

    @Override
    public int getPrecio() {
        return this.base.getPrecio() + 2000;
    }
    
}
