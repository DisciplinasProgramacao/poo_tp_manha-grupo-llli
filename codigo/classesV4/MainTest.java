/*
Desativar a extensão "Maven for Java" para visualizar outputs
Issue: https://github.com/Microsoft/vscode-java-test/issues/344
*/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class MainTest {
    /*
    @BeforeEach
    public void init(){

    }
    */
    
    @Test
    public void JUnit(){
        int x = 1 + 1;

        File f = new File(System.getProperty("user.dir") + "README.md");
        System.out.println(System.getProperty("user.dir"));
        System.out.println(f.exists());

        assertEquals(2, x);
    }

    @Test
    public void AdicionarUsuario(){
        Plataforma p = new Plataforma();
        System.out.printf("Clientes: %d\n", p.getClientes().size());
        assertEquals(0, p.getClientes().size());
        

        p.adicionarCliente("Leonardo", "ArquiRibeiro", "123");
        System.out.printf("Clientes: %d\n", p.getClientes().size());
        assertEquals(1, p.getClientes().size());
    }

    @Test
    public void encontrarFilmePorNome(){
        Plataforma p = new Plataforma();
        assertEquals(0, p.getMidias().size());

        /*
        File f = new File(System.getProperty("user.dir") + "/bin/arquivosDeLeitura");
        
        for(File x : f.listFiles()){
            System.out.println("DAWD" + x);
        }
        */

        p.cadastrarFilme("bin/arquivosDeLeitura/POO_Filmes.csv");
        assertNotEquals(0, p.getMidias().size());

        String nomePrimeiroFilme =  p.getMidias().get("9361").getNome();
        String nomeFilmeProcurado = p.procurarMidiaPorNome(nomePrimeiroFilme).getNome();

        assertEquals(nomePrimeiroFilme, nomeFilmeProcurado);
    }

    @Test
    public void filtrarFilmesDeAcao(){
        Plataforma p = new Plataforma();
        String generoTeste = "acao";
        Generos gen = Generos.obterGeneroPorNome(generoTeste);
        boolean generoIncorreto = false;

        p.cadastrarFilme("bin/arquivosDeLeitura/POO_Filmes.csv");
        p.getMidias().get("9361").adicionarGenero(gen);

        for(String chaveMedia : p.getMidias().keySet()){
            p.getMidias().get(chaveMedia).adicionarGenero(Generos.AVENTURA);
        }

        Set<Midia> midiasFiltradasGenero = p.aplicadorDeFiltros.filtrarGenero(gen, p.getMidias());
        
        for(Midia midia : midiasFiltradasGenero){
            System.out.println(midia.getGeneros());
            if(midia.getGeneros().contains(generoTeste) == false){
                generoIncorreto = true;
            }
            //assertEquals(generoTeste, midia.getGeneros().contains(Generos.obterGeneroPorNome(generoTeste)));
        }

        assertFalse(generoIncorreto);
    }
}
