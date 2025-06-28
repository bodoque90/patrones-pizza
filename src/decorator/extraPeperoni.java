
package decorator;

public class extraPeperoni extends ingredienteBase {

    public extraPeperoni(IPizza _base) {
        super(_base);
    }

    @Override
    public String getDescripcion() {
        return this.base.getDescripcion() + " + extra peperoni";
    }

    @Override
    public int getPrecio() {
        return this.base.getPrecio() + 2240;
    }

}
