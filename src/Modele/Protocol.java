package Modele;

/**
 * Représente les différents protocoles de communication
 * utilisés dans le modèle du réseau.
 *
 * Chaque protocole est associé à une probabilité de propagation
 * définie dans les connexions du graphe.
 *
 * Certains protocoles, comme SMB ou RDP, sont fréquemment
 * ciblés par les vers informatiques pour se propager entre
 * les machines d'un réseau.
 */

public enum Protocol {
    HTTP,
    SMB,
    RDP,
    SQL,
    VPN,
}
