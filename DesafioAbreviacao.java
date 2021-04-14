/*
Desafio
Leonardo é um nômade digital e viaja pelo mundo programando em diferentes cafés das cidades por onde passa. Recentemente, resolveu criar um blog, para compartilhar suas experiências e aprendizados com seus amigos.

Para criação do blog, ele optou por utilizar uma ferramenta pronta, que há um limite de caracteres que se pode escrever por dia, e Leonardo está preocupado que essa limitação, afinal, irá impedir de contar suas melhores experiências. Para contornar esse problema, decidiu usar um sistema de abreviação de palavras em seus posts.

O sistema de abreviações é simples e funciona da seguinte forma: para cada letra, é possível escolher uma palavra que inicia com tal letra e que aparece no post. Uma vez escolhida a palavra, sempre que ela aparecer no post, ela será substituída por sua letra inicial e um ponto, diminuindo assim o número de caracteres impressos na tela.

Por exemplo, na frase: “hoje eu programei em Python”, podemos escolher a palavra “programei” para representar a letra ‘p', e a frase ficará assim: “hoje eu p. em Python”, economizando assim sete caracteres. Uma mesma palavra pode aparecer mais de uma vez no texto, e será abreviada todas as vezes. Note que, se após uma abreviação o número de caracteres não diminuir, ela não deve ser usada, tal como no caso da palavra “eu” acima.

Leonardo precisa que seu post tenha o menor número de caracteres possíveis, e por isso pediu a sua ajuda. Para cada letra, escolha uma palavra, de modo que ao serem aplicadas todas as abreviações, o texto contenha o menor número de caracteres possíveis.

Entrada
Haverá diversos casos de teste. Cada caso de teste é composto de uma linha, contendo uma frase de até 10⁴ caracteres. A frase é composta de palavras e espaços em branco, e cada palavra é composta de letras minúsculas ('a'-'z'), e contém entre 1 e 30 caracteres cada.

O último caso de teste é indicado quando a linha dada conter apenas um “.”, o qual não deverá ser processado.

Saída
Para cada caso de teste, imprima uma linha contendo a frase já com as abreviações escolhidas e aplicadas.

Em seguida, imprima um inteiro N, indicando o número de palavras em que foram escolhidas uma letra para a abreviação no texto. Nas próximas N linhas, imprima o seguinte padrão “C. = P”, onde C é a letra inicial e P é a palavra escolhida para tal letra. As linhas devem ser impressas em ordem crescente da letra inicial.

*/




import java.util.*;
import java.util.stream.Collectors;


public class DesafioAbreviacao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Lista contendo todos as letras do alfabeto.
        List<String> letras =new ArrayList<>(Arrays.asList("abcdefghijklmnopqrstuvwxyz".split("")));

        while(true){

            // Receber um input do user e formatar conforme as orientações
            String inputUser = sc.nextLine().trim().toLowerCase();
            // O último caso de teste é indicado quando a linha dada conter apenas um “.”, o qual não deverá ser processado.
            if(inputUser.equals(".")) break;
            // Se for vazio vai repetir o input
            if(inputUser.isBlank()) continue;

            //Criar uma lista com as palavras da frase que do input
            List<String> spellInputUser = new ArrayList(Arrays.asList(inputUser.split(" ")));

            Map<String, String> map = new HashMap<>();

            //Vou colocar no map a letra como chave e seu valor vazio
            letras.stream().forEach(letra -> {
                map.put(letra, "");
            });

            Map<String, Map<String, Integer>> repeticaoPalavra = new HashMap<String, Map<String, Integer>>();

            //vou colocar no repeticaoPalavra a letra como sua chave e hashmap como seu valor
            letras.stream().forEach(letra -> {
                repeticaoPalavra.put(letra, new HashMap<String, Integer>());
            });

            //Vou pegar a primeira letra da palavra uma letra que foi adicionada como key e colocar o valor sendo
            // a palavra e o valor 0
            spellInputUser.stream().forEach(palavra -> {
                repeticaoPalavra.get(palavra.substring(0,1)).put(palavra,0);
            });

            // pegando palavras repetidas;
            spellInputUser.stream().forEach(palavra -> {
                int qt = repeticaoPalavra.get(palavra.substring(0,1)).get(palavra);
                qt++;
                repeticaoPalavra.get(palavra.substring(0,1)).put(palavra,qt);
            });

            letras.stream().forEach(letra -> {
                // a gnt criar um novo map com o map utilizado como valor do repeticaoPalavra
                Map<String, Integer> map1 = repeticaoPalavra.get(letra);
                // Cria uma lista com as chaves do map
                List<String> chaves = new ArrayList<>(map1.keySet());
                int qtTotalCharPorLetra = 0;

                for(String chave: chaves){
                    // pegando as quantidades de char das palavras usando chave.
                    qtTotalCharPorLetra += map1.get(chave) * chave.length();

                }

                int qtTotalCharMelhorCenario;
                int qtTotalCharMelhorCenarioAux = 10000;

                for (String chave : chaves) {

                    //estou escolhendo a melhor palavra para ser abreviada
                    qtTotalCharMelhorCenario = (qtTotalCharPorLetra - (map1.get(chave) * chave.length())) + (map1.get(chave) * 2);

                    if((qtTotalCharMelhorCenarioAux > qtTotalCharMelhorCenario) && chave.length() > 2) {

                        qtTotalCharMelhorCenarioAux = qtTotalCharMelhorCenario;
                        //estou adicionando a palavra com a letra
                        map.put(letra, chave);

                    }
                }
            });

            String fraseAbreviada = spellInputUser.stream().map(palavra -> {
                String primeiraletra = palavra.substring(0, 1);
                // se a primeira letra da palavra no map for igual a palavra, return primeiraletra + "." se não retorna a palavra
                return map.get(primeiraletra).equals(palavra) ? primeiraletra + "." : palavra;
                }).collect(Collectors.joining(" "));

            System.out.println(fraseAbreviada);
            int qunatidadeAbreviacoes = 0;

            // estou contando quantas abreviações tem na frase
            for(String letra: letras){
                if(!map.get(letra).equals("")){
                    qunatidadeAbreviacoes++;
                }
            }

            System.out.println(qunatidadeAbreviacoes);

            letras.stream().forEach(letra -> {
                if(!map.get(letra).equals("")){
                    System.out.println(letra + ". = " + map.get(letra));
                }
            });
        }
    }
}
