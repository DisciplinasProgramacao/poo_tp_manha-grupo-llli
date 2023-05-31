//Classe ClienteComentarista herda da interface Icomentar
public class ClienteComentarista implements Icomentar{

     /**
     * Faz um comentário em uma determinada mídia, associando-o a um cliente.
     *
     * @param comentario    o texto do comentário a ser feito
     * @param midia         a mídia em que o comentário será feito
     * @param clienteLogin  o login do cliente que está fazendo o comentário
     */
    public void fazerComentario(String comentario, Midia midia,String clienteLogin){

        midia.receberComentario(comentario, clienteLogin);
    }

}