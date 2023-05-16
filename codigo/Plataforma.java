import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/*
* Classe Platafomra: Agregação com "Serie" e "Cliente"
*/
public class Plataforma {

    private List <Cliente> clientes;
    private List <Serie> series;





    /*
    *
    * Construtor. Um Plataforma para ser contruida nao precisa de parametros
    * Ela apenas instancia uam lista de Serie e Cliente
    */
    Plataforma (){
        
        this.clientes = new ArrayList<Cliente>();

        this.series = new ArrayList <Serie>();

    }
    
    
    
    /*
    * Função para que ira ler uma arquivo e vai adionar Clientes a lista de clientes
    * @param nomeArquivo
    */
    public void cadastrarCliente (String nomeArquivo){

        
        try {

            // Cria um objeto Scanner para ler o arquivo
            Scanner scanner = new Scanner(new File(nomeArquivo));
            
            
            // Lê cada linha do arquivo até o final
            while (scanner.hasNextLine()) {
                
                String linha = scanner.nextLine();
                String[] campos = linha.split(";");


                this.clientes.add(new Cliente(campos[0],campos[1],campos[2]));
                //System.out.println(linha);

            }

            // Fecha o Scanner após a leitura do arquivo
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + nomeArquivo);
            e.printStackTrace();
        }
    }



    /*
    * Função para que ira ler uma arquivo e vai adionar Serie a lista de Serie
    * @param nomeArquivo
    */
    public void cadastrarSerie (String nomeArquivo){

        
        try {

            // Cria um objeto Scanner para ler o arquivo
            Scanner scanner = new Scanner(new File(nomeArquivo));
            
            
            // Lê cada linha do arquivo até o final
            while (scanner.hasNextLine()) {
                
                String linha = scanner.nextLine();
                String[] campos = linha.split(";");


                this.series.add(new Serie(campos[0],campos[1],campos[2]));
                //System.out.println(linha);

            }

            // Fecha o Scanner após a leitura do arquivo
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + nomeArquivo);
            e.printStackTrace();
        }
    }



    /*
    * Função para que ira ler uma arquivo com logins, F/A, idSerie,
    * Vai verificar se o login e a serie existem
    * Vai adicionar a Serie a lista do CLiente especificado
    * Vai adicionar nova Audiencia a Serie especificada
    * @param nomeArquivo
    */
    public void arquivoRegistraAudiencia (String nomeArquivo){


        try {

            // Cria um objeto Scanner para ler o arquivo
            Scanner scanner = new Scanner(new File(nomeArquivo));
            FileWriter fw = new FileWriter("arquivosDeLeitura/Audiencia.txt");
            
            // Lê cada linha do arquivo até o final
            while (scanner.hasNextLine()) {
                
                String linha = scanner.nextLine();
                String[] campos = linha.split(";");

                Cliente clienteTemporario = procurarClientePorLogin(campos[0]);
                Serie serieTemporaria = procurarSeriePorId(campos[2]);

                if (clienteTemporario != null && serieTemporaria != null){

                    clienteTemporario.adicionarSeries(serieTemporaria, campos[1].charAt(0));
                }
            }

            // Fecha o Scanner após a leitura do arquivo
            scanner.close();
            return;
            //fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + nomeArquivo);
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Erro de E/S ao escrever no arquivo");
        }

    }


    
    
    /*
    * Função para Procurar um cliente por Login e retorna o CLiente caso encontrado
    * @param login
    */
    private Cliente procurarClientePorLogin(String login) {

        for (Cliente cliente : clientes) {

            if (cliente.getLogin().equals(login)) {
                return cliente;
            }
        }
        return null; // Retorna null se o cliente não for encontrado
    }



    /*
    * Função para Procurar uma Serie por idSerie e retorna a Serie caso encontrado
    * @param login
    */
    public Serie procurarSeriePorId(String idSerie) {

        if (idSerie.matches("-?\\d+")){

            
            for (Serie serie : series) {
                if (serie.getId() == Integer.valueOf(idSerie)) {

                    //System.out.println("Serie: " +idSerie);
                    return serie;
                }
            }
            
            //System.out.println("Serie nao encontrada: " +idSerie);
            return null;
        }
        else {

            System.out.println("IdSerie invalido");
            return null; // Retorna null se a série não for encontrada
        }
    }



    /*
    * Filtra as series presentes no catalogo pelo nome
    * retorna uma lista de serie com o nome desejado
    * @param nome a ser procurado no catalogo
    */
    public List<Serie> filtrarNome (String nome, List <Serie> listaParaSerFiltrada){

        List <Serie> listaFiltradaPorNome = new ArrayList<Serie>();

        for (int i = 0; i < listaParaSerFiltrada.size(); i++){
            
            Serie temp = listaParaSerFiltrada.get(i);
            
            String nomePadronizado = nome.toLowerCase();
            String nomePadronizado2 = temp.getNome().toLowerCase();

            if (nomePadronizado.equals(nomePadronizado2)){
                listaFiltradaPorNome.add(temp);
            }
            
        }
        return listaFiltradaPorNome;
    }



    /*
    * Filtra as series presentes no catalogo pelo genero
    * retorna uma lista de serie com o genero desejado
    * @param genero a ser procurado no catalogo
    */
    public List<Serie>filtrarGenero(String genero, List <Serie> listaParaSerFiltrada){

        List <Serie> listaFiltradaPorGenero = new ArrayList<Serie>();

        for (int i = 0; i < listaParaSerFiltrada.size(); i++){

            Serie temp = listaParaSerFiltrada.get(i);
            List <String> tempGenero = temp.getGenero();

            
            for (int j = 0; j < tempGenero.size(); j++){

                if (genero.toLowerCase().equals(tempGenero.get(j).toLowerCase())){
                    
                    listaFiltradaPorGenero.add(temp);
                    break;
                }
                
            }

        }
        return listaFiltradaPorGenero;
    }



    /*
    * Filtra as series presentes no catalogo pelo idioma
    * retorna uma lista de serie com o idioma desejado
    * @param idioma a ser procurado no catalogo
    */
    public List<Serie>filtrarIdioma(String idioma, List <Serie> listaParaSerFiltrada){

        List <Serie> listaFiltradaPorIdioma = new ArrayList<Serie>();

        for (int i = 0; i < listaParaSerFiltrada.size(); i++){
            
            Serie temp = listaParaSerFiltrada.get(i);
            List <String> tempIdioma = temp.getIdioma();
            
            for (int j = 0; j < tempIdioma.size(); j++){

                if (idioma.toLowerCase().equals(tempIdioma.get(j).toLowerCase())){
                    
                    listaFiltradaPorIdioma.add(temp);
                    break;
                }
                
            }

        }
        return listaFiltradaPorIdioma;
    }

    


    /*
    * Junta listas de series
    * Caso o cliente deseja fazer uma filtragem de mais de um tipo ele realiza as filtragens. 
    * Pega a lista que retornar e passa como input nessa funcao ex.
    * retorna uma lista de serie.
    * @param Lista de Serie A
    * @param Lista de Serie B
    */
    public List<Serie> juntarListasFiltradas (List <Serie> a, List <Serie> b){

        List <Serie> listasFiltradas = new ArrayList<Serie>();

        for (int i = 0; i < a.size(); i++){
            
            listasFiltradas.add(a.get(i));
        }

        for (int i = 0; i < b.size(); i++){
            
            listasFiltradas.add(b.get(i));
        }


        return filtrarDuplicadas(listasFiltradas);
    }


    
    /*
    * Verifica se existem series duplicadas na lista e romove elas e retorna uma lista de serie sem series repetidas
    * @param Lista de Serie A
    */    
    private List<Serie> filtrarDuplicadas (List <Serie> a){

        Serie temp;
        Serie temp2;

        for (int i = 0; i < a.size(); i++){

            temp = a.get(i);
            for (int j = i+1; j < a.size(); j++){

                temp2 = a.get(j);

                 if (temp.getNome().toLowerCase().equals(temp2.getNome().toLowerCase())){
                     
                    a.remove(j);
                }
            }
        }

        return a;
    }





    public List<Cliente> getClientesLista (){

        return this.clientes;
    }


    public List<Serie> getSerieLista (){

        return this.series;
    }

}
