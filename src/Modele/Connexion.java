package Modele;

public class Connexion {
    private Protocol protocol;
    private double probability;


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