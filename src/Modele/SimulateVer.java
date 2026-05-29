package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulateVer {

    private NetworkGraph graph;
    private Random random;

    public SimulateVer(NetworkGraph graph) {
        this.graph = graph;
        this.random = new Random();
    }

    public void simulateInfection(List<Machine> machines, int steps) {

        for (int step = 1; step <= steps; step++) {

            List<Machine> newlyInfected = new ArrayList<>();

            for (Machine machine : machines) {

                if (machine.getState() == MachineState.INFECTED) {

                    for (Edge edge : graph.getNeighbors(machine)) {

                        Machine target = edge.getTarget();
                        double probability = edge.getConnexion().getProbability();

                        if (target.getState() == MachineState.SUSCEPTIBLE) {

                            double draw = random.nextDouble();

                            if (draw < probability) {
                                newlyInfected.add(target);
                            }
                        }
                    }
                }
            }

            for (Machine machine : newlyInfected) {
                machine.setState(MachineState.INFECTED);
            }

            System.out.println("Étape " + step + " :");

            for (Machine machine : machines) {
                System.out.println(machine.getName() + " : " + machine.getState());
            }

            System.out.println();
        }
    }
}