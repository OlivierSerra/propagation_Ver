package Modele;

public class Connexion {
    private Protocol protocol;
    private double probability;

/*
* CEtte classe sert de protocole de connexion.
*
* La connexion est de la forme :
*   - un protocole
*   - une probabilité
*
* Elle assure que 2 machines soient en connexion. C'est la liaison entre 2 machines.
* */
public Connexion (Protocol protocol, double probability) {
    this.protocol = protocol;
    this.probability = probability;
}

public Protocol getProtocol() {
    return protocol;
}
public double getProbability() {

    return probability;
}
}