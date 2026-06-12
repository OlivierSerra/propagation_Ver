package Modele;

import java.util.*;

/**
 * Représente le réseau informatique sous la forme
 * d'un graphe orienté pondéré.
 *
 * Les sommets du graphe sont représentés par des Machines et les arêtes par des Edge.
 *
 * Chaque arête contient :
 * - un protocole de communication ;
 * - une probabilité de propagation du ver.
 *
 * Cette classe permet de construire l'architecture du réseau
 * et de récupérer les connexions utilisées lors de la simulation
 * de propagation.
 */

public class NetworkGraph {

    private Map<Machine, List<Edge>> graph;

    /**
     * Initialise un graphe vide.
     */
    public NetworkGraph() {

        graph= new HashMap<>();
    }

    /**
     * Ajoute une machine au graphe.
     *
     * Si la machine existe déjà, aucune modification n'est effectuée.
     *
     * @param machine machine à ajouter
     */
    public void addMachine(Machine machine) {

        graph.putIfAbsent(machine, new ArrayList<>());
    }

    /**
     * Ajoute une connexion orientée entre deux machines.
     *
     * Une connexion est caractérisée par :
     * - une machine source ;
     * - une machine cible ;
     * - un protocole ;
     * - une probabilité de propagation.
     *
     */
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

    /**
     * Retourne la liste des connexions sortantes
     * d'une machine.
     *
     * @param machine machine dont on souhaite obtenir les voisins
     * @return liste des arêtes sortantes
     */
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