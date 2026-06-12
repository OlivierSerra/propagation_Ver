package Modele;

public class Edge {
    private Machine target;
    private Connexion connexion;

    /**
     * Arête du graphe
     *
     * Une arête relie 2 machine entre elles
     * et contient les informations de connexion
     *
     * elle est le support du protocole et de la probabilité de propagation
     *
    * */
    public Edge(Machine target, Connexion connexion){
        this.target = target;
        this.connexion = connexion;
    }

    public Machine getTarget() {
        return target;
    }

    public Connexion getConnexion() {

        return connexion;
        }
}
