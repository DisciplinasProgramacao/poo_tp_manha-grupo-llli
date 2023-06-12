
import java.util.HashSet;
import java.util.Set;

import java.util.Map;

public class FiltrosDeMidias implements IFiltragem{
    


    /*
    * Filtra as Midia existentes na plataforma pelo nome
    * retorna um Set de Midias com os nome desejado
    * @param nome a ser procurado no catalogo
    */
    public Set<Midia> filtrarNome (String nome, Map<String, Midia> midiasPlataforma){
        
        Set <Midia> setFiltradoPorNome = new HashSet<Midia>();;
    
        for (Map.Entry<String, Midia> entry : midiasPlataforma.entrySet()){

            Midia midia = entry.getValue();
            String[] nomeMidiaSeparado = midia.getNome().split(" ");

            for (int i = 0; i < nomeMidiaSeparado.length; i++){
                
                String temp = nomeMidiaSeparado[i];
               
                if (nome.equals(temp)){
                    setFiltradoPorNome.add(midia);
                    break;
                }
            }

        }

        return setFiltradoPorNome;
    }
    
    

    /*
    * Filtra as Midias existentes pelo genero
    * retorna um Set de Midias com o genero desejado
    * @param genero a ser procurado no catalogo
    */
    public Set<Midia>filtrarGenero(String filtrarPorGenero,Map<String, Midia> midiasPlataforma){
        
        Set<Midia> midiasGenerosIguais = new HashSet<>();

        for (Map.Entry<String, Midia> entry : midiasPlataforma.entrySet()){


            Midia midia = entry.getValue();

            Set <String> generos = midia.getGeneros();
            
            for (String genero : generos){

                if (generos.contains(filtrarPorGenero)){

                    midiasGenerosIguais.add(midia);
                    break;
                }
            }
            
        }
       
        return midiasGenerosIguais;
    }
    
    

    /*
    * Filtra as Midias Existentes no catalogo pelo idioma
    * retorna um Set de Midias com o idioma desejado
    * @param idioma a ser procurado no catalogo
    */
    public Set<Midia>filtrarIdioma(String filtrarPorIdioma,Map<String, Midia> midiasPlataforma){
        
        Set<Midia> midiasIdiomasIguais = new HashSet<>();

        for (Map.Entry<String, Midia> entry : midiasPlataforma.entrySet()){

            Midia midia = entry.getValue();

            Set <String> idiomas = midia.getIdiomas();
            
            for (String idioma : idiomas){

                if (idiomas.contains(filtrarPorIdioma)){

                    midiasIdiomasIguais.add(midia);
                    break;
                }
            }

        }

       
        return midiasIdiomasIguais;
    }

    


    /*
    * Junta Set de Midias
    * Caso o cliente deseja fazer uma filtragem de mais de um tipo ele realiza as filtragens. 
    * @param Lista de Serie A
    * @param Lista de Serie B
    * @return retorna um Set único com o set "a" adicionado no Set b.
    */
    public Set<Midia> juntarFiltros (HashSet <Midia> a, HashSet <Midia> b){
        
        b.addAll(a);

        return b;
    }
    
    
}
