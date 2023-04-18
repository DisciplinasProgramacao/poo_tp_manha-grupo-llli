
import java.util.ArrayList;
import java.util.List;


/*
* Classe Serie: Agregação com "Catalogo"
*/
public class Serie{

    private int audiencia;
    private static int proximoID = 1;
    private int ID;
    private String nome;
    private int quantidadeDeEpisodeos;
    private List <String> genero;
    private List <String> idioma;

    /*
    *
    * Construtor. Uma serie para ser contruida precisa no minimo dos parametros nome e quantidade de episodeos.
    * Caso eles não forem passados a serie sera contruida da seguinte maneira:
    * @param nome = ""
    * @param quantideDeEpisodeos = 0
    */
    Serie(){
        
        init("", 0);
    }
    
    /*
    *
    * Construtor. Uma serie para ser contruida precisa no minimo dos parametros abaixo
    * @param nome, para definir um nome para série
    * @param quantideDeEpisodeos, para definir a quantidade de episodeos da serie
    */
    Serie(String nome, int quantidadeDeEpisodeos){

        init (nome, quantidadeDeEpisodeos);
    }


    /*
    * Vai instaciar os atributos da serie, alem de criar uma lista de generos e idiomas vazias e definir i ID da serie
    * @param nome, para definir um nome para série
    * @param quantideDeEpisodeos, para definir a quantidade de episodeos da serie
    */
    private void init (String nome, int quantidadeDeEpisodeos){

        this.audiencia = 0;
        this.ID = this.proximoID;
        this.proximoID++;
        this.nome = nome;
        this.quantidadeDeEpisodeos = quantidadeDeEpisodeos;
        this.genero = new ArrayList<String>();
        this.idioma = new ArrayList<String>();
    }



    /*
    * Registra a audiencia da serie basiada nas pessoas que ja assistiram.
    * Todos os clientes possuem uma lista de series ja assistidas que é recebida como input nessa função
    * @param List <Serie> seriesJaAssistidas,
    */
    public void registrarAudiencia(List <Serie> seriesJaAssistidas){

        for (int i = 0; i < seriesJaAssistidas.size(); i++){

            Serie temp = seriesJaAssistidas.get(i);

            if (this.ID == temp.getId()){

                audiencia++;
            }
        }
    }


    /*
    * Adiciona um novo serie a lista de genero 
    * @param tipoGenero que sera adicionada
    */
    public boolean adicionarGenero (String tipoGenero){
    
        this.genero.add(tipoGenero);
        
        if (this.genero.isEmpty()) return false;

        else return true;
    }


    /*
    * Adiciona um novo idoma a lista de idioma 
    * @param tipoIdioma que sera adicionada
    */
    public boolean adicionarIdioma (String tipoIdioma){

        this.idioma.add(tipoIdioma);
        

        if (this.idioma.isEmpty()) return false;
        
        else return true;
    }


    /*
    * Remove um genero da lista de genero
    * @param nomeGenero a ser removido
    */
    public boolean removerGenero (String nomeGenero){
        
        for (int i = 0; i < this.genero.size(); i++){
            
            String temp = this.genero.get(i);
            
            if (nomeGenero.toLowerCase().equals(temp.toLowerCase())){
                
                this.genero.remove(i);

                return true;
            }
            
        }

        return false;
    }


    /*
    * Remove um idioma da lista de idioma
    * @param nomeIdioma a ser removido
    */
    public boolean removerIdioma(String nomeIdioma){

        for (int i = 0; i < this.idioma.size(); i++){
            
            String temp = this.idioma.get(i);
            
            if (nomeIdioma.toLowerCase().equals(temp.toLowerCase())){
                
                this.idioma.remove(i);

                return true;
            }
            
        }

        return false;
    }

    /*
    * Printa os atributos da serie caso necessario
    */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return (super.toString()+" Serie Id:" +this.ID+"| Nome: "+this.nome+"| Quantidade De Episódeos: " +this.quantidadeDeEpisodeos+"\n");
    }   


    /*
    * Printa os elementos da lista de genero caso necessario
    */
    public void mostrarGeneros (){
        

        String temp;

        System.out.print("Generos disponiveis:");
        for (int i = 0; i < genero.size(); i++){


            temp = genero.get(i);
            System.out.print(temp+" ");
        }
        System.out.println("");

        return;
    }



    /*
    * Printa os elementos da lista de idomas caso necessario
    */
    public void mostrarIdiomas (){
        String temp;

        System.out.print("Idiomas disponiveis:");
        for (int i = 0; i < idioma.size(); i++){

            temp = idioma.get(i);
            System.out.print(temp+" ");
        }
        System.out.println("");

        return;
    }

    //GETS

    public int getAudiencia(){

        return this.audiencia;
    }

    public String getNome(){

        return this.nome;
    }

    public int getId(){

        return this.ID;
    }

    public List<String> getGenero(){

        return this.genero;
    }

    
    public List<String> getIdioma(){

        return this.idioma;
    }
}
