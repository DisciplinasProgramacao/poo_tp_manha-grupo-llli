
public class Main {
    
    public static void main (String[] args){
        
        Plataforma plataforma = new Plataforma();
        
        plataforma.cadastrarCliente("arquivosDeLeitura/POO_Espectadores.csv");
        plataforma.cadastrarSerie("arquivosDeLeitura/POO_Series.csv");
        plataforma.cadastrarFilme("arquivosDeLeitura/POO_Filmes.csv");

        plataforma.RegistraAudienciPorArquivo("arquivosDeLeitura/POO_Audiencia.csv"); 

        Cliente cliente1 = plataforma.procurarClientePorLogin("Mar123153");
        Cliente cliente2 = plataforma.procurarClientePorLogin("Sim182051");
        Cliente cliente3 = plataforma.procurarClientePorLogin("Dav32678");
        Cliente cliente4 = plataforma.procurarClientePorLogin("Kat86221");
        Cliente cliente5 = plataforma.procurarClientePorLogin("Mit133739");

        Midia midia = plataforma.procurarMidiaPorId("3857");

// ------------------------------------------------------------------------------------------------------------
// Fim Dos testes para Cadastrar: CLiente, Midias e procura-los e registrar Audiencia

        cliente1.avaliarMidia(midia, 5);
        
        // cliente1.definirTipoCliente();

        cliente2.avaliarMidia(midia, 5);

        cliente3.avaliarMidia(midia, 2);

        cliente4.avaliarMidia(midia, 4);

        cliente5.avaliarMidia(midia, 4);
        
        System.out.println(midia.toString());
    }

}
