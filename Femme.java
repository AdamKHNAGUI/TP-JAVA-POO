import java.io.*;

public class Femme extends Humain {

    private int fertilite;

    Femme(String nom) {
        super(nom);
        this.fertilite=0;
    }

    Femme(int age, int poids, String nom, int fertilite) {
        super(age,poids,nom);
        this.fertilite=fertilite;
    }

    int getFertilite() {
        return fertilite;
    }

    public void vieillir() {
        this.age++;

        if (age==15){
            this.fertilite = loto.nextInt(101);
        }

        if (age <= 20) poids = 3+(int)(2.6*age);
        else if (age >= 50) poids += (age % 2);
    }

    public Humain rencontre (Homme h) {
        if (this.age > 15 && this.age < 50 && h.age > 15){
            if (this.poids > 150 || h.poids > 150) {
                return null;
            } else {
                int f = loto.nextInt(101);
                if (f > this.fertilite) {
                    return null;
                }
                int p = loto.nextInt(101);
                Humain baby;
                if (p < 50) {
                    baby = new Homme(h.nom + this.nom);
                } else {
                    baby = new Femme(h.nom + this.nom);
                }
                int g = loto.nextInt(21);
                h.grossir(g);
                this.grossir(10);
                return baby;
            }
        } else {
            return null;
        }
    }

    protected void setEsperanceVie() {
        this.esperanceVie = 55 + loto.nextInt(41);
    }
}