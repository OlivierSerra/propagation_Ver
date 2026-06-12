package RéseauSegmentéMalConfiguré;

import Modele.*;

import java.util.Arrays;
import java.util.List;

/**
 * scénario du réseau segmenté
 * avec un firewall mal configuré.
 *
 * Réseau avec mécanisme de segmentation mais règles
 * de filtrage  insuffisantes pour empêcherla propagation du ver.
 *
 * permet d'évaluer l'impact d'une mauvaise
 * configuration du firewall sur le niveau global
 * de sécurité du réseau.
 */

public class MainFirewallMalConfiguré {

    /**
     *
     *
     * Lance calcul des statistiques
     * de contamination pour ce scénario.
     *
     */
    public static void main(String[] args) {
        runStatistics();
    }

    /**
     * scénario du réseau segmenté
     * avec un firewall mal configuré.
     *
     * Définition: connexions du graphe avec des probabilités
     * relativement élevées car filtrage
     * insuffisant des communications réseau.
     *
     */
    public static ScenarioResult buildScenario() {

        NetworkGraph graph = new NetworkGraph();

        Machine internet = new Machine("Internet", MachineState.IMMUNE);
        Machine siteWeb = new Machine("Site Web", MachineState.SUSCEPTIBLE);
        Machine serveurWeb = new Machine("Serveur Web", MachineState.SUSCEPTIBLE);
        Machine serveurApp = new Machine("Serveur Applications", MachineState.SUSCEPTIBLE);
        Machine database = new Machine("Base de données", MachineState.SUSCEPTIBLE);
        Machine firewall = new Machine("Firewall", MachineState.SUSCEPTIBLE);
        Machine serveurCloud = new Machine("Serveur Cloud", MachineState.INFECTED);
        Machine vpnGateway = new Machine("VPN Gateway", MachineState.IMMUNE);
        Machine posteAdmin = new Machine("Poste Admin", MachineState.INFECTED);

        graph.addConnexion(internet, siteWeb, Protocol.HTTP, 0.30);
        graph.addConnexion(siteWeb, serveurWeb, Protocol.HTTP, 0.50);

        graph.addConnexion(serveurCloud, vpnGateway, Protocol.RDP, 0.40);
        graph.addConnexion(vpnGateway, firewall, Protocol.VPN, 0.20);

        graph.addConnexion(serveurWeb, firewall, Protocol.SMB, 0.40);
        graph.addConnexion(posteAdmin, firewall, Protocol.SQL, 0.40);

        graph.addConnexion(firewall, serveurApp, Protocol.SMB, 0.30);
        graph.addConnexion(firewall, serveurApp, Protocol.SQL, 0.30);
        graph.addConnexion(firewall, serveurApp, Protocol.RDP, 0.30);

        graph.addConnexion(serveurApp, firewall, Protocol.RDP, 0.30);
        graph.addConnexion(firewall, database, Protocol.RDP, 0.25);

        List<Machine> machines = Arrays.asList(
                internet,
                siteWeb,
                serveurWeb,
                serveurApp,
                database,
                serveurCloud,
                vpnGateway,
                posteAdmin,
                firewall
        );

        return new ScenarioResult(graph, machines);
    }

    /**
     * simulation simple de propagation.
     *
     * Graphe construit puis simulation du ver.
     */
    public static void runScenario() {

        ScenarioResult result = buildScenario();

        result.getGraph().printGraph();

        SimulateVer simulation = new SimulateVer(result.getGraph());

        simulation.simulateInfection(result.getMachines(), 10);
    }

    /**
     * Exécute plusieurs simulations du scénario
     * afin de calculer des statistiques de contamination.
     *
     * résultats = estimation du pourcentage d'infection du serveur d'applications
     * et de la base de données = cas d'un firewall
     * mal configuré.
     */
    public static void runStatistics() {

        int runs = 100;
        int steps = 10;

        SimulationStats stats = new SimulationStats(runs);

        for (int i = 0; i < runs; i++) {

            ScenarioResult result = buildScenario();

            SimulateVer simulation = new SimulateVer(result.getGraph());

            simulation.simulateInfection(
                    result.getMachines(),
                    steps
            );

            stats.record(result.getMachines());
        }

        stats.printResults("Réseau segmenté - firewall mal configuré");
    }
}