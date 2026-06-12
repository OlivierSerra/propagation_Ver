package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Moteur de simulation de propagation du ver informatique.
 *
 * Cette classe parcourt le graphe du réseau et simule
 * la propagation de l'infection entre les machines.
 *
 * À chaque étape :
 * - les machines infectées tentent de contaminer leurs voisins ;
 * - une probabilité est associée à chaque connexion ;
 * - un tirage aléatoire détermine si l'infection réussit ;
 * - les nouvelles infections sont appliquées en fin d'étape.
 *
 * Cette approche permet de modéliser la diffusion progressive
 * d'un ver informatique dans un réseau hybride.
 */

public class SimulateVer {

    private NetworkGraph graph;
    private Random random;

    /**
     * Initialise le moteur de simulation.
     *
     */

    public SimulateVer(NetworkGraph graph) {
        this.graph = graph;
        this.random = new Random();
    }

    /**
     * Simule la propagation du ver pendant un nombre
     * donné d'étapes.
     *
     * À chaque étape, les machines infectées tentent
     * d'infecter les machines voisines selon la probabilité
     * associée à chaque connexion.
     *
     */

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

            // à décommenter si on veut avoir toutes les étapes.
            /*System.out.println("Étape " + step + " :");

            for (Machine machine : machines) {
                System.out.println(machine.getName() + " : " + machine.getState());
            }

            System.out.println();
            */
        }
    }
}