package Modele;

public class Machine {
    private String name;
    private MachineState state;
/**
 *
 * c'est la descritpion d'une machine type.
 * correspond à un sommet en th"éorie des graphes
 *
 * Pour retrouver une machine on définit un nom et un état (lié à machineState)
 *
 *
 * */
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
