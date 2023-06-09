import java.io.*;
import java.util.Scanner;

import java.util.Map;
import java.util.HashMap;

import java.util.HashSet;
import java.util.Set;

import java.io.File;
import java.io.FileNotFoundException;


/*
* Classe Plataforma: Agregação com "Midia" e "Cliente"
*/
public class Plataforma {

    //#region controle
    private Map<String, Cliente> clientes;
    private Map<String, Midia> midias;
    public IFiltragem aplicadorDeFiltros = new FiltrosDeMidias();

    //#endregion

    /*
    *
    * Construtor. Um Plataforma para ser contruida nao precisa de parametros
    * Ela apenas instancia um Map de midia e Cliente
    */
    Plataforma (){
        
        this.clientes = new HashMap<>(80000, 0.8f);
        this.midias = new HashMap<>(1000,0.7f);

    }
    
    
    
    /*
    * Função para que ira ler uma arquivo e vai adionar Clientes ao Map de clientes
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


                Cliente temp = new Cliente(campos[0],campos[1],campos[2]);
                this.clientes.put(campos[1],temp);

            }

            // Fecha o Scanner após a leitura do arquivo
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + nomeArquivo);
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Erro no Arquivo");
 
        }
    }







    /*
    * Função para que ira ler uma arquivo e vai adionar Serie ao Map de Midia
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

                Serie temp = (new Serie(campos[0],campos[1],campos[2]));

                this.midias.put(campos[0], temp);
            }

            // Fecha o Scanner após a leitura do arquivo
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + nomeArquivo);
            e.printStackTrace();
        }
    }


    /*
    * Função para que ira ler uma arquivo e vai adionar Filme ao Map de Midia
    * @param nomeArquivo
    */
    public void cadastrarFilme(String nomeArquivo){

        
        try {

            // Cria um objeto Scanner para ler o arquivo
            Scanner scanner = new Scanner(new File(nomeArquivo));
            
            //Para pular a primeira linha do arquivo
            if (scanner.hasNextLine()){
                scanner.nextLine();
            }
            // Lê cada linha do arquivo até o final
            while (scanner.hasNextLine()) {
                
                String linha = scanner.nextLine();
                String[] campos = linha.split(";");

                Filme temp = (new Filme(campos[0],campos[1],campos[2], campos[3]));

                this.midias.put(campos[0], temp);
            }

            // Fecha o Scanner após a leitura do arquivo
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + nomeArquivo);
            e.printStackTrace();
        }
    }


    /*
    * Função para que ira ler uma arquivo com logins, F/A, idMidia,
    * F/A indica a qual lista iremos mexer no cliente, F: assistir futuramente, A: ja assistidos
    * Vai verificar se o login e a serie existem
    * Vai adicionar a Midia ao Map do Cliente especificado
    * Vai adicionar nova Audiencia a Midia especificada
    * @param nomeArquivo
    */
    public void RegistraAudienciPorArquivo (String nomeArquivo){


        try {

            // Cria um objeto Scanner para ler o arquivo
            Scanner scanner = new Scanner(new File(nomeArquivo));

            // Lê cada linha do arquivo até o final
            while (scanner.hasNextLine()) {
                
                String linha = scanner.nextLine();
                String[] campos = linha.split(";");

                Cliente clienteTemporario = procurarClientePorLogin(campos[0]);
                Midia midiaTemporaria = procurarMidiaPorId(campos[2]);
                
                //Se meu cliente e Midia existem na plataforma
                if ((clienteTemporario != null) && (midiaTemporaria != null)){

                    clienteTemporario.adicionarMidia(midiaTemporaria, campos[1].charAt(0));

                }
                // else if (clienteTemporario == null){

                //     System.out.println("Cliente "+ campos[0] +" não encontrado");
                // }         
                // else if (midiaTemporaria == null){

                //     System.out.println("Mida "+ campos[2] +" não encontrado");
                // }
                
                
            }

            // Fecha o Scanner após a leitura do arquivo
            scanner.close();
            return;
            
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + nomeArquivo);
            e.printStackTrace();
            
        } catch (IOException e){
            System.out.println("Erro de E/S ao escrever no arquivo");
        }

    }


    
    
    /*
    * Função para Procurar um cliente por Login e retorna o Cliente caso encontrado
    * @param login
    */
    public Cliente procurarClientePorLogin(String login) {

        return this.clientes.get(login);

    }

  


    /*
    * Função para Procurar uma Midia por idMidia e retorna a Midia caso encontrado
    * @param idMidia
    */
    public Midia procurarMidiaPorId(String idMidia) {

        return this.midias.get(idMidia);

    }


    public void getNomeMidias() {

        System.out.println("Lista geral de filmes e séries");

        for (Midia midia : midias.values()) {
            System.out.println("==========================");
            System.out.println("Nome: "+ midia.getNome()+ " -- Audiencia :"+ midia.getAudiencia());
        }


    }
    
}
