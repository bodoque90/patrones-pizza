package decorator;

public class extraQueso extends ingredienteBase {

    public extraQueso(IPizza _base) {
        super(_base);
    }

    @Override
    public String getDescripcion() {
        return this.base.getDescripcion() + " extra queso";
    }

    @Override
    public int getPrecio() {
        return this.base.getPrecio() + 3200;
    }

}
