public class SomaTeste {

    public static void main(String[] args) {
        
        String bin1 = "101010100011";
        String bin2 = "101010000111";

        String resultado = SomaBinaria(bin1, bin2);

        System.out.println(bin1 + " + " + bin2);
        System.out.println("Resultado: " + resultado);
    }

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
    
}
