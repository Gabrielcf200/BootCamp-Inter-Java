/*
Desafio
Nesse algoritmo você deverá descobrir se um conjunto de palavras é bom ou ruim. Por definição, um conjunto é bom quando nenhuma palavra desse conjunto é um prefixo de outra palavra. Caso contrário, é considerado um conjunto ruim.

Por exemplo, {dbc, dae, dbcde} é um conjunto ruim, pois dbc é um prefixo de dbcde. Quando duas palavras são idênticas, definimos como uma sendo prefixo da outra.

Entrada
A entrada contém vários casos de teste. A primeira linha de cada caso de teste terá um inteiro N (1 ≤ N ≤ 10⁵), que representa a quantidade de palavras no conjunto. Segue então N linhas, cada uma tendo uma palavra de no máximo 100 letras minúsculas. A entrada termina quando N = 0 e não deve ser processada.

Saída
Para cada caso de teste, você deverá imprimir "Conjunto Bom", ou "Conjunto Ruim", conforme explicado acima.

 */


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class DesafioConjuntoBonsOrRuins {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Recebendo um input da quantidade de palavras que o conjuto terá
        Integer n = sc.nextInt();

        while (n != 0) {
            List<String> x = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                x.add(sc.next().trim().toLowerCase());
            }

            //Ordenação do conjunto por ordem alfabética
            x = x.stream().sorted().collect(Collectors.toList());

            Boolean conjutoRuim = false;

            for(int i = 0; i < n - 1; i++) {
                //Como está por ordem alfabética e palavras semelhantes maiores são colocadas depois,
                //Por isso preciso validar se a palavra é prefixo da próxima palavra.
                if(x.get(i+1).startsWith(x.get(i))){
                    //Se essa palavra for prefixo da próxima, eu marco como conjutoRuim sendo true
                    conjutoRuim = true;
                    break;

            }
        }
            if(conjutoRuim) {
                System.out.println("Conjunto Ruim");
            }else{
                System.out.println("Conjunto Bom");
            }

            n = sc.nextInt();
        }
    }
}
