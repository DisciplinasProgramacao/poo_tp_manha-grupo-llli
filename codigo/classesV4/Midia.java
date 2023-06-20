import java.util.Date;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*
* Classe Midia: Classe mãe de Serie e Filme e Composição com Avaliações
*/
public class Midia{


    protected int idMidia;
    protected int audiencia;
    protected String nome;
    
    protected BigDecimal mediaAvaliacao;
    
    protected Date dataDeLancamento;

    protected Lancamento lancamento;

    protected Map <String, String> comentarioRecebidos;

    protected Set<String> clientesAvaliadores; //Set de Clientes que avaliaram a midia
    protected List<Avaliacoes> avaliacoesRecebidas;

    protected Set<Generos> genero;
    protected Set<String> idioma;

    protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
   


    /**
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
            clientesAvaliadores = new HashSet<>(); 
            avaliacoesRecebidas = new ArrayList<>();
            this.comentarioRecebidos = new HashMap<>();


        } catch (ParseException e) {
            System.out.println("Erro ao converter");
            // TODO: handle exception
        }
    }

    
    
    /**
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


    /**
     * Metodo Faz a midia receber determinada avaliação enviada pelo cliente
     * @param clienteLogin informa qual cliente deseja fazer a avaliação
     * @param notaAvaliacao infor a nota que o cliente deseja dar para midia
     * @return Retorna o objeto avaliçôes ou null
     * 
     */
    public Avaliacoes receberAvaliacao(String clienteLogin,int notaAvaliacao){

        Avaliacoes avaliacoes  = new Avaliacoes();
        
        if (avaliacoes != null){
            
            //Cliente que ja tiver avaliou a midia antes
            if (this.clientesAvaliadores.contains(clienteLogin)){

                System.out.println("Cliente não pode avaliar a mesma midia 2 vezes");
                return null;
            }

            //Cliente nunca avaliou essa midia antes
            else{

                if(avaliacoes.validarAvaliacao(clienteLogin, notaAvaliacao)){

                    clientesAvaliadores.add(clienteLogin);
                    avaliacoesRecebidas.add(avaliacoes);

                    return avaliacoes;
                }
            }
        }
        return null;
    }

    /**
     * Metodo faz midia receber comentarios e armazena no Map de comentarios
     * @param comentario informa Qual comentario o cliente deseja fazer
     * @param idCliente intforma Quem é o cliente que deseja fazer o comentario
     * 
     */
    public void receberComentario (String comentario, String idCliente){

        this.comentarioRecebidos.put(comentario, idCliente);
        System.out.println("Comentario Realizado com sucesso");
    }

    public void calcularMediaAvalicao(){

        if (this.avaliacoesRecebidas.size() > 0){

            BigDecimal quantidadeAvaliacoes = new BigDecimal(avaliacoesRecebidas.size());
            
            BigDecimal somaAvaliacoes = new BigDecimal(this.avaliacoesRecebidas.stream()
            .mapToInt(Avaliacoes :: getNotaAvaliacao)
            .sum());

            this.mediaAvaliacao = somaAvaliacoes.divide(quantidadeAvaliacoes, 2 , RoundingMode.HALF_UP);
        }

    }



    /**
    * Adiciona um novo serie ao Set de genero 
    * @param tipoGenero que sera adicionada
    */
    public void adicionarGenero (Generos tipoGenero){

        if (genero == null){

            genero = new HashSet<>();
        }

        genero.add(tipoGenero);
    }


    /**
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
    /**
    * Adiciona se a mídia é lançamento ou não 
    * @param tipo que sera adicionado
    */
    public void adicionarLancamento (Lancamento tipo){

        if(lancamento == null){
            lancamento = tipo;
        }
        else{
            System.out.println("Mídia já tem tipo de lançamento.");
        }
    }


    /**
    * Remove um genero do Set de genero
    * @param nomeGenero a ser removido
    */
    public void removerGenero (Generos nomeGenero){
        
        if (this.genero != null){
            this.genero.remove(nomeGenero);
        }

        return;
    }


    /**
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

        return ("IdMidia: "+this.idMidia+"|| Nome: "+this.nome+"|| Data de lançamento: "+this.dataDeLancamento.toString()+"|| Media De Avaliacoes: "+this.mediaAvaliacao + " || Idiomas:" + this.idioma +"|| Generos: " + this.genero + "|| "+ this.lancamento);
    }   


    
    /**
    * Metodo para Verificar se o genero Passado como parametro existe no Setgenero da midia
    * @param generoParaComparar
    * @return True, se o método existe no Set genero
    * @return False, se o método nao existe no Set genero
    */
    public boolean compararGenero (Generos generoParaComparar){
        
        if (this.genero!=null){

            Iterator<Generos> iterator = this.genero.iterator();

            while (iterator.hasNext()) {
                Generos elemento = iterator.next();
            
                if (generoParaComparar.equals(elemento)){
                    return true;
                }
            }
        }

        return false;
    }


    /**
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



    /**
    * Printa os elementos do Set de genero
    */
    public void mostrarGeneros (){
        
        if (this.genero!=null){

            Iterator<Generos> iterator = genero.iterator();

            while (iterator.hasNext()) {
                Generos elemento = iterator.next();
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

    public int getAvalicoesRecebidas(){

        return this.avaliacoesRecebidas.size();
    }

    public String getNome(){

        return this.nome;
    }

    public int getId(){

        return this.idMidia;
    }

    public Set<Generos> getGeneros(){

        return this.genero;
    }


    public Set<String> getIdiomas(){

        return this.idioma;
    }

    public BigDecimal getMediaAvaliacao(){

        calcularMediaAvalicao();

        return this.mediaAvaliacao;
    }
    public Lancamento getLancamento(){
        return this.lancamento;
    }
}