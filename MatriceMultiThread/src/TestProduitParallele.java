import java.io.File;

class TestProduitParallele{

    public static void main(String[] args) {
        MatriceEntiere matrice1 = new MatriceEntiere(new File("C:\\Users\\hp\\IdeaProjects\\ProgC\\src\\MatriceEntiere\\donnees_produit1"));

        MatriceEntiere matrice2 = new MatriceEntiere(new File("C:\\Users\\hp\\IdeaProjects\\ProgC\\src\\MatriceEntiere\\donnees_produit2"));
        MatriceEntiere m3 = new MatriceEntiere(matrice1.getNbLignes(),matrice2.getNbColonnes());
        m3.initWithZeros();
        try{

            Thread [][] threads = new Thread[matrice1.getNbLignes()][matrice2.getNbColonnes()];
            for (int i = 0; i < matrice1.getNbLignes() ; i++) {
                for (int j = 0; j < matrice2.getNbColonnes() ; j++) {
                    threads[i][j] = new Thread(new CalculElem(matrice1, matrice2, m3, i, j));
                    threads[i][j].start();

                }
            }
            for (int i = 0; i < matrice1.getNbLignes() ; i++) {
                for (int j = 0; j < matrice2.getNbColonnes() ; j++) {
                    threads[i][j].join();

                }
            }
            // for testing
            //System.out.println(m3.compare(matrice1.produitMatriciel(matrice2)));

        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
