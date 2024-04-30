package TresEnRaya;

public class Celda {
    private Estado estado;
    private boolean valueAssigned;

    public Celda() {
        estado = Estado.Undefined;
        valueAssigned = false;
    }

    public boolean setEstado(Estado estado) {
        if(!valueAssigned) {
            this.estado = estado;
            valueAssigned(true);
            return true;
        }else if(estado.equals(Estado.Undefined)){
            this.estado = estado;
            valueAssigned(false);
            return true;
        }
        return false;
    }

    private void valueAssigned(boolean b){
        valueAssigned = b;
    }

    public boolean isValueAssigned() {
        return valueAssigned;
    }

    public Estado getEstado() {
        return estado;
    }
}
