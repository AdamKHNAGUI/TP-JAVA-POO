class TP1 {


    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java TP1 <nombre_tours> <taille_population>");
            return;
        }
        int nbTourJeu = Integer.parseInt(args[0]);
        int tailleInit = Integer.parseInt(args[1]);

        // Vérification que la taille initiale est paire
        if (tailleInit % 2 != 0) {
            System.out.println("La taille initiale doit être paire !");
            return;
        }
        Population population = new Population();

    }
}