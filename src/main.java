import Classes.Converter;


class mainCode {

    public static void main(String[] args) {
        String numero = "01000100001";

        int from = Converter.BINARY;
        int to = Converter.HEXADECIMAL;

        try {
            String output = Converter.converter(numero, from, to);    
            System.out.printf("The number '%s' in base %d is '%s' in base %d.\n\n", numero, from, output, to);
        }
        catch (Exception e) {
            System.out.printf("Not able to convert '%s' in base %d to base %d.\n", numero, from, to);
            System.out.println(e);
        }
    }
}