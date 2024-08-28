// Solucao de Marcelo Augusto Felisberto Martins
// 28/08/2024
// T1 de POA

public class KaratsubaBinario {
    public static void main(String[] args) {

        // tem que passar dois numeros como argumento
        if(args.length != 2) {
            System.out.println("Nao foi passado dois numeros binarios como argumento");
            return;
        }

        // chamando o metodo de Karatsuba usando os argumentos
        String resultado = Karatsuba(args[0], args[1]);
        System.out.println("Resultado: " + resultado);
    }

  
    // karatsuba para binarios (SEM INT ;-;)
    public static String Karatsuba(String bin1, String bin2) {

        // ver se as string tem o mesmo tamanho
        if(bin1.length() != bin2.length()){ 
            // ver qual é o maior
            if(bin1.length() > bin2.length()){             
                bin2 = ZeroAEsquerda(bin2, bin1);   // adicionar zeros a esquerda em bin2
            } else {
                bin1 = ZeroAEsquerda(bin1, bin2);   // adicionar zeros a esquerda em bin1
            }
        }

        // base do algoritmo (quando 1 bit)
        if (bin1.length() == 1) {
            return String.valueOf((bin1.charAt(0) - '0') * (bin2.charAt(0) - '0'));
        }

        // meio da nossa string
        int meio = bin1.length() / 2; 
        
        String A = bin1.substring(0, meio); //bin1
        String B = bin1.substring(meio);
        String C = bin2.substring(0, meio); //bin2
        String D = bin2.substring(meio);


        
        return "";
    }

    // adicionar zeros a esquerda
    public static String ZeroAEsquerda(String bin, String length) {
        
        // adicionar zero ate o tamanho ser igual
        while (bin.length() < length.length()) {
            // concatenar 0 a esquerda
            bin = "0" + bin;
        }
        
        // retorna o binario agora com o mesmo tamanho
        return bin;
    }

}