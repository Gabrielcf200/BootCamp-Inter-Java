/*
Desafio
Pedro e Fernando são os desenvolvedores em uma stratup e vão desenvolver o novo sistema de cadastro, e pediram a sua ajuda. Sua task é fazer o código que valide as senhas que são cadastradas, para isso você deve atentar aos requisitos a seguir:

A senha deve conter, no mínimo, uma letra maiúscula, uma letra minúscula e um número;
A mesma não pode ter nenhum caractere de pontuação, acentuação ou espaço;
Além disso, a senha pode ter de 6 a 32 caracteres.
Entrada
A entrada contém vários casos de teste e termina com final de arquivo. Cada linha tem uma string S, correspondente a senha que é inserida pelo usuário no momento do cadastro.

Saída
A saída contém uma linha, que pode ser “Senha valida.”, caso a senha tenha cada item dos requisitos solicitados anteriormente, ou “Senha invalida.”, se um ou mais requisitos não forem atendidos.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class DesafioValidadorDeSenhaComRequesitos {
    public final static int MAX = 200;

    public static void main(String[] args) throws IOException {
        boolean caracterEspecial;
        boolean caracterDigit;
        String s;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        while ((s = bf.readLine()) != null) {
            caracterDigit = false;
            caracterEspecial = false;

            //Validando se há números ou caracter especial
            for(char c: s.toCharArray()){
                if(Character.isLetter(c)){
                }else if(Character.isDigit(c)){
                    if(!caracterDigit) {
                        caracterDigit = true;
                    }
                }else{
                    caracterEspecial = true;
                    break;
                }
            }

            //Validando o tamanho da String e se tem char maiúsculo e minúsculo
            if (s.length() >= 6 && s.length() <= 32 && !(s.toLowerCase() == s) && !(s.toUpperCase() == s)) {
                //Se não tiver caracter especial e tiver número é válida
                if(!caracterEspecial && caracterDigit){
                    System.out.println("Senha valida.");
                }else{
                    System.out.println("Senha invalida.");
                }
            }else{
                System.out.println("Senha invalida.");
            }
        }
    }
}
