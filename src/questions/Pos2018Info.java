package questions;

import java.util.ArrayList;

public class Pos2018Info {

    public static ArrayList<String[]> getQuestions() {
        ArrayList<String[]> questions = new ArrayList<>();
        questions.add(new String[]{
            "Dois vetores ordenados, contendo, cada um deles, N números inteiros precisam, \r\n" + //
            "ser unidos em outro vetor maior, que conterá os 2N números, que também serão \r\n" + //
            "armazenados de forma ordenada. A complexidade de tempo de melhor caso desse processo será, então,",
            "A) O(1), pois se precisa fazer apenas uma cópia simples de cada um dos elementos originais. ",
            "B) O(log N), pois se usa a busca binária para determinar qual será o próximo elemento copiado para o vetor de destino. ",
            "C) O(N), pois se precisa fazer uma cópia de cada um dos elementos originais, o que implica uma varredura completa de cada vetor de origem. ",
            "D) O(Nlog N), pois se precisa fazer uma busca de cada elemento para depois inseri-lo no vetor de destino. ",
            "E) O(N2 ), pois, como há dois vetores, precisa-se fazer dois laços de forma aninhada dentro do outro), gerando uma multiplicação das quantidades de elementos. ",
            "C"
        });
        // Adicione mais perguntas aqui
        questions.add(new String[]{
            "Dadas as seguintes afirmações a respeito de árvores B, \r\n" + //
            "I. Em uma árvore B de ordem \"m\" cada nó tem, no máximo, \"m\" filhos. \r\n" + //
            "II.  Em uma árvore B de ordem \"m\" cada nó (exceto a raíz e as folhas) tem pelo menos \"m/2\" filhos.\n" +
            "III. Árvores B precisam ser rebalanceadas frequentemente.\n" +
            "IV. Um nó não-folha com \"k\" filhos deve ter k chaves.\n" +
            "V. Todas as folhas aparecem no mesmo nível e carregam informação.\n" + 
            "estão CORRETOS os itens: ",
            "A) I e III, apenas.",
            "B) I, III, IV e V.",
            "C) I, II e V, apenas.",
            "D) III, IV e V, apenas.",
            "E) II, III e IV, apenas.",
            "C"
        });
        questions.add(new String[]{
            "Analise as duas afirmativas a seguir, relacionadas a árvores de busca: \r\n" + //
            "I. Uma árvore AVL é uma árvore binária de busca autobalanceada que respeita algumas propriedades fundamentais. \r\n" + //
            "II.  Como todas as árvores, ela tem uma propriedade chamada altura, que é igual ao valor da altura de sua raiz. \n" +
            "Sabendo que a altura de uma folha é igual a um (01) e que a altura de um nó pai é igual ao\n" + 
            "máximo das alturas de seus filhos mais um, qual estrutura NÃO pode representar uma árvore AVL? \n",
            "A) Uma árvore vazia ",
            "B) Uma árvore com dois nós",
            "C) Uma árvore com três nós e altura igual a dois",
            "D) Uma árvore com três nós e altura igual a três",
            "E) Uma árvore com seis nós e altura igual a três ",
            "B"
        });
        return questions;
    }
}
