package Modele;

import java.util.List;

/**
 * Centralise la gestion des statistiques des simulations.
 *
 * Cette classe permet de comptabiliser le nombre de fois
 * où certaines machines critiques sont infectées lors
 * d'une série de simulations.
 *
 * Les résultats sont ensuite convertis en pourcentages
 * afin d'évaluer l'efficacité des différentes architectures
 * réseau étudiées.
 */


public class SimulationStats {

    private int totalRuns;
    private double serveurAppInfectedCount;
    private double databaseInfectedCount;

    /**
     * Initialise un objet de statistiques.
     *
     *
     */
    public SimulationStats(int totalRuns ) {
        this.totalRuns = totalRuns;
    }

    /**
     * Enregistre le résultat d'une simulation.
     *
     * vérifie si le serveur d'applications
     * + base de données sont infectés à la fin de la
     * simulation + mise à jour les compteurs associés.
     *
     */
    public void record (List<Machine> machines) {
        for (Machine machine : machines) {
            if (machine.getName().equals("Serveur Applications")
            && machine.getState() == MachineState.INFECTED) {
                serveurAppInfectedCount++;
            }

            if (machine.getName().equals("Base de données")
            && machine.getState() == MachineState.INFECTED) {
                databaseInfectedCount++;
            }
        }
    }

    /**
     * Affiche statistiques pour un scénario.
     *
     * exprime les pourcentages de contamination et de protection.
     *
     */
    public void printResults(String scenarioName) {

        System.out.println("\n====== STATISTIQUES : " + scenarioName + " ======");
        System.out.println("Nombre de simulations : " + totalRuns);

        System.out.println("Serveur Applications Infecté : " + (serveurAppInfectedCount * 100 / totalRuns));
        System.out.println("Serveur Applications protégé : " + (100.0 - (serveurAppInfectedCount * 100 / totalRuns)));

        System.out.println("Base de données infectée : " + (databaseInfectedCount * 100 / totalRuns));

        System.out.println("Base de données protégée : " + (100.0 - (databaseInfectedCount * 100 / totalRuns)));
    }
}
