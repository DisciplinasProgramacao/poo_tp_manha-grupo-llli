//Enumerador com a lista de gêneros possiveis nas mídias
public enum Generos {

    
    ACAO ("acao"),
    ANIME ("anime"),
    AVENTURA ("aventura"),
    COMEDIA ("comedia"),
    DOCUMENTARIO ("documentario"),
    DRAMA ("drama"),
    POLICIAL ("policial"), 
    ROMANCE ("romance"), 
    SUSPENSE ("suspense");

    //Variavel String caso sejá necessario fazer alguma operação em relação ao gênero
    String Nome;
    
    //Construtor do gênero que recebe uma String como nome
    Generos(String Nome){
        this.Nome=Nome;
    }

    /*Método que recebe uma String e retorna um Gênero, principalmente utilizado em filtros
    *@param nome o nome do gênero que o que será utilizado na filtragem
    *@return Generos retorna um enumeravel Gênero para a filtragem de mídias pelo gênero
    */
    public static Generos obterGeneroPorNome(String nome) {
        for (Generos genero : Generos.values()) {
            if (genero.Nome.equals(nome)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Gênero não encontrado: " + nome);
    }
}

