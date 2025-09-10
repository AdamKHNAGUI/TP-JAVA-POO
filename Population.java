import java.util.*;
import java.io.*;

class Population {

    List<Humain> pop;

    Population() {
        this.pop = new ArrayList<>();
    }

    public void vider() {
        this.pop.clear();
    }

    public void addHumain(Humain h) {
        this.pop.add(h);
    }

    public Humain getHumain(int index) {
        return this.pop.get(index);
    }

    public Humain removeHumain(Humain h) {
        this.pop.remove(h);
        return h;
    }

    public Humain removeHumain(int index) {
        return this.pop.remove(index);
    }

    public int taille() {
        return this.pop.size();
    }

    public void vieillir() {
        for (int i = 0; i < this.pop.size(); i++) {
            getHumain(i).vieillir();
        }
    }

    public void print() {
        for (int i = 0; i < this.pop.size(); i++) {
            getHumain(i).print();
        }
    }
}