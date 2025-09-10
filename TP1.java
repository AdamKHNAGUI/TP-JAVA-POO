import java.util.*;

public class TP1 {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java TP1 <nombre_tours> <taille_population> <modeNaissance> [nbb]");
            return;
        }

        int nbTourJeu = Integer.parseInt(args[0]);
        int tailleInit = Integer.parseInt(args[1]);

        if (tailleInit % 2 != 0) {
            System.out.println("La taille initiale doit être paire !");
            return;
        }

        int modeNaissance = 0;
        int nbb = 0;

        if (args.length >= 3) {
            modeNaissance = Integer.parseInt(args[2]);
            if (modeNaissance == 2 && args.length >= 4) {
                nbb = Integer.parseInt(args[3]);
            }
        }

        Population population = new Population();
        int countH = 1;
        int countF = 1;

        // --- Création population initiale ---
        for (int i = 0; i < tailleInit / 2; i++) {
            String nomH = "Homme" + countH++;
            String nomF = "Femme" + countF++;
            int age = 20;
            int poidsH = 70;
            int poidsF = 55;
            int fertilite = Humain.loto.nextInt(101);
            int batifolage = 70 + Humain.loto.nextInt(31);

            Homme h = new Homme(age, poidsH, nomH, batifolage);
            Femme f = new Femme(age, poidsF, nomF, fertilite);
            population.addHumain(h);
            population.addHumain(f);
        }

        // --- Boucle de jeu ---
        for (int tour = 0; tour < nbTourJeu; tour++) {
            List<Humain> nouveauxBebes = new ArrayList<>();
            List<Humain> accidentes = new ArrayList<>();

            // --- Préparation des indices et shuffle ---
            List<Integer> indices = new ArrayList<>();
            for (int i = 0; i < population.taille(); i++) indices.add(i);
            Collections.shuffle(indices, Humain.loto);

            // --- Déterminer nombre de rencontres selon mode ---
            int n;
            switch (modeNaissance) {
                case 0: // normal
                    n = Humain.loto.nextInt(Math.max(1, population.taille() / 2));
                    break;
                case 1: // croissance forcée
                    int hommesFertiles = 0, femmesFertiles = 0;
                    for (Humain h : population.pop) {
                        if (h.isHomme() && h.getAge() > 15) hommesFertiles++;
                        if (h.isFemme() && h.getAge() > 15 && h.getAge() < 50) femmesFertiles++;
                    }
                    n = Math.min(hommesFertiles, femmesFertiles);
                    break;
                case 2: // croissance régulée
                    n = Integer.MAX_VALUE; // on fait autant de rencontres que nécessaire pour atteindre nbb bébés
                    break;
                default:
                    n = Humain.loto.nextInt(Math.max(1, population.taille() / 2));
            }

            int rencontresEffectuees = 0;
            int i = 0;

            // --- Boucle de rencontres ---
            while ((modeNaissance != 2 && rencontresEffectuees < n && i + 1 < indices.size())
                    || (modeNaissance == 2 && nouveauxBebes.size() < nbb && i + 1 < indices.size())) {

                int i1 = indices.get(i);
                int i2 = indices.get(i + 1);

                Humain h1 = population.getHumain(i1);
                Humain h2 = population.getHumain(i2);

                Humain bebe = null;
                if (h1.isFemme() && h2.isHomme()) bebe = ((Femme) h1).rencontre((Homme) h2);
                else if (h1.isHomme() && h2.isFemme()) bebe = ((Homme) h1).rencontre((Femme) h2);

                if (bebe != null) {
                    nouveauxBebes.add(bebe);
                    System.out.println("Naissance ! " + bebe.getNom());

                    // --- Accident ---
                    List<Humain> candidats = new ArrayList<>();
                    candidats.add(h1);
                    candidats.add(h2);
                    candidats.add(bebe);

                    int victimeIndex = Humain.loto.nextInt(candidats.size());
                    Humain victime = candidats.get(victimeIndex);

                    System.out.println("Accident ! " + victime.getNom() + " est décédé.");
                    accidentes.add(victime);
                }

                rencontresEffectuees++;
                i += 2;

                // Reshuffle pour mode 2 si besoin
                if (i + 1 >= indices.size() && modeNaissance == 2 && nouveauxBebes.size() < nbb) {
                    indices.clear();
                    for (int j = 0; j < population.taille(); j++) indices.add(j);
                    Collections.shuffle(indices, Humain.loto);
                    i = 0;
                }
            }

            // --- Vieillissement et retrait des morts + accidentés ---
            Iterator<Humain> it = population.pop.iterator();
            while (it.hasNext()) {
                Humain humain = it.next();
                humain.vieillir();
                if (humain.isDead() || accidentes.contains(humain)) it.remove();
            }

            // Ajouter les bébés survivants
            nouveauxBebes.removeAll(accidentes); // les bébés morts par accident sont supprimés
            population.pop.addAll(nouveauxBebes);

            System.out.println("Fin du tour " + (tour + 1) + ", population actuelle : " + population.taille());
        }

        System.out.println("Simulation terminée. Taille finale de la population : " + population.taille());
    }
}
