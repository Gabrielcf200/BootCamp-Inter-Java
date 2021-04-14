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
