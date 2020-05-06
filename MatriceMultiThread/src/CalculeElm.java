
class CalculElem implements Runnable{
    MatriceEntiere m1, m2, m3;
    int i, j;
    public CalculElem(MatriceEntiere m1, MatriceEntiere m2, MatriceEntiere m3, int i, int j){
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.i = i;
        this.j = j;

    }

    @Override
    public void run() {
        try{
            m3.setElem(i, j, MatriceEntiere.produitLigneColonne(m1, i, m2, j));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

