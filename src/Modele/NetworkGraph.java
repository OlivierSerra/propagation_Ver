package Modele;

import java.util.*;

public class NetworkGraph {

    private Map<Machine, List<Edge>> graph;

    public NetworkGraph() {
        graph = new HashMap<>();
    }

    public void addMachine(Machine machine) {
        graph.putIfAbsent(machine, new ArrayList<>());
    }

    public void addConnexion(
            Machine source,
            Machine target,
            Protocol protocol,
            double probability
    ) {
        addMachine(source);
        addMachine(target);

        Connexion connexion = new Connexion(protocol, probability);
        Edge edge = new Edge(target, connexion);

        graph.get(source).add(edge);
    }

    public List<Edge> getNeighbors(Machine machine) {
        return graph.get(machine);
    }

    public void printGraph() {
        for (Machine source : graph.keySet()) {
            System.out.println("\nModele.Machine : " + source.getName());

            for (Edge edge : graph.get(source)) {
                System.out.println(
                        " -> "
                                + edge.getTarget().getName()
                                + " | "
                                + edge.getConnexion().getProtocol()
                                + " | probabilité : "
                                + edge.getConnexion().getProbability()
                );
            }
        }
    }
}