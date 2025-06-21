
package decorator;


public abstract class ingredienteBase implements IPizza{
    protected IPizza base;
    
    public ingredienteBase(IPizza _base){
    this.base = _base;
    }
    
    @Override
    public abstract String getDescripcion();
    
    @Override
    public abstract int getPrecio();
}
