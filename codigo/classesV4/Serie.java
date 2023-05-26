/*
* Classe Serie: especialização da Classe Midia
*/
public class Serie extends Midia{

    private int quantidadeDeEpisodeos;



    /*
    *
    * Construtor. Uma Serie para ser contruida precisa no minimo dos parametros abaixo
    * @param idFilme, para definir um id para filme
    * @param nome, para definir um nome para filme
    * @param dataDeLancamento, para definir a data  do filme
    * Caso a quantidade de episodeos não for informada a Serie será criada com 0 eps
    */
    Serie (String idSerie, String nome, String dataDeLancamento){
        
        super(idSerie, nome, dataDeLancamento);
        init(0);
        
    }

    /*
    *
    * Construtor. Uma serie para ser contruida precisa no minimo dos parametros abaixo
    * @param idSerie, para definir um id para série
    * @param nome, para definir um nome para série
    * @param dataDeLancamento, para definir a data  da serie
    */
    Serie (String idSerie, String nome, String dataDeLancamento, int quantidadeDeEpisodeos){
        
        super(idSerie, nome, dataDeLancamento);
        
        init (quantidadeDeEpisodeos);
    }




    /*
    * Vai instaciar os atributos da serie
    * @param quantidadeDeEpisodeos, para definir a quantiade de episodeos  da serie
    */
    private void init (int quantidadeDeEpisodeos){

        this.quantidadeDeEpisodeos = quantidadeDeEpisodeos;
    }


    /*
    * Printa os atributos da serie caso necessario
    */
    @Override
    public String toString() {

        return (super.toString()+"|| Quantide de Episodeos:" +this.quantidadeDeEpisodeos);
    }   
}
