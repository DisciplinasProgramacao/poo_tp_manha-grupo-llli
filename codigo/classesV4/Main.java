import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Main {



    public static Plataforma geraDados() {

        Plataforma plataforma = new Plataforma();
        
        plataforma.cadastrarCliente("arquivosDeLeitura/POO_Espectadores.csv");
        plataforma.cadastrarSerie("arquivosDeLeitura/POO_Series.csv");
        plataforma.cadastrarFilme("arquivosDeLeitura/POO_Filmes.csv");
        plataforma.RegistraAudienciPorArquivo("arquivosDeLeitura/POO_Audiencia.csv"); 

        //Cliente cliente1 = plataforma.procurarClientePorLogin("Kur101885");
        // Cliente cliente2 = plataforma.procurarClientePorLogin("Sim182051");
        // Cliente cliente3 = plataforma.procurarClientePorLogin("Dav32678");
        // Cliente cliente4 = plataforma.procurarClientePorLogin("Kat86221");
       
        
        Cliente igor = new Cliente ("Igor", "Rogerin", "123");
        Midia midia = plataforma.procurarMidiaPorId("3460");
        Midia midia2 = plataforma.procurarMidiaPorId("3462");
        Midia midia3 = plataforma.procurarMidiaPorId("3465");
        Midia midia4 = plataforma.procurarMidiaPorId("3474");
        Midia midia5 = plataforma.procurarMidiaPorId("3493");

        char temp = 'A';

        igor.adicionarMidia(midia, (char)temp);
        igor.adicionarMidia(midia2, (char)temp);
        igor.adicionarMidia(midia3, (char)temp);
        igor.adicionarMidia(midia4, (char)temp);
        igor.adicionarMidia(midia5, (char)temp);

        igor.avaliarMidia(midia, 4);
        igor.avaliarMidia(midia2, 4);
        igor.avaliarMidia(midia3, 4);
        igor.avaliarMidia(midia4, 4);
        igor.avaliarMidia(midia5, 4);

        igor.definirTipoCliente();

        String s = igor.toString();

        System.out.println(s);



        Cliente cliente5 = plataforma.procurarClientePorLogin("Mit133739");
        cliente5.adicionarMidia(midia, (char)temp);
        cliente5.adicionarMidia(midia2, (char)temp);
        cliente5.adicionarMidia(midia3, (char)temp);
        cliente5.adicionarMidia(midia4, (char)temp);
        cliente5.adicionarMidia(midia5, (char)temp);

        cliente5.avaliarMidia(midia, 4);
        cliente5.avaliarMidia(midia2, 4);
        cliente5.avaliarMidia(midia3, 4);
        cliente5.avaliarMidia(midia4, 4);
        cliente5.avaliarMidia(midia5, 4);

        cliente5.definirTipoCliente();

        Midia midia6 = plataforma.procurarMidiaPorId("3811");
        Midia midia7 = plataforma.procurarMidiaPorId("3815");
        Midia midia8 = plataforma.procurarMidiaPorId("3465");
        Midia midia9 = plataforma.procurarMidiaPorId("3816");
        Midia midia10 = plataforma.procurarMidiaPorId("3823");

        char temp2 = 'F';

        cliente5.adicionarMidia(midia6, (char)temp2);
        cliente5.adicionarMidia(midia7, (char)temp2);
        cliente5.adicionarMidia(midia8, (char)temp2);
        cliente5.adicionarMidia(midia9, (char)temp2);
        cliente5.adicionarMidia(midia10, (char)temp2);
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
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }


    public static void main (String[] args){
    
        

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
                                        plataforma.procurarClientePorLogin(login).getNomeMidiasJaAssistidas();
                                        teclado.nextLine();
                                        limparTela();
                                        opcaosubmenu = subMenuCliente();
                                        break;

                                        case 3:
                                        plataforma.procurarClientePorLogin(login).getNomeMidiasAssistirFuturamente();
                                        teclado.nextLine();
                                        limparTela();
                                        opcaosubmenu = subMenuCliente();
                                        break;
                                    }
                                }    
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
                        teclado.nextLine();
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
