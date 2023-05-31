import java.time.LocalDate;


/*
* Classe Avaliacoes: Composicao com "CLiente" e "Midias"
*/
public class Avaliacoes {


    //#region controle

    private LocalDate dataDaAvaliacao;
    private int notaAvaliacao;

    //#endregion


    /*
    *
    * Construtor. Aliacao para ser contruida precisa no minimo da Midia que sera avaliada
    * @param mdia, para definir a midia que sera avaliada
    */
    Avaliacoes(){
   
        init();
    }
    

    /**
    * Vai instaciar valores para midia, definir data atual e nota da avaliaçaõ
    */
    private void init (){
        
        this.dataDaAvaliacao =  LocalDate.now();
        this.notaAvaliacao = 0;
    }



    /**
    * Vai registrar avaliação para
    * @param clienteLOgin, vai adicionar
    * @param notaAvaliação, para definir qual nota a midia recebera
    * @return boolean, True: se conseguir registrar a avaliacação
    * @return boolean, False: se nao conseguir registrar a avaliacação
    */
    public boolean validarAvaliacao (String clienteLogin,int notaAvaliacao){
        
        
        if ( notaAvaliacao < 1 || notaAvaliacao > 5){
            System.out.println("A nota da avaliação deve estar entre 1 e 5.");
            return false ;
        }

        this.notaAvaliacao = notaAvaliacao;

        return true;
    }


    //Gets Region

    public LocalDate getDataAvaliacao(){

        return this.dataDaAvaliacao;
    }

    public int getNotaAvaliacao(){

        return this.notaAvaliacao;
    }

    public String toString(){

        return ("Data da avaliação:"+this.dataDaAvaliacao.toString()+"Nota da avaliacao: "+this.notaAvaliacao);
    }
}
