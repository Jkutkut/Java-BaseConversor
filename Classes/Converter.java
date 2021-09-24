public class Converter {

    public static final int BINARY = 2;
    public static final int OCTAL = 8;
    public static final int DECIMAL = 10;
    public static final int HEXADECIMAL = 16;

    /**
     * Number converter.
     * @param number - String with the number in any base.
     * @param baseFrom - Base of the number.
     * @param baseTo - Desired base.
     * @return The equivalent number on the given base.
     */
    static String converter(String number, int baseFrom, int baseTo) {
        if (baseFrom == baseTo) { // If same base.
            System.out.println("Same base!");
            return number;
        }

        if (baseFrom == Converter.DECIMAL) {
            return Converter.decimal2base(number, baseTo);
        }
        return "";
    }

    public static void main(String[] args) {

        String numero = "8";

        int baseFrom = Converter.DECIMAL;
        int baseTo = Converter.DECIMAL;

        System.out.println("\nConverter:");

        String output = converter(numero, baseFrom, baseTo);


        System.out.printf("The number %s in base %d is %s in base %d.\n\n", numero, baseFrom, output, baseTo);
    }
}