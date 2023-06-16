import java.util.Map;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Stack;
import java.util.Iterator;

/*
* Classe Cliente: Agregação com "Plataforma", Composição com interface IComentar
*/
public class Cliente{
    
    //#region controle

    private String nome;
    private String login;
    private String senha;
    private Icomentar tipoCliente;
    public IFiltragem aplicadorDeFiltros = new FiltrosDeMidias();
    private Stack<Avaliacoes> avaliacoesFeitas;

    private Map<Integer, Midia> mapMidiaAssistirFuturamente;
    private Map<Integer, Midia> mapMidiaJaAssistidas;
  
    //#endregion


    /**
    *
    * Construtor. Um Cliente para ser contruida precisa no minimo dos parametros abaixo
    * @param nome, para definir um nome para Cliente
    * @param login, para definir um login para Cliente
    * @param senha, para definir uma Senha para o CLiente
    */
    Cliente(String nome,String login,String senha){

        init(nome,login, senha);
    }





    /**
    *
    * Vai dar valor os atributos do Cliente, Além de instanciar os Maps de seriesJaAssistidas e seriesAssistirFuturamente
    * @param nome, para definir um nome para Cliente
    * @param login, para definir um login para Cliente
    * @param senha, para definir uma Senha para o Cliente
    */
    private void init (String nome,String login,String senha){
        
        this.mapMidiaAssistirFuturamente = new HashMap<>();
        this.mapMidiaJaAssistidas = new HashMap<>();
        this.avaliacoesFeitas = new Stack<>();

        this.nome = nome;
        this.login = login;
        this.senha = senha;

    }
    
    


    /**
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



    /**
    * Vai retirar uma Midia do Map de Midia para assistir futuramente
    * @param idMidiaRemover, para saber qual Midia sera removida
    */
    public void removerMidiaAssistirFuturamente(int idMidiaRemover){
        
        this.mapMidiaAssistirFuturamente.remove(idMidiaRemover);

    }


    /**
    * Vai Avaliar uma Midia 
    * @param midiaParaAvaliar, deve informar qual midia deseja avaliar
    * @param notaParaMidia, deve informar a nota que deseja dar para midia
    */
    public void avaliarMidia (Midia midiaParaAvaliar, int notaParaMidia){


        //O cliente so pode avaliar Midias Ja asisstidas
        if (jaAssitiuMidia(midiaParaAvaliar)){

            Avaliacoes temp = midiaParaAvaliar.receberAvaliacao(this.login, notaParaMidia);

            if (temp != null){

                this.avaliacoesFeitas.push(temp);
            }
            
        }
        else {

            System.out.println("Nao pode avaliar uma Midia que nao foi assistida");
        }
    
    }




    /**
    * Definir o Tipo de cliente baseado nas ultimas avaliações feitas 
    */
    public void definirTipoCliente(){

       LocalDate atual =  LocalDate.now();
       LocalDate mesAnterior = atual.minusMonths(1);

       Stack <Avaliacoes> temp = this.avaliacoesFeitas;

       int comentariosNoMesAnterior = 0;

       //Para o cliente mudar o tipo deve ter no minimo 5 avalaições
       if (this.avaliacoesFeitas.size() >= 5){
            
           for (int i = 0; i < 5; i++){
                
               Avaliacoes avaliacao = temp.pop();
                
               //Faz comparação da data das ultimas 5 avaliações, a data da avaliação deve ser menor que o mes atual, igual ao mes anterior e os anos devem ser iguais
               if (atual.getMonthValue() > avaliacao.getDataAvaliacao().getMonthValue() &&
                   mesAnterior.getMonthValue() == avaliacao.getDataAvaliacao().getMonthValue() &&
                   atual.getYear() == avaliacao.getDataAvaliacao().getYear() &&
                   mesAnterior.getYear() ==  avaliacao.getDataAvaliacao().getYear()){


                   comentariosNoMesAnterior++;
               }

                    
           }
       }

       if (comentariosNoMesAnterior >= 5)this.tipoCliente = new ClienteComentarista();

       else this.tipoCliente = null;
    }


    /**
     * Função que permite que um cliente comentarista faça comentários em midias ja assistidas
     * 
     * @param comentario deve passar um comentario
     * @param midia Midia que vai ser comentada
     */
    public void fazerComentario(String comentario, Midia midia){
        
        definirTipoCliente();
        
        //Apenas clientes comentaristas e midias que ja assistiram
        if ((tipoCliente != null) && jaAssitiuMidia(midia)){

            tipoCliente.fazerComentario(comentario,midia,this.login);
        }
        else {
            System.out.println("Cliente não pode comentar");
        }
        
    }

    /**
     * Metodo verifica se midia passada ja foi assistida pelo cliente
     * @param midia informa a Midia para verificar se foi assistida
     * @return True se ja ssistiu a midia
     * @return False se nunca assistiu a midia
     */
    private boolean jaAssitiuMidia (Midia midia){

        if (this.mapMidiaJaAssistidas.get(midia.getId()) == midia) return true;

        else return false;
    }


    /*
    * Retorna um string com os atributos do cliente 
    */
    public String toString(){

        return (this.nome+";"+this.login+";"+this.senha);
    }


    public int totalMidiasJaAssistidas(){

        return this.mapMidiaJaAssistidas.size();
    }

    public int totalMidiasAvaliadas(){

        return this.avaliacoesFeitas.size();
    }

    /*
    * Retorna login do cliente 
    */
    public String getLogin(){
        return this.login;
    }

    public String getNome(){

        return this.nome;
    }

    public String getSenha(){

        return this.senha;
    }

    public void getNomeMidiasJaAssistidas() {
        System.out.println("Lista geral de filmes e séries já assistidos");
        for (Midia midia : mapMidiaJaAssistidas.values()) {
            System.out.println("==========================");
            System.out.println("Nome: "+ midia.getNome()+ " -- Audiencia :"+ midia.getAudiencia());
        }
    }


    public void getNomeMidiasAssistirFuturamente() {
        System.out.println("Lista geral de filmes e séries que desejo assistir futuramente");
        for (Midia midia : mapMidiaAssistirFuturamente.values()) {
            System.out.println("==========================");
            System.out.println("Nome: "+ midia.getNome()+ " -- Audiencia :"+ midia.getAudiencia());
        }
    }




}
