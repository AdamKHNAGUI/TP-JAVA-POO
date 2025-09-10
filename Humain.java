import java.io.*;
import java.util.*;

public class Humain {

    protected static Random loto = new Random(Calendar.getInstance().getTimeInMillis());
    protected int age;
    protected int poids;
    protected String nom;
    protected int esperanceVie;

    Humain(String nom) {
        this.nom=nom;
        this.age=0;
        this.poids=3;
        this.setEsperanceVie();
    }

    Humain(int age, int poids, String nom) {
        this.age=age;
        this.poids=poids;
        this.nom=nom;
        this.setEsperanceVie();
    }

    void setNom(String nom) {
        this.nom=nom;
    }

    void setAge(int age) {
        this.age=age;
    }

    void setPoids(int poids) {
        this.poids=poids;
    }

    int getAge() {
        return this.age;
    }

    int getPoids() {
        return this.poids;
    }

    String getNom() {
        return this.nom;
    }

    protected void setEsperanceVie() {
        this.esperanceVie = 70;
    }

    public void vieillir() {
        this.age++;
    }

    public void grossir(int p) {
        this.age+=p;
    }

    public boolean isDead() {
        if (this.age > this.esperanceVie)
            return true;
        return false;
    }

    public void print() {
        System.out.println("Je m'appelle " + this.nom + ", j'ai "+this.age +" et je pèse "+ this.poids);
    }
}