
import java.util.ArrayList;
import java.util.List;



/*
* Classe Cliente: Agregação com "Plataforma"
*/
public class Cliente{
    
    private String nome;
    private String login;
    private String senha;
    private List <Serie> seriesAssistirFuturamente;
    private List <Serie> seriesJaAssistidas;




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
    * Vai instaciar os atributos da serie, Além de instanciar as listas de seriesJaAssistidas e seriesAssistirFuturamente
    * @param nome, para definir um nome para Cliente
    * @param login, para definir um login para Cliente
    * @param senha, para definir uma Senha para o CLiente
    */
    private void init (String nome,String login,String senha){
        
        seriesJaAssistidas = new ArrayList<Serie>();
        seriesAssistirFuturamente = new ArrayList<Serie>();

        this.nome = nome;
        this.login = login;
        this.senha = senha;

    }
    
    


    /*
    * Vai adicionar Series a um tipo de lista, no input char tipo lista
    * @param Serie, para definir qual serie sera adiconada na lista
    * @param tipoLista, para definir se vai ser adicionado na lista de assistir futuramente ou ja ssistidos
    */
    public void adicionarSeries(Serie serie, char tipoLista ){
        
        
        if (0 == Character.valueOf(tipoLista).compareTo(Character.valueOf('F'))){
            
            //System.out.println("Serie adiconada: "+serie.getId()+" na lista "+tipoLista+"\n");
            seriesAssistirFuturamente.add(serie);
             
            }
            else if (0 == Character.valueOf(tipoLista).compareTo(Character.valueOf('A'))){
                
                seriesJaAssistidas.add(serie);
                serie.registrarAudiencia();

                //System.out.println("Serie adiconada: "+serie.getId()+" na lista "+tipoLista+"\n");
            }
        
        return;       
    }



    /*
    * Vai retirar uma Serie Da lista de Series para assistir futuramente
    * @param posicao, para saber qual Serie sera removida
    */
    public void removerSerieAssistirFuturamente(int posicao){
        
        seriesAssistirFuturamente.remove(posicao);
    }

    public String toString(){

        return (this.nome+";"+this.login+";"+this.senha);
    }


    public String getLogin(){

        return this.login;
    }
}
