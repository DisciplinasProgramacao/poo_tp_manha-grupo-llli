
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


/*
* Classe Serie: Agregação com "Plataforma"
*/
public class Serie{

    private int audiencia;
    
    private int idSerie;
    private String nome;
    private Date dataDeLancamento;

    private List <String> genero;
    private List <String> idioma;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    

    /*
    *
    * Construtor. Uma serie para ser contruida precisa no minimo dos parametros abaixo
    * @param idSerie, para definir um id para série
    * @param nome, para definir um nome para série
    * @param dataDeLancamento, para definir a data  da serie
    */


    Serie (String idSerie, String nome, String dataDeLancamento){
        
        init (idSerie, nome, dataDeLancamento);
    }


    /*
    * Vai instaciar os atributos da serie
    * @param idSerie, para definir um id para série
    * @param nome, para definir um nome para série
    * @param dataDeLancamento, para definir a data  da serie
    */
    private void init (String idSerie, String nome, String dataDeLancamento){

        try {
            
            //Apenas numeros podem ser convertidos em Int
            if (idSerie.matches("-?\\d+")) this.idSerie = Integer.parseInt(idSerie);

            this.nome = nome;
            this.dataDeLancamento = dateFormat.parse(dataDeLancamento);
            this.audiencia = 0;
        
        } catch (ParseException e){
            System.out.println("Erro ao converter");
            // TODO: handle exception
        }
    }



    /*
    * Registra a audiencia da serie basiada nas pessoas que ja assistiram.
    * Todos os clientes possuem uma lista de series ja assistidas que é recebida como input nessa função
    * @param List <Serie> seriesJaAssistidas,
    */
    public void registrarAudiencia(){

        this.audiencia++;
        return;
    }


    /*
    * Adiciona um novo serie a lista de genero 
    * @param tipoGenero que sera adicionada
    */
    public boolean adicionarGenero (String tipoGenero){

        if(genero.isEmpty()){
            
            this.genero = new ArrayList<String>();
        }
        
        this.genero.add(tipoGenero);
        
        if (this.genero.isEmpty()) return false;

        else return true;
    }


    /*
    * Adiciona um novo idoma a lista de idioma 
    * @param tipoIdioma que sera adicionada
    */
    public boolean adicionarIdioma (String tipoIdioma){

        if (idioma.isEmpty()){

            this.idioma = new ArrayList<String>();
        }
        
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

        return (this.idSerie+";"+this.nome+";"+this.dataDeLancamento);
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

        return this.idSerie;
    }

    public List<String> getGenero(){

        return this.genero;
    }

    
    public List<String> getIdioma(){

        return this.idioma;
    }
}
