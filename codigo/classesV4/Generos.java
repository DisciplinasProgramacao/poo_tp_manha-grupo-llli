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

    String Nome;
    
    Generos(String Nome){
        this.Nome=Nome;
    }

    public static Generos obterGeneroPorNome(String nome) {
        for (Generos genero : Generos.values()) {
            if (genero.Nome.equals(nome)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Gênero não encontrado: " + nome);
    }
}

