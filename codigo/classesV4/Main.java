import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
        Map<String, Cliente> clientes = plataforma.getClientes();
        
        if (clientes != null) {
            // Percorre os clientes usando um loop foreach
            for (Cliente cliente : clientes.values()) {
                // gera um numero aleatorio para em seguida adicionar se o cliente é profissional ou não
                Random random = new Random();
                int numeroRandomico = random.nextInt(2) + 1; 
                if (numeroRandomico == 1){
                cliente.addProfissional(Profissional.Profissional);
                }
                else {
                cliente.addProfissional(Profissional.Não_profissional);
                }
            }
        }

        plataforma.procurarClientePorLogin("Igor").addProfissional(Profissional.Profissional);
        plataforma.procurarClientePorLogin("Mit133739").addProfissional(Profissional.Não_profissional);
        plataforma.procurarClientePorLogin("Vitor").addProfissional(Profissional.Não_profissional);

        plataforma.cadastrarSerie("arquivosDeLeitura/POO_Series.csv");
        plataforma.cadastrarFilme("arquivosDeLeitura/POO_Filmes.csv");
        Map<String, Midia> midias = plataforma.getMidias();
        
        if (midias != null) {
            // Percorre as mídias usando um loop foreach
            for (Midia midia : midias.values()) {
                // gera um numero aleatorio para em seguida adicionar se a mídia é lançamento ou regular
                Random random = new Random();
                int numeroRandomico = random.nextInt(2) + 1; 
                if (numeroRandomico == 1){
                midia.adicionarLancamento(Lancamento.Lançamento);
                }
                else {
                midia.adicionarLancamento(Lancamento.Regular);
                }
            }
        }

        plataforma.RegistraAudienciPorArquivo("arquivosDeLeitura/POO_Audiencia.csv"); 
        plataforma.RegistraAudienciPorArquivo("arquivosDeLeitura/POO_Audiencia2.csv"); 


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
                // gera um numero aleatorio para em seguida adicionar genero a midia
                Random random = new Random();
                int numeroRandomico = random.nextInt(9) + 1; 
                if (numeroRandomico == 1){
                midia.adicionarGenero(Generos.ACAO);
                }
                else if(numeroRandomico == 2){
                midia.adicionarGenero(Generos.DRAMA);
                }
                else if(numeroRandomico == 3){
                midia.adicionarGenero(Generos.ANIME);
                }
                else if(numeroRandomico == 4){
                midia.adicionarGenero(Generos.AVENTURA);
                }
                else if(numeroRandomico == 5){
                midia.adicionarGenero(Generos.COMEDIA);
                }
                else if(numeroRandomico == 6){
                midia.adicionarGenero(Generos.DOCUMENTARIO);
                }
                else if(numeroRandomico == 7){
                midia.adicionarGenero(Generos.POLICIAL);
                }
                else if(numeroRandomico == 8){
                midia.adicionarGenero(Generos.SUSPENSE);
                }
                else {
                midia.adicionarGenero(Generos.ROMANCE);
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
                if(plataforma.procurarClientePorLogin(campos[0]).getProfissional()==Profissional.Profissional){
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
        int opcao =-1;
        while (opcao!=0) {
        try {
            opcao = Integer.parseInt(teclado.nextLine());
            break; // Se a conversão for bem-sucedida, sai do loop
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida. Digite novamente: ");
        }
    }
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
        System.out.println("7 - Adicionar a lista de assistidos");
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = -1;
        while (opcao!=0) {
        try {
            opcao = Integer.parseInt(teclado.nextLine());
            break; // Se a conversão for bem-sucedida, sai do loop
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida. Digite novamente: ");
        }
        }
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
        int opcao =-1;
        while (opcao!= 0) {
        try {
            opcao = Integer.parseInt(teclado.nextLine());
            break; // Se a conversão for bem-sucedida, sai do loop
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida. Digite novamente: ");
        }
        }
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
        int opcao = -1;
        while (opcao!=0) {
        try {
            opcao = Integer.parseInt(teclado.nextLine());
            break; // Se a conversão for bem-sucedida, sai do loop
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida. Digite novamente: ");
        }
        }
        return opcao;
    }


public static void casesMenuUsuario(int opcaosubmenu , Plataforma plataforma, String login) {
   Scanner scanner = new Scanner(System.in);
    while (opcaosubmenu!=0){
        switch (opcaosubmenu) {
            case 1:
            limparTela();
            plataforma.getNomeMidias();
            int opcaosubmenufiltros = subMenuFiltros();
            casesMenuCatalogo(opcaosubmenufiltros, plataforma);      
                       
            break;

            case 2:
            Map<Integer, Midia> nomeMidiasAssitidas = plataforma.procurarClientePorLogin(login).getMidiasJaAssistidas();
            Map<String, Midia> nomeMidiasAssitidasString = new HashMap<>();
            for (Map.Entry<Integer, Midia> entry : nomeMidiasAssitidas.entrySet()) {
                Integer chave = entry.getKey();
                Midia midia = entry.getValue();
                String chaveString = chave.toString();
                nomeMidiasAssitidasString.put(chaveString, midia);
                System.out.println("=================================================");
                System.out.println("Midia: " + midia.toString());
            }
            int opcaosubmenufiltrosAssis = subMenuFiltros(); 
              while(opcaosubmenufiltrosAssis!=0){
                casesMenuFiltros(opcaosubmenufiltrosAssis , plataforma,  login, nomeMidiasAssitidasString);
                opcaosubmenufiltrosAssis = subMenuFiltros();
            }
          

            teclado.nextLine();
            limparTela();
            break;

            case 3:
            Map<Integer, Midia> nomeMidiasFuturamente = plataforma.procurarClientePorLogin(login).getMidiasFuturamente();
            Map<String, Midia> nomeMidiasFuturamenteString = new HashMap<>();
            for (Map.Entry<Integer, Midia> entry : nomeMidiasFuturamente.entrySet()) {
                Integer chave = entry.getKey();
                Midia midia = entry.getValue();
                String chaveString = chave.toString();
                nomeMidiasFuturamenteString.put(chaveString, midia);
                System.out.println("=================================================");
                System.out.println("Midia: " + midia.toString());
            }
            int opcaosubmenufiltrosFuturamente = subMenuFiltros(); 
            while(opcaosubmenufiltrosFuturamente!=0){
              casesMenuFiltros(opcaosubmenufiltrosFuturamente , plataforma,  login, nomeMidiasFuturamenteString);
              opcaosubmenufiltrosFuturamente = subMenuFiltros(); 
            }
            teclado.nextLine();
            limparTela();
            
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
         
                break;       
            
            case 5:
                
                System.out.println("Escreva o nome da midia para comentar");
                String nomeMidia = scanner.nextLine();

                Midia midiaParaComentar = plataforma.procurarMidiaPorNome(nomeMidia);
                if(plataforma.procurarClientePorLogin(login).getProfissional()==Profissional.Não_profissional && midiaParaComentar.getLancamento()==Lancamento.Regular){
                    System.out.println("Cliente não profissional não pode comentar mídias do tipo lançamento.");
                }
                else{
                    if (midiaParaComentar != null){

                        System.out.println("Digite o comentario");
                        String comentario = scanner.nextLine();

                        plataforma.procurarClientePorLogin(login).fazerComentario(comentario, midiaParaComentar);
                                        
                    }
                    else System.out.println("Midia nao encontrada");
                }
                
                System.out.println("\n Pressione Enter para voltar ao menu do usuário");
            
                teclado.nextLine();
                limparTela();
             
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
                }
                teclado.nextLine();
                limparTela();
           
                break;

            case 7:
                System.out.println("Escreva o nome da mídia que deseja marcar como assistida");
                String nomeMidiaAssistirAssistidas = scanner.nextLine();
                Midia midiaAssitida = plataforma.procurarMidiaPorNome(nomeMidiaAssistirAssistidas);
                if (midiaAssitida == null) {
                    System.out.println("O filme não existe.");
                    
                } else {
                    if(plataforma.procurarClientePorLogin(login).getProfissional()==Profissional.Não_profissional && midiaAssitida.getLancamento()==Lancamento.Lançamento){
                        System.out.print("Voce nao pode assistir essa midia");
                    }
                    else{
                    plataforma.procurarClientePorLogin(login).adicionarMidia(midiaAssitida, 'A');
                    System.out.println("O filme \"" + midiaAssitida.getNome() + "\" foi adicionado à lista de assistidos ");
                    }
                } 
            teclado.nextLine();
            limparTela();
            break;  
          
        }   
         opcaosubmenu = subMenuCliente();
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
            if (midiasFiltradasNome.isEmpty()) {
                System.out.println("Nenhuma mídia encontrada com o nome especificado.");
            } else {
                for (Midia midia : midiasFiltradasNome) {
                    System.out.println("=======================================================");
                    System.out.println(midia.toString());
                }
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
            if (midiasFiltradasIdioma.isEmpty()) {
                System.out.println("Nenhuma mídia encontrada com o idioma especificado.");
            } else {
                for (Midia midia : midiasFiltradasIdioma) {
                    System.out.println("=======================================================");
                    System.out.println(midia.toString());
                }
            }
            teclado.nextLine();
            opcaosubmenufiltros = subMenuFiltros();
            break;
      case 3:
    System.out.println("\nDigite o Gênero que deseja buscar:");
    String genero = teclado.nextLine();
    genero = genero.toLowerCase();
    try {
        Generos gen = Generos.obterGeneroPorNome(genero);
        Set<Midia> midiasFiltradasGenero = plataforma.aplicadorDeFiltros.filtrarGenero(gen, plataforma.getMidias());
        limparTela();
        System.out.println("Mídias filtradas pelo gênero: " + genero);
        if (midiasFiltradasGenero.isEmpty()) {
            System.out.println("Nenhuma mídia encontrada com o gênero especificado.");
        } else {
            for (Midia midia : midiasFiltradasGenero) {
                System.out.println("=======================================================");
                System.out.println(midia.toString());
            }
        }
    } catch (Exception e) {
        System.out.println("Gênero não encontrado.");
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
                limparTela();
                List<Midia> midiasComMelhorMedia = plataforma.getMidias().values().stream()
                        .filter(m -> m.getAudiencia() >= 100)
                        .sorted(Comparator.comparing(Midia::getMediaAvaliacao))
                        .collect(Collectors.toList());

                System.out.println("Top 10 mais bem avaliadas:\n");
                for (int i = 0; i < 10; i++){
                    int total = midiasComMelhorMedia.size()-1;
                    Midia midia = midiasComMelhorMedia.get(total - i);
                    System.out.printf("\t%d#: %s: %.2f (%d visualizações)\n", i+1, midia.getNome(), midia.getMediaAvaliacao(), midia.getAudiencia());
                }

                teclado.nextLine();
                opcaosubmenurelatorios = subMenuRelatorios();
                break;  

                //Quais sao as 10 midias com mais visualizacoes em ordem descrescente");
            case 5:
                limparTela();

                List<Midia>  midiasComMaisVisualizacoes = plataforma.getMidias().values().stream()
                    .sorted(Comparator.comparing(Midia::getAudiencia))
                    .collect(Collectors.toList());

                System.out.println("Top 5 mais visualizados:\n");
                for (int i = 0; i < 5; i++){
                    int total = midiasComMaisVisualizacoes.size()-1;
                    Midia midia = midiasComMaisVisualizacoes.get(total - i);
                    System.out.printf("\t%d#: %s (%d visualizações)\n", i+1, midia.getNome(), midia.getAudiencia());
                }
                
                teclado.nextLine();
                opcaosubmenurelatorios = subMenuRelatorios();
                break;  
                
                //Estes mesmos 2 ultimos relatorios, porem com as midias separadas por genero");
            case 6:
                limparTela();
                for (Generos genero : Generos.values()) {
                    System.out.printf("#%s\n", genero);
                    
                    List<Midia> midiasPorGenero = plataforma.getMidias().values().stream()
                        .filter(m -> m.getGeneros().contains(genero))
                        .filter(m -> m.getAudiencia() >= 100)
                        .sorted(Comparator.comparing(Midia::getAudiencia))
                        .collect(Collectors.toList());
                    
                    for (int i = 0; i < 5; i++){
                        int total = midiasPorGenero.size()-1;
                        Midia midia = midiasPorGenero.get(total - i);
                        System.out.printf("\t-%s: %.2f (%d visualizações) %s\n", midia.getNome(), midia.getMediaAvaliacao(), midia.getAudiencia(), midia.getGeneros().toString());
                    }

                    System.out.println("\n");
                }
                teclado.nextLine();
                opcaosubmenurelatorios = subMenuRelatorios();
                break;  
            }
        
    }

}

//case de menus do usuário com opções de filtros
public static void casesMenuFiltros(int opcaosubmenufiltros , Plataforma plataforma, String login, Map<String, Midia> mapMidiasUsuarios) {
    switch(opcaosubmenufiltros){
       case 1:
        System.out.println("\nDigite o nome que deseja buscar:");
        String nome = teclado.nextLine();
        Set<Midia> midiasFiltradasNome = plataforma.procurarClientePorLogin(login).aplicadorDeFiltros.filtrarNome(nome, mapMidiasUsuarios);
        if (midiasFiltradasNome.isEmpty()) {
            System.out.println("Nenhum resultado encontrado para o nome: " + nome);
        } else {
            for (Midia midia : midiasFiltradasNome) {
                System.out.println("=======================================================");
                System.out.println(midia.toString());
            }
        }
    teclado.nextLine();
    break;

case 2:
   
        System.out.println("\nDigite o idioma que deseja buscar:");
        String idioma = teclado.nextLine();
        Set<Midia> midiasFiltradasidioma = plataforma.procurarClientePorLogin(login).aplicadorDeFiltros.filtrarIdioma(idioma, mapMidiasUsuarios);
        if (midiasFiltradasidioma.isEmpty()) {
            System.out.println("Nenhum resultado encontrado para o idioma: " + idioma);
        } else {
            for (Midia midia : midiasFiltradasidioma) {
                System.out.println("=======================================================");
                System.out.println(midia.toString());
            }
        }
  
    teclado.nextLine();
    break;

case 3:
    System.out.println("\nDigite o gênero que deseja buscar:");
    String genero = teclado.nextLine();
    genero = genero.toLowerCase();

    try {
        Generos gen = Generos.obterGeneroPorNome(genero);
        Set<Midia> midiasFiltradasGenero = plataforma.aplicadorDeFiltros.filtrarGenero(gen, mapMidiasUsuarios);
        if (midiasFiltradasGenero.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (Midia midia : midiasFiltradasGenero) {
            System.out.println("=======================================================");
            System.out.println(midia.toString());
        }
    } catch (IllegalArgumentException e) {
        System.out.println("Nenhum resultado encontrado para o gênero: " + genero);
    }

    teclado.nextLine();

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
                            System.out.println("Agora digite sua senha");
                            String senha = teclado.nextLine();
                            if(plataforma.procurarClientePorLogin(login).getSenha().equals(senha)){
                                limparTela();
                                System.out.println("Seja Bem Vindo "+ plataforma.procurarClientePorLogin(login).getNome()+" ! O que deseja fazer? "+plataforma.procurarClientePorLogin(login).getProfissional());
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
                        String profissional;

                        System.out.println("Digite o seu nome");
                        nome = scanner.nextLine();


                        System.out.println("Digite o Login ");
                        loginTemp = scanner.nextLine();


                        System.out.println("Digite a senha");
                        senha = scanner.nextLine();

                        plataforma.adicionarCliente(nome,loginTemp,senha);

                        System.out.println("Voce e profissioal? digite sim ou não ");
                        profissional = scanner.nextLine();

                        if (profissional.toLowerCase().equals("sim")){
                            plataforma.procurarClientePorLogin(loginTemp).addProfissional(Profissional.Profissional);
                        }
                        else {

                            plataforma.procurarClientePorLogin(loginTemp).addProfissional(Profissional.Não_profissional);
                        }
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
