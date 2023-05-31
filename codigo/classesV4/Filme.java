

/*
* Classe FIlme: especialização da Classe Midia
*/
public class Filme extends Midia{
    


    private int duracao;
    

    /**
    *
    * Construtor. Um filme para ser contruida precisa no minimo dos parametros abaixo
    * @param idFilme, para definir um id para filme
    * @param nome, para definir um nome para filme
    * @param dataDeLancamento, para definir a data  do filme
    * @param duracao
    */
    Filme (String idFilme, String nome, String dataDeLancamento, String duracao){
        
        super(idFilme, nome, dataDeLancamento);
        
        init (duracao);
    }


    /**
    * Vai instaciar os atributos do filme
    * @param duracao, para definir a duracao  do filme em minutos
    */
    private void init (String duracao){

        if (duracao.matches("-?\\d+")) this.duracao = Integer.valueOf(duracao);

        else{
            System.out.println("Duracao do filme invalida");
        }
    }


    /*
    * Printa os atributos do Filme caso necessario
    */
    @Override
    public String toString() {

        return (super.toString()+" Quantide de Episodeos:" +this.duracao);
    }   
}
