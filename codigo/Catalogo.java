
import java.util.ArrayList;
import java.util.List;


/*
* Classe Catalogo: Associação com "Cliente", Agregação com "Serie"
*
*/
public class Catalogo {
    
    public List <Serie> catalogo;

    /*
    * Construtor. Catalogo cria uma lista de series vazia
    */
    Catalogo(){

        catalogo = new ArrayList<Serie>();
    }

    /*
    * Adiciona uma nova serie a lista de serie presente no catalogo
    * @param novaSerie que sera adicionada
    */
    public boolean adicionarSerie(Serie novaSerie){

        catalogo.add(novaSerie);

        if (catalogo.isEmpty())return false;
        
        else return true;
    }

    /*
    *
    * Remove uma serie da lista de serie do catalogo
    * @param nome da serie a ser removida
    */
    public boolean removerSerie (String nome){
        
        for (int i = 0; i < this.catalogo.size(); i++){
            
            Serie temp = catalogo.get(i);
            
            if (nome.toLowerCase().equals(temp.getNome().toLowerCase())){
                
                this.catalogo.remove(i);
                
                return true;
            }
            
        }
        return false;
    }
    

    /*
    * Filtra as series presentes no catalogo pelo nome
    * retorna uma lista de serie com o nome desejado
    * @param nome a ser procurado no catalogo
    */
    public List<Serie> filtrarNome (String nome){

        List <Serie> listaFiltradaPorNome = new ArrayList<Serie>();

        for (int i = 0; i < catalogo.size(); i++){
            
            Serie temp = catalogo.get(i);
            
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
    public List<Serie>filtrarGenero(String genero){

        List <Serie> listaFiltradaPorGenero = new ArrayList<Serie>();

        for (int i = 0; i < catalogo.size(); i++){

            Serie temp = catalogo.get(i);
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
    public List<Serie>filtrarIdioma(String idioma){

        List <Serie> listaFiltradaPorIdioma = new ArrayList<Serie>();

        for (int i = 0; i < catalogo.size(); i++){
            
            Serie temp = catalogo.get(i);
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

        List <Serie> catalogoFiltrado = new ArrayList<Serie>();

        for (int i = 0; i < a.size(); i++){
            
            catalogoFiltrado.add(a.get(i));
        }

        for (int i = 0; i < b.size(); i++){
            
            catalogoFiltrado.add(b.get(i));
        }


        return filtrarDuplicadas(catalogoFiltrado);
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


}
