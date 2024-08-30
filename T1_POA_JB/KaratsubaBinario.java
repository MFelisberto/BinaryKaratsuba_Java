// Solucao de Marcelo Augusto Felisberto Martins
// 28/08/2024
// T1 de POA

public class KaratsubaBinario {
    public static void main(String[] args) {
        // se nao tiver dois argumentos, nao faz sentido
        if(args.length != 2) {
            System.out.println("Nao foi passado dois numeros binarios como argumento ^-^");	
            return;
        }
        // chamando o metodo de Karatsuba usando os argumentos
        System.out.println(Karatsuba(args[0], args[1]));
    }

    // karatsuba para binarios (SEM INT ;-;)
    public static String Karatsuba(String bin1, String bin2) {
        // igualando os binarios em numero de bits
        if(bin1.length() != bin2.length()){ 
            if(bin1.length() > bin2.length()){             
                bin2 = ZeroAEsquerda(bin2, bin1);
            } else {
                bin1 = ZeroAEsquerda(bin1, bin2);
            }
        }
        // base do algoritmo (quando 1 bit)
        if(bin1.length() == 1 && bin2.length() == 1) {
            return MultiplicacaoBinaria(bin1.charAt(0), bin2.charAt(0));
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

        return RemoverZerosEsquerda(SomaBinaria(SomaBinaria(ShiftLeft(AC, 2 * (bin1.length() - meio)), ShiftLeft(ADmaisBC, bin1.length() - meio)), BD));
    }

    // adicionar zeros a esquerda
    public static String ZeroAEsquerda(String bin, String length) {
        while (bin.length() < length.length()) {
            bin = "0" + bin; // 0 a esquerda -> concatena para esquerda
        }
        return bin; // binario "igualado"
    }

    // shift left (adicionar zeros a direita)
    public static String ShiftLeft(String bin, int n) {
        // adicionar zero ate o shift total ser feito
        while(n > 0) {
            bin = bin + "0"; // 0 a direita -> concatena para direita
            n--;
        }
        return bin;
    }

    // Em alguns casos de numeros impares, na minha logica acabou ficando com zero a esquerda a mais
    // tipo -> 0001010
    // eles nao significam nada... mas pra fica bonito eu tirei :)
    public static String RemoverZerosEsquerda(String bin) {
        int i = 0;
        while(i < bin.length() && bin.charAt(i) == '0') {
            i++;
        }
        return bin.substring(i);
    }   
    

    // somar binarios como se fossem strings (nao int ;-;)
    public static String SomaBinaria(String bin1, String bin2) {
        StringBuilder resultado = new StringBuilder(); // pro resultado
        char carry = '0';    
        
        // igualando os binarios em numero de bits
        if(bin1.length() != bin2.length()){ 
            if(bin1.length() > bin2.length()){             
                bin2 = ZeroAEsquerda(bin2, bin1);
            } else {
                bin1 = ZeroAEsquerda(bin1, bin2);
            }
        }

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

    // subtracao de binarios como se fossem strings (complemento de 2 mais facil)
    public static String SubtracaoBinaria(String bin1, String bin2) {
       
        // igualando os binarios em numero de bits
        if (bin1.length() != bin2.length()) {
            if (bin1.length() > bin2.length()) {
                bin2 = ZeroAEsquerda(bin2, bin1);
            } else {
                bin1 = ZeroAEsquerda(bin1, bin2);
            }
        }
    
        StringBuilder invertido = new StringBuilder();    
        for (int i = 0; i < bin2.length(); i++) {
            if (bin2.charAt(i) == '0') {
                invertido.append('1');
            } else {
                invertido.append('0');
            }
        }
    
        String complemento2 = SomaBinaria(invertido.toString(), "1");
    
        String resultado = SomaBinaria(bin1, complemento2);
    
        // tira o carry da soma se sobrar
        if (resultado.length() > bin1.length()) {
            resultado = resultado.substring(1); // tiro o bit
        }
    
        return resultado;
    }

    // multiplicacao de binaria stringada
    public static String MultiplicacaoBinaria(char bin1, char bin2) {
        if(bin1 == '1' && bin2 == '1'){
            return "1";
        }else{
            return "0";
        }

    }


}