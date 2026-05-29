package Modele;

public class Machine {
    private String name;
    private MachineState state;

    public Machine(String name, MachineState state){
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public MachineState getState() {
        return state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }
}
