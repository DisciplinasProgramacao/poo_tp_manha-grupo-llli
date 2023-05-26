import java.time.LocalDate;
import java.util.Stack;
import java.util.Date;
import java.util.Calendar;

public class ClienteEspecialista implements ITipoCliente{

    

    public int contabilizarQuantidadeDeComentariosMensais(Stack<Avaliacoes> avaliacoesCliente){

        Calendar calendario =  Calendar.getInstance();
        Date atual = calendario.getTime();

        System.out.println(atual.toString());
        
        return 1;
    }

    public void fazerComentario(String comentario){



    }

}