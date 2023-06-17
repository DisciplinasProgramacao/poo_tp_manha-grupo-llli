import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {


    

    public static Plataforma geraDados() {

        Plataforma plataforma = new Plataforma();
        
        plataforma.cadastrarCliente("arquivosDeLeitura/POO_Espectadores.csv");
        plataforma.cadastrarSerie("arquivosDeLeitura/POO_Series.csv");
        plataforma.cadastrarFilme("arquivosDeLeitura/POO_Filmes.csv");
        plataforma.RegistraAudienciPorArquivo("arquivosDeLeitura/POO_Audiencia.csv"); 
        plataforma.RegistraAudienciPorArquivo("arquivosDeLeitura/POO_Audiencia2.csv"); 

  

        Map<String, Midia> midias = plataforma.getMidias();

        if (midias != null) {
            // Percorre as mídias usando um loop foreach
            for (Midia midia : midias.values()) {
                // gera um numero aleatorio para em seguida adicionar idioma a midia
                Random random = new Random();
                int numeroRandomico = random.nextInt(3) + 1; 
                if (numeroRandomico == 1){
                midia.adicionarIdioma("ingles");
                }
                else if(numeroRandomico == 2){
                midia.adicionarIdioma("portugues");
                }
                else {
                midia.adicionarIdioma("espanhol");
                }
            }
        }

        if (midias != null) {
            // Percorre as mídias usando um loop foreach
            for (Midia midia : midias.values()) {
                // gera um numero aleatorio para em seguida adicionar idioma a midia
                Random random = new Random();
                int numeroRandomico = random.nextInt(3) + 1; 
                if (numeroRandomico == 1){
                midia.adicionarGenero("acao");
                }
                else if(numeroRandomico == 2){
                midia.adicionarGenero("drama");
                }
                else {
                midia.adicionarGenero("romance");
                }
            }
        }

        try {

           //Cria um objeto Scanner para ler o arquivo
            Scanner scanner = new Scanner(new File("arquivosDeLeitura/POO_Audiencia2.csv"));
            
            int contador = 1;
           //ê cada linha do arquivo até o final
            while (scanner.hasNextLine()) {
                
                String linha = scanner.nextLine();
                String[] campos = linha.split(";");
                
                Midia temp = plataforma.procurarMidiaPorId(campos[2]);

                if (contador == 1)plataforma.procurarClientePorLogin(campos[0]).avaliarMidia(temp,contador);
                else if (contador == 2)plataforma.procurarClientePorLogin(campos[0]).avaliarMidia(temp,contador);
                else if (contador == 3)plataforma.procurarClientePorLogin(campos[0]).avaliarMidia(temp,contador);
                else if (contador == 4)plataforma.procurarClientePorLogin(campos[0]).avaliarMidia(temp,contador);
                else if (contador == 5){
                    
                    plataforma.procurarClientePorLogin(campos[0]).avaliarMidia(temp,contador);
                    contador = 0;
                }
                contador++;
            }

           //Prencha o Scanner após a leitura do arquivo
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
            e.printStackTrace();
        }


        return plataforma;
    }
    

    static Scanner teclado = new Scanner(System.in);
    static final String MENU_PRINCIPAL = "dados/menuPrincipal";

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }



    public static int menu(String nomeArquivo) throws FileNotFoundException {
        limparTela();
        File arqMenu = new File(nomeArquivo);
        Scanner leitor = new Scanner(arqMenu, "UTF-8");
        System.out.println(leitor.nextLine());
        System.out.println("==========================");
        int contador = 1;
        while(leitor.hasNextLine()){
            System.out.println(contador + " - " + leitor.nextLine());
            contador++;
        }
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        leitor.close();
        return opcao;
    }

    public static int subMenuCliente() {
        System.out.println("");
        System.out.println("==========================");
        System.out.println("1 - Ver lista de series disponiveis");
        System.out.println("2 - Ver lista de series já assistidas");
        System.out.println("3 - Ver lista assistir futuramente");
        System.out.println("4 - Avaliar Midia");
        System.out.println("5 - Comentar Midia");
        System.out.println("6 - Adicionar a lista de assistir futuramente");
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }



    public static int subMenuRelatorios() {
        System.out.println("");
        System.out.println("==========================");
        System.out.println("1 - Qual CLiente Assistiu a mais midias, e quantas midias");
        System.out.println("2 - Qual cliente tem mais avaliacoes e quantas avaliacoes");
        System.out.println("3 - Qual a porcentagem dos clientes com pelomenos 15 avaliacoes");
        System.out.println("4 - Quais sao as 10 midias com a melhor media de avaliacoes e que tenham sido vistas pelo menos 100 vezes, apresentadas em ordem decrescente");
        System.out.println("5 - Quais sao as 10 midias com mais visualizacoes em ordem descrescente");
        System.out.println("6 - Estes mesmos 2 ultimos relatorios, porem com as midias separadas por genero");
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }




    public static int subMenuFiltros() {
        System.out.println("");
        System.out.println("==========================");
        System.out.println("1 - Filtrar pelo Nome");
        System.out.println("2 - Filtrar pelo idioma");
        System.out.println("3 - Filtrar pelo Genero");
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

public static void casesMenuUsuario(int opcaosubmenu , Plataforma plataforma, String login) {
   Scanner scanner = new Scanner(System.in);
    while (opcaosubmenu!=0){
        switch (opcaosubmenu) {
            case 1:
            plataforma.getNomeMidias();
            System.out.println("\n Pressione Enter para voltar ao menu do usuário");
            teclado.nextLine();
            limparTela();
            opcaosubmenu = subMenuCliente();
            break;

            case 2:
            List<String> nomeMidiasAssitidas = plataforma.procurarClientePorLogin(login).getMidiasJaAssistidas();
            for (String nomeMidia : nomeMidiasAssitidas) {
                System.out.println("=================================================");
                 System.out.println(nomeMidia);
            }
            int opcaosubmenufiltrosAssis = subMenuFiltros();  
            teclado.nextLine();
            limparTela();
            opcaosubmenu = subMenuCliente();
            break;

            case 3:
                List<String> nomeMidiasFuturamente = plataforma.procurarClientePorLogin(login).getMidiasFuturamente();
                if (nomeMidiasFuturamente != null && !nomeMidiasFuturamente.isEmpty()) {
                    for (String nomeMidia : nomeMidiasFuturamente) {
                    System.out.println("=================================================");
                    System.out.println(nomeMidia);
                }
                } else {
                System.out.println("Lista Assistir Futuramente Vazia");
                } 
                int opcaosubmenufiltrosFut = subMenuFiltros();                  
                teclado.nextLine();
                limparTela();
                opcaosubmenu = subMenuCliente();
                break;

            case 4:

                System.out.println("Escreva o nome da midia para avaliar");
                String nomeMida = scanner.nextLine();

                Midia midiaParaAvaliar = plataforma.procurarMidiaPorNome(nomeMida);

                if (midiaParaAvaliar != null){

                    System.out.println("Digite a nota para midia de 1 a 5");
                    int nota = scanner.nextInt();

                    plataforma.procurarClientePorLogin(login).avaliarMidia(midiaParaAvaliar, nota);
                                    
                }
                else System.out.println("Midia nao encontrada");
                
                System.out.println("\n Pressione Enter para voltar ao menu do usuário");
            
                teclado.nextLine();
                limparTela();
                opcaosubmenu = subMenuCliente();
                break;       
            
            case 5:
                
                System.out.println("Escreva o nome da midia para comentar");
                String nomeMidia = scanner.nextLine();

                Midia midiaParaComentar = plataforma.procurarMidiaPorNome(nomeMidia);

                if (midiaParaComentar != null){

                    System.out.println("Digite o comentario");
                    String comentario = scanner.nextLine();

                    plataforma.procurarClientePorLogin(login).fazerComentario(comentario, midiaParaComentar);
                                    
                }
                else System.out.println("Midia nao encontrada");
                
                System.out.println("\n Pressione Enter para voltar ao menu do usuário");
            
                teclado.nextLine();
                limparTela();
                opcaosubmenu = subMenuCliente();
                break;       
            
            case 6: 

                System.out.println("Escreva o nome da mídia para assistir futuramente");
                String nomeMidiaAssistirFuturamente = scanner.nextLine();
                Midia midiaAssistirFuturamente = plataforma.procurarMidiaPorNome(nomeMidiaAssistirFuturamente);
                if (midiaAssistirFuturamente == null) {
                    System.out.println("O filme não existe.");
                } else {
                    plataforma.procurarClientePorLogin(login).adicionarMidia(midiaAssistirFuturamente, 'F');
                    System.out.println("O filme \"" + midiaAssistirFuturamente.getNome() + "\" foi adicionado à lista de assistir futuramente.");
                    teclado.nextLine();
                    limparTela();
                    opcaosubmenu = subMenuCliente();
                    break;
                }

            case 7:
                teclado.nextLine();
                limparTela();
                opcaosubmenu = subMenuCliente();
                break;       
        }   
    }  
}


public static void casesMenuCatalogo(int opcaosubmenufiltros , Plataforma plataforma) {
    while (opcaosubmenufiltros!=0){
        switch (opcaosubmenufiltros) {
            case 1:
                System.out.println("\nDigite o nome que deseja buscar:");
                String nome = teclado.nextLine();
                Set<Midia> midiasFiltradasNome = plataforma.aplicadorDeFiltros.filtrarNome(nome, plataforma.getMidias());
                limparTela();
                System.out.println("Mídias filtradas por Nome: " + nome);
                for (Midia midia : midiasFiltradasNome) {

                System.out.println("=======================================================");
                System.out.println(midia.toString());
                }
                teclado.nextLine();
                opcaosubmenufiltros = subMenuFiltros();
                break;
            case 2:
                System.out.println("\nDigite o idioma que deseja buscar:");
                String idioma = teclado.nextLine();
                idioma = idioma.toLowerCase();
                Set<Midia> midiasFiltradasIdioma = plataforma.aplicadorDeFiltros.filtrarIdioma(idioma, plataforma.getMidias());
                limparTela();
                System.out.println("Mídias filtradas pelo idioma: " + idioma);
                for (Midia midia : midiasFiltradasIdioma) {
                System.out.println("=======================================================");
                    System.out.println(midia.toString());
                }
                teclado.nextLine();
                opcaosubmenufiltros = subMenuFiltros();
                break;
                case 3:
                System.out.println("\nDigite o Genero que deseja buscar:");
                String genero = teclado.nextLine();
                genero = genero.toLowerCase();
                Set<Midia> midiasFiltradasGenero = plataforma.aplicadorDeFiltros.filtrarGenero(genero, plataforma.getMidias());
                limparTela();
                System.out.println("Mídias filtradas pelo idioma: " + genero);
                for (Midia midia : midiasFiltradasGenero) {
                System.out.println("=======================================================");
                System.out.println(midia.toString());
                }
                teclado.nextLine();
                opcaosubmenufiltros = subMenuFiltros();
                break;
        }

    } 
    
}


public static void casesMenuRelatorios(int opcaosubmenurelatorios , Plataforma plataforma) {
    while (opcaosubmenurelatorios!=0){
        switch (opcaosubmenurelatorios) {
            case 1:
            
                limparTela();

                Optional<Cliente> clienteComMaiorTotalMidiasAssistidas = plataforma.getClientes().values().stream()
                                            .max(Comparator.comparingInt(Cliente::totalMidiasJaAssistidas));

                if (clienteComMaiorTotalMidiasAssistidas.isPresent()){

                    Cliente cliente = clienteComMaiorTotalMidiasAssistidas.get();
                    int maiorTotalMidiasAssistidas = cliente.totalMidiasJaAssistidas();
                    System.out.println("Cliente com maior total de mídias assistidas: " + cliente.getNome());
                    System.out.println("Total de mídias assistidas: " + maiorTotalMidiasAssistidas);
                } 
                else {
                    System.out.println("Não há clientes com Mídias já assistidas");
                }

                

                teclado.nextLine();
                opcaosubmenurelatorios = subMenuRelatorios();
                break;

            case 2:

                limparTela();
                
                Optional<Cliente> clienteComMaiorTotalMidiasAvaliadas = plataforma.getClientes().values().stream()
                                        .max(Comparator.comparingInt(Cliente::totalMidiasAvaliadas));
                
                if (clienteComMaiorTotalMidiasAvaliadas.isPresent()){

                    Cliente cliente = clienteComMaiorTotalMidiasAvaliadas.get();
                    int totalMidiasAvaliadas = cliente.totalMidiasAvaliadas();
                    System.out.println("Cliente com maior total de mídias assistidas: " + cliente.getNome());
                    System.out.println("Total de mídias Avalidas: " + totalMidiasAvaliadas);
                } 
                else {
                    System.out.println("Não há clientes com Mídias já assistidas");
                }

                
                teclado.nextLine();
                opcaosubmenurelatorios = subMenuRelatorios();
                break;  
            
                
                case 3:
                
                int temp = (int) plataforma.getClientes().values().stream()
                                                    .filter(c -> plataforma.procurarClientePorLogin(c.getLogin()).totalMidiasAvaliadas() >= 15)
                                                    .count();
                                                    int totalClientes = plataforma.getClientes().size();
                
                System.out.println("Porcentagem de clientes que avaliaram mais de 15 Midias: "+((double) temp * 100 / totalClientes)+"%");                                    
                
                teclado.nextLine();
                opcaosubmenurelatorios = subMenuRelatorios();
                break;  
                
                //Quais sao as 10 midias com a melhor media de avaliacoes e que tenham sido vistas pelo menos 100 vezes, apresentadas em ordem decrescente");
                case 4:

                    // plataforma.getMidias().values().stream()
                    //                                .filter(m -> m.getAvalicoesRecebidas() > 0)
                    //                                .forEach(Midia::calcularMediaAvalicao);

                    // List<Midia> midiasMaiorAvaliacao = new ArrayList<>();

                    // midiasMaiorAvaliacao = plataforma.getMidias().values().stream()
                    //                                .filter(m -> m.getAudiencia() >= 100)
                    //                                .sorted(Comparator.comparing(m -> m.getMediaAvaliacao()))
                    //                                .limit(10)
                    //                                .collect(Collectors.toList());


                    plataforma.getMidias().values().stream()
                                                    .filter(m -> m.getAudiencia() >= 100)
                                                    .limit(5)
                                                    .forEach(midia -> System.out.println(midia.getNome() + " - Audiência: " + midia.getAudiencia()+ " - Media Avaliacao: " +midia.getMediaAvaliacao() + " - Quantidade De avaliacoes Recebidas: " +midia.getAvalicoesRecebidas()));


                    // for (Midia midia : midiasMaiorAvaliacao) {
                    //     System.out.println(midia.getNome() + " - Média de Avaliações: " + midia.getMediaAvaliacao());
                    // }

                    teclado.nextLine();
                    opcaosubmenurelatorios = subMenuRelatorios();
                    break;  

                //Quais sao as 10 midias com mais visualizacoes em ordem descrescente");
                case 5:



                    teclado.nextLine();
                    opcaosubmenurelatorios = subMenuRelatorios();
                    break;  
                
                //Estes mesmos 2 ultimos relatorios, porem com as midias separadas por genero");
                case 6:

                    teclado.nextLine();
                    opcaosubmenurelatorios = subMenuRelatorios();
                    break;  
            }   
        
    }

}

    public static void main (String[] args){
    
     Scanner scanner = new Scanner(System.in);

        Plataforma plataforma = geraDados(); 
        
        int opcao = -1;
        do {
            try {
                opcao = menu(MENU_PRINCIPAL);
                switch (opcao) {
                    case 1:
                        System.out.println("Digite o seu Login");
                        String login = teclado.nextLine();
                        limparTela();
                        if(plataforma.procurarClientePorLogin(login)!=null){
                            System.out.println("\nOlá ! " + plataforma.procurarClientePorLogin(login).getNome()+ "\nAgora digite sua senha");
                            String senha = teclado.nextLine();
                            if(plataforma.procurarClientePorLogin(login).getSenha().equals(senha)){
                                limparTela();
                                System.out.println("Seja Bem Vindo "+ plataforma.procurarClientePorLogin(login).getNome()+" ! O que deseja fazer ?");
                                int opcaosubmenu = subMenuCliente();
                                casesMenuUsuario(opcaosubmenu, plataforma, login);
                            }
                            else{
                                System.out.println("Senha incorreta");
                                teclado.nextLine();
                            }
                        }
                        else{
                          System.out.println("Login não existe, precione enter para voltar ao menu");
                          teclado.nextLine();
                        } 
                        break;
                    case 2:
                        limparTela();
                        plataforma.getNomeMidias();
                        int opcaosubmenufiltros = subMenuFiltros();
                        casesMenuCatalogo(opcaosubmenufiltros, plataforma);      
                        break;

                    case 3:

                        String nome;
                        String loginTemp;
                        String senha;

                        System.out.println("Digite o seu nome");
                        nome = scanner.nextLine();


                        System.out.println("Digite o Login ");
                        loginTemp = scanner.nextLine();


                        System.out.println("Digite a senha");
                        senha = scanner.nextLine();

                        plataforma.adicionarCliente(nome,loginTemp,senha);
                        System.out.println("Cliente cadastrado com sucesso");
                        break;

                    case 4:
                        System.out.println("\n Qual relatório deseja visualizar ?");
                        int opcaosubmenurelatorios = subMenuRelatorios();
                        casesMenuRelatorios(opcaosubmenurelatorios, plataforma);
                        break;
                        
                }
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado: " + e.getMessage());
            }
        } while (opcao != 0);
        System.out.println("Até mais, obrigado!");
        

        //igor.toString();
        //cliente1.avaliarMidia(midia2, 3);

        //cliente1.definirTipoCliente();
        // System.out.println("Atributos da Mídia:");
        //System.out.println("ID: " + midia.getId());
       // System.out.println("Nome: " + midia.getNome());
    //igor.fazerComentario("Corto eles com a samehada", midia5);

    }

}
