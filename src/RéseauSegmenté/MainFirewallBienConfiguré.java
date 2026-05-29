package RéseauSegmenté;

import Modele.*;

import java.util.Arrays;
import java.util.List;

public class MainFirewallBienConfiguré {

    public static void main(String[] args) {
        runScenario();
    }

    public static void runScenario() {
        NetworkGraph graph = new NetworkGraph();
        // définition des Machines et de leurs états
        Machine internet = new Machine("Internet", MachineState.IMMUNE);
        Machine siteWeb = new Machine("Site Web", MachineState.SUSCEPTIBLE);
        Machine serveurWeb = new Machine("Serveur Web", MachineState.SUSCEPTIBLE);
        Machine serveurApp = new Machine("Serveur Applications", MachineState.SUSCEPTIBLE);
        Machine database = new Machine("Base de données", MachineState.SUSCEPTIBLE);
        Machine firewall = new Machine("Firewall", MachineState.IMMUNE);
        Machine serveurCloud = new Machine("Serveur Cloud", MachineState.INFECTED);
        Machine vpnGateway = new Machine("VPN Gateway", MachineState.IMMUNE);
        Machine posteAdmin = new Machine("Poste Admin", MachineState.INFECTED);

        //définition des connexions en tre les machines et leurs probabilités
        graph.addConnexion(internet, siteWeb, Protocol.HTTP, 0.30);

        graph.addConnexion(serveurCloud, vpnGateway, Protocol.RDP, 0.40);
        graph.addConnexion(vpnGateway,firewall , Protocol.VPN, 0.10);
        //graph.addConnexion(vpnGateway, serveurApp, Protocol.VPN, 0.20);

        //graph.addConnexion(serveurApp, database, Protocol.RDP, 0.60);
        graph.addConnexion(serveurApp, firewall, Protocol.RDP, 0.05);

        graph.addConnexion(siteWeb, serveurWeb, Protocol.HTTP, 0.50);
        //graph.addConnexion(serveurWeb, serveurApp, Protocol.SMB, 0.70);
        graph.addConnexion(serveurWeb, firewall, Protocol.SMB, 0.20);
        //graph.addConnexion(serveurWeb, serveurApp, Protocol.SMB, 0.50);
        //graph.addConnexion(posteAdmin, serveurApp, Protocol.SQL, 0.30);
        graph.addConnexion(posteAdmin, firewall, Protocol.SQL, 0.10);
        graph.addConnexion(firewall, serveurApp, Protocol.SMB, 0.10);
        graph.addConnexion(firewall, serveurApp, Protocol.SQL, 0.05);
        graph.addConnexion(firewall, serveurApp, Protocol.RDP, 0.10);
        graph.addConnexion(firewall, serveurApp, Protocol.RDP, 0.05);
        graph.addConnexion(firewall, database, Protocol.RDP, 0.01);



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
                posteAdmin,
                firewall
        );

        SimulateVer simulation = new SimulateVer(graph);
        simulation.simulateInfection(machines, 5);

    }
}


