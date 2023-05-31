
public class Main {
    
    public static void main (String[] args){
        
        Plataforma plataforma = new Plataforma();
        
        plataforma.cadastrarCliente("arquivosDeLeitura/POO_Espectadores.csv");
        plataforma.cadastrarSerie("arquivosDeLeitura/POO_Series.csv");
        plataforma.cadastrarFilme("arquivosDeLeitura/POO_Filmes.csv");

        plataforma.RegistraAudienciPorArquivo("arquivosDeLeitura/POO_Audiencia.csv"); 

        
        
        
        
        //Cliente cliente1 = plataforma.procurarClientePorLogin("Kur101885");
        // Cliente cliente2 = plataforma.procurarClientePorLogin("Sim182051");
        // Cliente cliente3 = plataforma.procurarClientePorLogin("Dav32678");
        // Cliente cliente4 = plataforma.procurarClientePorLogin("Kat86221");
        // Cliente cliente5 = plataforma.procurarClientePorLogin("Mit133739");
        
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

        igor.fazerComentario("Corto eles com a samehada", midia5);
        //cliente1.avaliarMidia(midia2, 3);

        //cliente1.definirTipoCliente();

    }

}
