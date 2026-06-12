package Comparaison;

import RéseauSegmenté.MainFirewallBienConfiguré;
import RéseauSegmentéMalConfiguré.MainFirewallMalConfiguré;

public class MainComparaison {
/**
* Classe principale qui compare les scénarii
 *
 * Il y a donc 3types de réseau (réseau non segmenté/ réseau segmenté firewall mak configuré / réseau segmenté firewall bien configuré)
 *
 *
 * Cette classe est utilisée pour afficher les graphes ou les statistiques après simulations
*
* */
    public static void main(String[] args) {

        /* a décommenter si on veut les graphes
        System.out.println("===== RÉSEAU NON SEGMENTÉ =====");
        RéseauNonSegmenté.Main.runScenario();

        System.out.println("\n\n===== RÉSEAU SEGMENTÉ Firewall Mal Configuré =====");
        MainFirewallMalConfiguré.runScenario();

        System.out.println("\n\n===== RÉSEAU SEGMENTÉ Firewall Bien Configuré =====");
        MainFirewallBienConfiguré.runScenario();
        */

        System.out.println("===== RÉSEAU NON SEGMENTÉ =====");
        RéseauNonSegmenté.Main.runStatistics();

        System.out.println("\n\n===== RÉSEAU SEGMENTÉ Firewall Mal Configuré =====");

        MainFirewallMalConfiguré.runStatistics();

        System.out.println("\n\n===== RÉSEAU SEGMENTÉ Firewall Bien Configuré =====");
        MainFirewallBienConfiguré.runStatistics();


    }
}