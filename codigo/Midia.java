import java.util.Date;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*
* Classe Midia: Classe mãe de Serie e Filme
*/
public class Midia{


    protected int idMidia;
    protected int audiencia;
    protected String nome;
    
    protected BigDecimal mediaAvaliacao;
    
    protected Date dataDeLancamento;
    
    protected Set<String> genero;
    protected Set<String> idioma;

    protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
   


    /*
    *
    * Construtor. Uma Midia para ser contruida precisa no minimo dos parametros abaixo
    * @param idMidia, para definir um id para midia
    * @param nome, para definir um nome para midia
    * @param dataDeLancamento, para definir a data  da midia
    */
    Midia(String idMidia, String nome, String dataDeLancamento){


        this.mediaAvaliacao = new BigDecimal (0.0);

        init (idMidia, nome, dataDeLancamento);
    }




    /*
    * Vai instaciar os atributos da midia
    * @param idSerie, para definir um id para midia
    * @param nome, para definir um nome para midia
    * @param dataDeLancamento, para definir a data  da midia
    */
    private void init (String idMidia, String nome, String dataDeLancamento){

        try {
            
            if (idMidia.matches("-?\\d+")) this.idMidia = Integer.valueOf(idMidia);

            this.nome = nome;
            this.dataDeLancamento = dateFormat.parse(dataDeLancamento);
  

        } catch (ParseException e) {
            System.out.println("Erro ao converter");
            // TODO: handle exception
        }
    }

    
    
    /*
    * Registra a audiencia da midia baseada nas pessoas que ja assistiram a Midia
    * @param Map <Integer, Midia> midiasJaAssistidas, o método recebe um Map de mídias ja assistidas.
    * se o Id da midia for igual a algum Id do Map de Midias podemos considerar a audiencia
    */

    public void registrarAudiencia( Map <Integer,Midia> midiasJaAssistidas){

        if (midiasJaAssistidas.get(this.idMidia)!= null){

            this.audiencia++;
            return;
        }

    }




    /*
    * Adiciona um novo serie ao Set de genero 
    * @param tipoGenero que sera adicionada
    */
    public void adicionarGenero (String tipoGenero){

        if (genero == null){

            genero = new HashSet<>();
        }

        genero.add(tipoGenero);
    }


    /*
    * Adiciona um novo idoma ao Set de idioma 
    * @param tipoIdioma que sera adicionada
    */
    public void adicionarIdioma (String tipoIdioma){

        if (idioma == null){

            idioma = new HashSet<>();
        }

        idioma.add(tipoIdioma);

        return;
    }


    /*
    * Remove um genero do Set de genero
    * @param nomeGenero a ser removido
    */
    public void removerGenero (String nomeGenero){
        
        if (this.genero != null){
            this.genero.remove(nomeGenero);
        }

        return;
    }


    /*
    * Remove um idioma do Set de idioma
    * @param nomeIdioma a ser removido
    */
    public void removerIdioma(String nomeIdioma){


        if (this.idioma != null){
            this.idioma.remove(nomeIdioma);
        }

        return;
    }

    
    /*
    * Retorna os atributos da midia
    */
    @Override
    public String toString() {

        return ("IdMidia: "+this.idMidia+"|| Nome: "+this.nome+"|| Data de lançamento: "+this.dataDeLancamento.toString()+"|| Media De Avaliacoes: "+this.mediaAvaliacao);
    }   


    
    /*
    * Metodo para Verificar se o genero Passado como parametro existe no Setgenero da midia
    * @param generoParaComparar
    * @return True, se o método existe no Set genero
    * @return False, se o método nao existe no Set genero
    */
    public boolean compararGenero (String generoParaComparar){
        
        if (this.genero!=null){

            Iterator<String> iterator = this.genero.iterator();

            while (iterator.hasNext()) {
                String elemento = iterator.next();
            
                if (generoParaComparar.equals(elemento)){
                    return true;
                }
            }
        }

        return false;
    }


    /*
    * Metodo para Verificar se o idioma Passado como parametro existe no SetIdioma da midia
    * @param idiomaParaComparar
    * @return True, se o método existe no Set idioma
    * @return False, se o método nao existe no Set idioma
    */
    public boolean compararIdioma (String idiomaParaComparar){
        
        if (this.idioma!=null){

            Iterator<String> iterator = this.idioma.iterator();

            while (iterator.hasNext()) {
                String elemento = iterator.next();
            
                if (idiomaParaComparar.equals(elemento)){
                    return true;
                }
            }
        }

        return false;
    }



    /*
    * Printa os elementos do Set de genero
    */
    public void mostrarGeneros (){
        
        if (this.genero!=null){

            Iterator<String> iterator = genero.iterator();

            while (iterator.hasNext()) {
                String elemento = iterator.next();
                System.out.println(elemento);
            }
        }

        return;
    }



    /*
    * Printa os elementos do Set de idiomas caso necessario
    */
    public void mostrarIdiomas (){

        if (this.idioma!=null){

            Iterator<String> iterator = idioma.iterator();
            
            while (iterator.hasNext()) {
                String elemento = iterator.next();
                System.out.println(elemento);
            }
        }

        return;
    }

    //GETS

    public void setMediaAvaliacao(BigDecimal mediaAvaliacao){

        this.mediaAvaliacao = mediaAvaliacao;
    }

    public int getAudiencia(){

        return this.audiencia;
    }

    public String getNome(){

        return this.nome;
    }

    public int getId(){

        return this.idMidia;
    }

    public BigDecimal getMediaAvaliacao(){

        return this.mediaAvaliacao;
    }
}