package Modele;

import java.util.List;

public class ScenarioResult {

    private NetworkGraph graph;
    private List<Machine> machines;

    /**
     * Représente le résultat de la construction d'un scénario.
     *
     * Cette classe regroupe les éléments nécessaires à l'exécution
     * d'une simulation :
     * - le graphe représentant l'architecture du réseau ;
     * - la liste des machines composant le réseau.
     *
     * Elle permet de retourner ces informations sous la forme
     * d'un seul objet lors de la création d'un scénario.
     */
    public ScenarioResult(NetworkGraph graph, List<Machine> machines) {
        this.graph = graph;
        this.machines = machines;
    }

    /**
     * Retourne le graphe du scénario.
     *
     */
    public NetworkGraph getGraph() {
        return graph;
    }

    /**
     * Retourne la liste des machines du scénario.
     *
     */
    public List<Machine> getMachines() {
        return machines;
    }

}
