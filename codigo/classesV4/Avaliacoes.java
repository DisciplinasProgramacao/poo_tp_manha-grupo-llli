import java.util.Date;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;


/*
* Classe Avaliacoes: Composicao com "CLiente" e "Midias"
*/
public class Avaliacoes {


    //#region controle

    private Date dataDaAvaliacao;
    private Map<String, Integer> avaliacoesPorCliente;
    private Midia midia; 

    private BigDecimal mediaAvaliacao;
    private int quantidadeAvalicoes;
    private int somaDeAvaliacoes;

    //#endregion


    /*
    *
    * Construtor. Aliacao para ser contruida precisa no minimo da Midia que sera avaliada
    * @param mdia, para definir a midia que sera avaliada
    */
    Avaliacoes(Midia midia){
   
        init(midia);
        avaliacoesPorCliente = new HashMap<>(10000,0.8f);
    }
    

    /*
    *
    * Vai dar valor os atributos da Avaliacao, Além de instanciar o Maps de avaliacoesPorCliente
    * @param midia, para definir um nome para Midia
    */
    private void init (Midia midia){
        
        this.midia = midia;
        dataDaAvaliacao = new Date();
        this.quantidadeAvalicoes = 0;
        this.somaDeAvaliacoes = 0;

    }



    /*
    * Vai registrar avaliação para
    * @param clienteId, vai adicionar o clienteId para a o Set avaliacoesPorCliente
    * @param nota, para definir qual nota a midia recebera
    * @return boolean, True: se conseguir registrar a avaliacação
    * @return boolean, False: se nao conseguir registrar a avaliacação
    */
    public boolean registrarAvaliacao (String clienteId, int nota){

        //Se o cliente so pode avaliar a midia uma vez
        if (avaliacoesPorCliente.containsKey(clienteId)){
            System.out.println("Este cliente já avaliou esta mídia.");
            return false;
        }

        //Se a nota deve ser um numero entre 1 e 5
        if ( nota < 1 && nota > 5){
            System.out.println("A nota da avaliação deve estar entre 1 e 5.");
            return false ;
        }

        this.quantidadeAvalicoes++;
        this.somaDeAvaliacoes += nota;
        avaliacoesPorCliente.put(clienteId,nota);

        return true;
    }



    /*
    * Vai calcular a media de  avaliaçoes da midia  e vai setar esse valor na midia
    */
    public void calcularMediaAvalicao (){


        BigDecimal somaDeAvaliacoesBigDecimal = new BigDecimal(this.somaDeAvaliacoes);        
        BigDecimal quantidadeAvalicoesRecebidasBigDecimal = new BigDecimal(this.quantidadeAvalicoes);
        BigDecimal resultado = somaDeAvaliacoesBigDecimal.divide(quantidadeAvalicoesRecebidasBigDecimal,2 ,RoundingMode.CEILING);
        
        this.mediaAvaliacao = resultado.setScale(2,RoundingMode.CEILING);
        

        this.midia.setMediaAvaliacao(this.mediaAvaliacao);

        return;
        
    }

    public Date getDataAvaliacao(){

        return this.dataDaAvaliacao;
    }

    public String toString(){

        return ("A media de avaliacoes da midia: "+midia.getNome()+" é "+this.mediaAvaliacao);
    }
}
