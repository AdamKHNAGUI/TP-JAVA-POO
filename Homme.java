import java.io.*;

public class Homme extends Humain {

    private int batifolage;

    Homme(String nom) {
        super(nom);
        this.batifolage = 0;
    }

    Homme(int age, int poids, String nom, int batifolage) {
        super(age,poids,nom);
        this.batifolage = batifolage;
    }

    public Humain rencontre (Femme f) {
        int b = loto.nextInt(101);
        if (b < this.batifolage) {
            return null;
        }
        if(f.age > 15 && f.age < 50 && this.age > 15) {
            if (this.poids > 150 || f.poids > 150) {
                return null;
            } else {
                int c = loto.nextInt(101);
                if (c > f.getFertilite()) {
                    return null;
                }
                int p = loto.nextInt(101);
                Humain baby;
                if (p < 50) {
                    baby = new Homme(this.nom + f.nom);
                } else {
                    baby = new Femme(this.nom + f.nom);

                }
                int g = loto.nextInt(21)-10;
                this.grossir(g);
                f.grossir(10);
                return baby;
            }
        } else {
            return null;
        }
    }

    public void vieillir() {
        this.age++;
        if(age > 15) {
            this.batifolage = 70 + loto.nextInt(31);
        }

        if(age > 30) {
            this.batifolage = 20 + loto.nextInt(31);
        }
        if(age > 60) {
            this.batifolage = 50 + loto.nextInt(51);
        }


        if (age <= 20) poids = 3+(int)(3.6*age);
        else if (age >= 50) poids += (age % 2);
    }

    protected void setEsperanceVie() {
        this.esperanceVie = 50 + loto.nextInt(31);
    }

    boolean isHomme(){
        return true;
    }

    boolean isFemme(){
        return false;
    }


}