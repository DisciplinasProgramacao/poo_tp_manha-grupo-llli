import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

/*
* Classe Cliente: Agregação com "Plataforma"
*/
public class Cliente{
    
    //#region controle

    private String nome;
    private String login;
    private String senha;
    //private ITipoCliente tipoCliente;
    
    private Map<Integer, Midia> mapMidiaAssistirFuturamente;
    private Map<Integer, Midia> mapMidiaJaAssistidas;
  
    //#endregion


    /*
    *
    * Construtor. Um Cliente para ser contruida precisa no minimo dos parametros abaixo
    * @param nome, para definir um nome para Cliente
    * @param login, para definir um login para Cliente
    * @param senha, para definir uma Senha para o CLiente
    */
    Cliente(String nome,String login,String senha){

        init(nome,login, senha);
    }





    /*
    *
    * Vai dar valor os atributos do Cliente, Além de instanciar os Maps de seriesJaAssistidas e seriesAssistirFuturamente
    * @param nome, para definir um nome para Cliente
    * @param login, para definir um login para Cliente
    * @param senha, para definir uma Senha para o Cliente
    */
    private void init (String nome,String login,String senha){
        
        this.mapMidiaAssistirFuturamente = new HashMap<>();
        this.mapMidiaJaAssistidas = new HashMap<>();

        this.nome = nome;
        this.login = login;
        this.senha = senha;

    }
    
    


    /*
    * Vai adicionar Clientes a um tipo de map, definido no input char qual Map iremos acessar
    * @param Midia, para definir qual midia sera adiconada no Map
    * @param tipoLista, para definir se vai ser adicionado no Map de assistir futuramente ou ja ssistidos
    */
    public void adicionarMidia(Midia midia, char tipoLista ){
        
        //Se o tipolista = 'F' adicionaremos no map de Assistir futuramente
        if (0 == Character.valueOf(tipoLista).compareTo(Character.valueOf('F'))){
            
            mapMidiaAssistirFuturamente.put(midia.getId(),midia);
             
        }

        //Se o tipolista = 'A' adicionaremos no map de midias Ja Assistidas
        else if (0 == Character.valueOf(tipoLista).compareTo(Character.valueOf('A'))){
            
            mapMidiaJaAssistidas.put(midia.getId(), midia);
            midia.registrarAudiencia(this.mapMidiaJaAssistidas);

        }
        else{
            System.out.println("Lista invalida");
        }        
        return;       
    }



    /*
    * Vai retirar uma Midia do Map de Midia para assistir futuramente
    * @param idMidiaRemover, para saber qual Midia sera removida
    */
    public void removerMidiaAssistirFuturamente(int idMidiaRemover){
        
        this.mapMidiaAssistirFuturamente.remove(idMidiaRemover);
    }




    /*
    * Vai Avaliar uma Midia 
    * @param midiaParaAvaliar, deve informar qual midia deseja avaliar
    * @param notaParaMidia, deve informar a nota que deseja dar para midia
    */
    public void avaliarMidia (Midia midiaParaAvaliar, int notaParaMidia){


        //O cliente so pode avaliar Midias Ja asisstidas
        if (this.mapMidiaJaAssistidas.get(midiaParaAvaliar.getId()) == midiaParaAvaliar){

            Avaliacoes avaliacao = new Avaliacoes(midiaParaAvaliar);
            
            if(avaliacao.registrarAvaliacao(this.login, notaParaMidia)){

                avaliacao.calcularMediaAvalicao();
            }
                
        }

        
        else {

            System.out.println("Nao pode avaliar uma Midia que nao foi assistida");
        }
    
    }

    /*
    * Retorna um string com os atributos do cliente 
    */
    public String toString(){

        return (this.nome+";"+this.login+";"+this.senha);
    }


    /*
    * Retorna login do cliente 
    */
    public String getLogin(){

        return this.login;
    }
}
