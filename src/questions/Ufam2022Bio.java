package questions;

import java.util.ArrayList;

public class Ufam2022Bio {

    public static ArrayList<String[]> getQuestions() {
        ArrayList<String[]> questions = new ArrayList<>();
        questions.add(new String[]{
            "(UFAM 2022) Uma mulher normal homozigota casou-se com um homem daltônico." +
            "\nSabendo-se que o daltonismo é uma doença hereditária recessiva ligada ao cromossomo X," +
            "\nqual a probabilidade do casal ter um filho daltônico do sexo masculino?",
            "A) 0%",
            "B) 25%",
            "C) 50%",
            "D) 75%",
            "E) 100%",
            "A"
        });
        // Adicione mais perguntas aqui
        questions.add(new String[]{
            "(UFAM 2022)A doença hemolítica do recém-nascido ou eritroblastose fetal é o resultado da" +  
            "\nincompatibilidade materno-fetal ao antígeno Rh. Apesar de ser aplicado de forma semelhante" +
            "\na uma vacina, o anti-Rh é, na verdade, um anticorpo pronto, obtido a partir de sangue humano," + 
            "\nque neutraliza o fator Rh.\n" +
            "\nConsiderando que uma mulher foi orientada a usar a vacina anti-Rh logo após o nascimento do" + 
            "\nprimeiro filho, podemos dizer que o fator Rh do filho, da mãe e do pai da criança são, respectivamente:",
            "A) negativo, negativo, positivo.",
            "B) positivo, positivo, negativo.",
            "C) negativo, positivo, positivo.",
            "D) positivo, negativo, positivo.",
            "E) negativo, negativo, negativo",
            "D"
        });

        questions.add(new String[]{
            "Na espécie humana, os oocistos iniciam sua primeira divisão meiótica antes do nascimento, contudo permanecem" +
            "\nna meiose I até a adolescência." + 
            "\n\nSobre isso, assinale a alternativa que indica CORRETAMENTE a fase e o estágio da meiose I, onde os oocistos " +
            "\npausam a divisão celular durante a oogênese:",
            "A) na anáfase I, no diplóteno.",
            "B) na metáfase I, no paquíteno.",
            "C) na prófase I, no dictióteno.",
            "D) na telófase I, no leptóteno.",
            "E) na anáfase I, na diacinese.",
            "C"
        });
        return questions;
    }
}
