package Modele;

public class Edge {
    private Machine target;
    private Connexion connexion;

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
