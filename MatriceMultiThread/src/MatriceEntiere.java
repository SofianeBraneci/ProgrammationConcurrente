import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

// Q1- la méthode est Integer.parseInt(String s);

public class MatriceEntiere {
    private int rows;
    private int cols;
    private int[][] mat = null;

    public MatriceEntiere(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.mat = new int[rows][cols];
    }

    public MatriceEntiere(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            this.rows = Integer.parseInt(reader.readLine());
            this.cols = Integer.parseInt(reader.readLine());
            this.mat = new int[this.rows][this.cols];
            //System.out.println(cols);
            for (int i = 0; i < this.rows; i++) {
                String[] line = reader.readLine().split(" ");
                //System.out.println(line.length);
                int[] numbers = new int[line.length];
                // convert any String digit to an Integer
                for (int j = 0; j < numbers.length; j++) {
                    numbers[j] = Integer.parseInt(line[j]);
                }

                // fill in the matrix
                for (int k = 0; k < this.cols; k++) {
                    this.mat[i][k] = numbers[k];
                }

            }

            reader.close();

        } catch (IOException e) {
            System.out.println(" Exception raised !, file not found ");
        }
    }


    public void printMatrice() {

        for (int i = 0; i < this.rows; i++) {
            System.out.println("row: " + i);
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.mat[i][j] + ",\t");
            }
            System.out.println("\n------------");
        }
    }


    public int getElem(int i, int j) {
        return this.mat[i][j];
    }

    public void setElem(int i, int j, int val) {
        this.mat[i][j] = val;
    }

    @Override
    public String toString() {
        return "MatriceEntière";
    }


    // for testing
    public void initWithZeros() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.mat[i][j] = 0;
            }


        }
    }

    public MatriceEntiere transpose() {
        MatriceEntiere r = new MatriceEntiere(this.cols, this.rows);
        r.initWithZeros();
        for (int i = 0; i < this.cols; i++) {
            for (int j = 0; j < this.rows; j++) {
                r.mat[i][j] = this.mat[j][i];

            }
        }
        return r;
    }


    public MatriceEntiere addition(MatriceEntiere mat2) throws TaillesNonConcordantesException {

            if ((this.rows == mat2.rows) && (this.cols == mat2.cols)) {
                MatriceEntiere resultat = new MatriceEntiere(this.rows, this.cols);
                resultat.initWithZeros();

                for (int i = 0; i < this.rows; i++) {
                    for (int j = 0; j < this.cols; j++) {
                        resultat.mat[i][j] = this.mat[i][j] + mat2.mat[i][j];

                    }
                }
                return resultat;

            } else {
                throw new TaillesNonConcordantesException("Invalid Dimension");
            }

    }

    public MatriceEntiere produitScalaire(int s) {
        MatriceEntiere resultat = new MatriceEntiere(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                resultat.mat[i][j] = this.mat[i][j] * s;

            }
        }
        return resultat;

    }

    public MatriceEntiere produitMatriciel(MatriceEntiere mat2) throws TaillesNonConcordantesException {

        System.out.println(this.cols);
        System.out.println(mat2.rows);
        if (this.cols == mat2.rows) {
            System.out.println("You can multiply");
            MatriceEntiere M = new MatriceEntiere(this.rows, mat2.cols);
            M.initWithZeros();
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < mat2.cols; j++) {
                    for (int k = 0; k < this.cols; k++) {
                        M.mat[i][j] += (this.mat[i][k] * mat2.mat[k][j]);

                    }
                }
            }

            return M;

        } else {
            throw new TaillesNonConcordantesException(" Invalid Dimension ");
        }
    }

    public int getNbLignes(){
        return this.rows;
    }

    public int getNbColonnes(){
        return this.cols;
    }

    public static int produitLigneColonne(MatriceEntiere m1, int i, MatriceEntiere m2, int j) throws TaillesNonConcordantesException{
        if (m1.cols != m2.rows){
            throw new TaillesNonConcordantesException("Dimensio Invalide");
        }
        else{
            int s = 0;
            for (int k = 0; k < m1.mat[i].length ; k++) {
                s += m1.mat[i][k] * m2.mat[k][j];
            }
            return s;
        }


    }

}

