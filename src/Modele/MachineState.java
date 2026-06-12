package Modele;

/**
 * Représente les états possibles d'une machine.
 * au cours d'une simulation
 *
 * SUSCEPTIBLE = la machine peut être infecté
 * INFECTED = machine infectée et peut contaminer d'autres machines.
 * IMMUNE = machine protégée
 * BLOCKED = mahcine isolé du réseau suite ç=à une détection
 * RECOVERED = machine néttoyé et remise en service
 *
 * */
public enum MachineState {
    SUSCEPTIBLE,
    INFECTED,
    IMMUNE,
    BLOCKED,
    RECOVERED,
}
