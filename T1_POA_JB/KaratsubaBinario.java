// Solucao de Marcelo Augusto Felisberto Martins
// 28/08/2024
// T1 de POA

public class KaratsubaBinario {
    public static void main(String[] args) {

        // 1100 x 1010 = 1111000
        args[0] = "1100";
        args[1] = "1010";
       
        // tem que passar dois numeros como argumento
        if(args.length != 2) {
            System.out.println("Nao foi passado dois numeros binarios como argumento ^-^");	
            return;
        }

        // chamando o metodo de Karatsuba usando os argumentos
        // nao preicsaria dessa variavel resultado, mas fica mais facil de entender (para mim)
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
        
        // divisao das strings no meio (A, B, C, D)
        String A = bin1.substring(0, meio); //bin1
        String B = bin1.substring(meio);
        String C = bin2.substring(0, meio); //bin2
        String D = bin2.substring(meio);

        // a logica de verdade é aqui
        String AC = Karatsuba(A, C);
        String BD = Karatsuba(B, D);
        String ABmaisCD = Karatsuba(SomaBinaria(A, B), SomaBinaria(C, D));
        String ADmaisBC = SubtracaoBinaria(SubtracaoBinaria(ABmaisCD, AC), BD);


        return SomaBinaria(SomaBinaria(ShiftLeft(AC, 2 * (bin1.length() - meio)), ShiftLeft(ADmaisBC, bin1.length() - meio)), BD);
    }

    // adicionar zeros a esquerda
    public static String ZeroAEsquerda(String bin, String length) {
        // adicionar zero ate o tamanho ser igual
        while (bin.length() < length.length()) {
            bin = "0" + bin; // 0 a esquerda
        }

        return bin; // binario "igualado"
    }

    // shift left (adicionar zeros a direita)
    public static String ShiftLeft(String bin, int n) {
        // adicionar zero ate o shift total ser feito
        while(n > 0) {
            bin = bin + "0"; // 0 a direita
            n--;
        }

        return bin;
    }

    // somar binarios como se fossem strings (nao int ;-;)
    public static String SomaBinaria(String bin1, String bin2) {

        StringBuilder resultado = new StringBuilder(); // pro resultado
        char carry = '0';                              // carry da soma (inicialmente zero)

        // de tras para frente (pq binario o "primeiro" é o mais significativo)
        for(int i = bin1.length() - 1; i >= 0; i--) {

            char bit1 = bin1.charAt(i); // bit do bin1 
            char bit2 = bin2.charAt(i); // bit do bin2

            if(bit1 == '1' && bit2 == '1'){        // 1 + 1 fica zero e vai 1
                resultado.insert(0, carry);
                carry = '1';
            }else if (bit1 == '0' && bit2 == '0'){ // 0 + 0 fica zero
                resultado.insert(0, carry);
                carry = '0';
            }else{                                 // se forem diferentes 
                if(carry == '1'){                  // 1 + 1(carry) fica zero e vai 1
                    resultado.insert(0, '0');
                    carry = '1';
                }else{                             // 1 + 0(carry) fica 1 
                    resultado.insert(0, '1');       
                    carry = '0';
                }
            }
        }

        // se sobrar um carry
        if(carry == '1'){
            resultado.insert(0, '1');
        }

        return resultado.toString(); 
    }

    // subtracao de binarios como se fossem strings
    public static String SubtracaoBinaria(String bin1, String bin2){

        StringBuilder resultado = new StringBuilder(); // pro resultado
        char carry = '0';                              // carry da subtracao (inicialmente zero)

        // de tras para frente (pq binario o "primeiro" é o mais significativo)
        for(int i = bin1.length() - 1; i >= 0; i--) {

            char bit1 = bin1.charAt(i); // bit do bin1 
            char bit2 = bin2.charAt(i); // bit do bin2

            if(bit1 == '0' && bit2 == '0'){        // 0 - 0 (sem carry) fica zero
                if(carry == '1'){
                    resultado.insert(0, '1');
                    carry = '1';
                }else{
                    resultado.insert(0, '0');
                    carry = '0';
                }
            }else if (bit1 == '1' && bit2 == '0'){ // 1 - 0 (sem carry) fica 1
                if(carry == '1'){
                    resultado.insert(0, '0');
                    carry = '1';
                }else{
                    resultado.insert(0, '1');
                }
            }else if (bit1 == '1' && bit2 == '1'){
                if(carry == '1'){
                    resultado.insert(0, '1');
                    carry = '1';
                }else{
                    resultado.insert(0, '0');
                    carry = '0';
                }
            }else if (bit1 == '0' && bit2 == '1'){ 
                if(carry == '1'){
                    resultado.insert(0, '0');
                    carry = '1';
                }else{
                    resultado.insert(0, '1');
                    carry = '1';
                }
            }
          
        }

       return resultado.toString();
    }
}