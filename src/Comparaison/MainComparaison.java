package Comparaison;

import RéseauSegmenté.MainFirewallBienConfiguré;
import RéseauSegmentéMalConfiguré.MainFirewallMalConfiguré;

public class MainComparaison {

    public static void main(String[] args) {

        System.out.println("===== RÉSEAU NON SEGMENTÉ =====");
        RéseauNonSegmenté.Main.runScenario();

        System.out.println("\n\n===== RÉSEAU SEGMENTÉ Firewall Mal Configuré=====");
        MainFirewallMalConfiguré.runScenario();

        System.out.println("\n\n===== RÉSEAU SEGMENTÉ Firewall Bien Configuré=====");
        MainFirewallBienConfiguré.runScenario();
    }
}