# CLasse avaliação

    map <Int, avaliação> midiasAvaliadas
    Date  data da avaliação


    - Metodo calcular avaliação media e retornar 
    - Não pode avaliar a mesma mídia 2 vezes ✔️
        Criar lista de midia avaliadas e quando tentar avaliar checar se ela esta na lista ✔️


# Classe Midia:

    - TIrar esse Atributo da classe midia avaliação (int de 1 a 5); ✔️


# Classe Cliente

    - Metodo Avaliar midia 1 a 5 ✔️

    - tem um Map de avaliações
# muda UML toda 
        Ideia Caram: 

            Fazer uma pilha do mes passado 

            Avaliação vai ser uma Classe

    - Metodo DefinirTipoDoCliente

# Cliente Regular

    - Apenas dão notas para mídias



# Cliente Especialista

    - Para ser especialista devem ter feito no minimo 5 avaliações no mes anterior
    - Dão notas para mídias e fazem comentários


# Novos pensamentos

## Avaliação

    dataDaAvaliação: Date
   
    CalcularMediaAvaliaçẽos (BigDecimal mediaMidiaAvaliações, int notaAvaliação): BigDecimal mediaAvaliações


## Cliente

    avaliaçõesCliente: Stack <Avaliações> 
    midiasJaAvaliadas: Map <Integer, Midia>

    Avaliar midia (Midia midiaParaSerAvaliada ,int notaAvaliacao){

        midiaParaSerAvaliada.receberAvaliação (notaAvaliação)
    }
        - Apenas midias que ele ja assistiu


## Midia

    mediaAvaliações: BigDecimal
    avaliacoesRecebidas: List <Avaliações>
    protected int quantidadeAvalicoes;

    Receber avaliação (int notaAvaliacao){

        avaliacoesRecebidas.add(new Avaliação ())

        avaliacoesRecebidas.LastObject.calcularMediaAvalicao (this.mediaMidiaAvaliações, int notaAvaliação)
    }


