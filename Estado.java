package TresEnRaya;

public enum Estado {
    X("X"), O("O"), Undefined("-");
    private final String STATE_VALUE;

    Estado(String s){
        STATE_VALUE = s;
    }

    public String getSTATE_VALUE() {
        return STATE_VALUE;
    }
}
