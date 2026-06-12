package RéseauNonSegmenté;

import Modele.*;

import java.util.Arrays;
import java.util.List;

/**
 * scénario du réseau non segmenté.
 *
 * initialisation  des machines + les connexions entre elles
 * + lance les simulations de propagation du ver.
 *
 */
public class Main {

    /**
     * Lance plusieurs simulations du scénario non segmenté +
     *  statistiques de contamination.
     *
     *On s'occuppe d'abord du serveur d'applications
     * et la base de données = appartiennent au chemin critique
     */
    public static void runStatistics() {

        int runs = 100;
        int steps = 10;

        SimulationStats stats = new SimulationStats(runs);

        for (int i = 0; i < runs; i++) {

            ScenarioResult result = buildScenario();

            SimulateVer simulation = new SimulateVer(result.getGraph());

            simulation.simulateInfection(result.getMachines(), steps);

            stats.record(result.getMachines());
        }

        stats.printResults("Réseau non segmenté");
    }

    /* A décommenter si on veut avoir le graphe
    public static void main(String[] args) {

        runScenario();
    }
    */
/*
    * scénario du réseau non segmenté.
    *
    * objet: def machines du réseau +  état initial
    * +  connexions entre les machines avec leurs protocoles
    * et leurs probabilités de propagation.
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
     * simulation simple du scénario non segmenté.
     *
     * affiche le graphe +lance la propagation
     * du ver.
     */

    public static void runScenario() {

        ScenarioResult result = buildScenario();

        result.getGraph().printGraph();

        SimulateVer simulation = new SimulateVer(result.getGraph());

        simulation.simulateInfection(result.getMachines(), 10);
    }

        /*
        public static void runScenario(){
            NetworkGraph graph = new NetworkGraph();
            // définition des Machines et de leurs états
            Machine internet = new Machine("Internet", MachineState.IMMUNE);
            Machine siteWeb = new Machine("Site Web", MachineState.SUSCEPTIBLE);
            Machine serveurWeb = new Machine("Serveur Web", MachineState.SUSCEPTIBLE);
            Machine serveurApp = new Machine("Serveur Applications", MachineState.SUSCEPTIBLE);
            Machine database = new Machine("Base de données", MachineState.SUSCEPTIBLE);
            Machine serveurCloud = new Machine("Serveur Cloud", MachineState.SUSCEPTIBLE);
            Machine vpnGateway = new Machine("VPN Gateway", MachineState.IMMUNE);
            Machine posteAdmin = new Machine("Poste Admin", MachineState.INFECTED);

            //définition des connexions en tre les machines et leurs probabilités
            graph.addConnexion(internet, siteWeb, Protocol.HTTP, 0.30);
            graph.addConnexion(siteWeb, serveurWeb, Protocol.HTTP, 0.50);
            graph.addConnexion(serveurWeb, serveurApp, Protocol.SMB, 0.70);
            graph.addConnexion(serveurWeb, serveurApp, Protocol.SMB, 0.50);
            graph.addConnexion(serveurApp, database, Protocol.SQL, 0.30);
            graph.addConnexion(serveurCloud, vpnGateway, Protocol.RDP, 0.40);
            graph.addConnexion(vpnGateway, serveurApp, Protocol.VPN, 0.20);
            graph.addConnexion(posteAdmin, serveurApp, Protocol.SQL, 0.30);
            graph.addConnexion(serveurApp, database, Protocol.RDP, 0.60);

            graph.printGraph();

            serveurCloud.setState(MachineState.INFECTED);

            List<Machine> machines = Arrays.asList(
                    internet,
                    siteWeb,
                    serveurWeb,
                    serveurApp,
                    database,
                    serveurCloud,
                    vpnGateway,
                    posteAdmin
            );

            SimulateVer simulation = new SimulateVer(graph);
            simulation.simulateInfection(machines, 10);


        }
        */
    }
