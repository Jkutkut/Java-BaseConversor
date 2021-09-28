import java.util.ArrayList;

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
     * @throws Exception
     */
    public static String converter(String number, int baseFrom, int baseTo) throws Exception {
        System.out.printf("\nConverter:\n- Converting %s from base %d to base %d:\n\n", number, baseFrom, baseTo);
        
        if (baseFrom == baseTo) { // If same base.
            System.out.println("Same base!");
            return number;
        }

        if (baseFrom == Converter.DECIMAL) {
            int decimalNumber = Integer.parseInt(number);
            return Converter.decimal2base(decimalNumber, baseTo, true);
        }

        if (baseTo == Converter.DECIMAL) {
            return Converter.base2decimal(number, baseFrom, true);
        }

        if (baseFrom == Converter.BINARY && Converter.isPowerOfM(baseTo, 2)) {
                return Converter.binary2powerTwoBase(number, baseTo, true);
        }

        if (baseTo == Converter.BINARY && Converter.isPowerOfM(baseFrom, 2)) {
            return Converter.powerTwoBase2binary(number, baseFrom, true);
        }

        System.out.printf("No fast method found. Using conversion base%d -> Decimal, Decimal -> base%d\n\n", baseFrom, baseTo);
        return Converter.decimal2base(
            Integer.parseInt(Converter.base2decimal(
                number,
                baseFrom,
                true)),
            baseTo,
            true
        );
    }

    /**
     * Converter from decimal to any base.
     * @param number - Desired number (Decimal)
     * @param baseTo - Desired base
     * @param log - If we want to see log
     * @return String with the look of number on the desired base.
     * @throws Exception
     */
    private static String decimal2base(int number, int baseTo, boolean log) throws Exception {
        final int STEPS_PER_LINE = 5;
        ArrayList<String[]> steps = new ArrayList<String[]>();
        
        String solution = "";
        int nDigitsDivisor = Converter.lengthNumber(baseTo);

        while (number >= baseTo) {
            int quotient = (int) number / baseTo;
            int remainder = number % baseTo;

            if (log) {
                // Get number of digits of Divisor and remainder
                int nDigitsNumber = Converter.lengthNumber(number);
                int nDigitsQuotient = Converter.lengthNumber(quotient);
                int nDigitsRemainder = Converter.lengthNumber(remainder);
                
                // Make step (3 lines)
                String[] s = {"", "", ""};
                
                // Start line
                s[0] = String.format("%d │%d", number, baseTo);
                for (int i = 0; i < nDigitsQuotient - nDigitsDivisor + 1; i++) {
                    s[0] += " ";
                }

                // Middle line
                for (int i = 0; i < nDigitsNumber; i++) {
                    s[1] += " ";
                }
                s[1] += " └";
                for (int i = 0; i < Math.max(nDigitsDivisor, nDigitsQuotient) + 1; i++) {
                    s[1] += "─";
                }

                // last line
                for (int i = 0; i < nDigitsNumber - nDigitsRemainder; i++) {
                    s[2] += " ";
                }
                s[2] += String.format("%d  %d ", remainder, quotient);
                
                steps.add(s);
            }

            number = quotient;
            solution = Converter.symbolEquivalent(remainder) + solution;
        }
        solution = Converter.symbolEquivalent(number) + solution; // Add the final number

        if (log) {
            String[] divider = {
                "      ",
                "  =>  ",
                "      "
            };

            for (int k = 0; k < steps.size(); k += STEPS_PER_LINE) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < STEPS_PER_LINE && j + k < steps.size(); j++) {
                        System.out.print(steps.get(k + j)[i]);
                        System.out.print(divider[i]);
                    }
                    System.out.println();
                }
                System.out.println("\n");
            }
        }

        return solution;
    }

    /**
     * Converter from any base to decimal.
     * @param number - Desired number (In any base).
     * @param baseFrom  - Given base.
     * @return String with the look of the number in decimal.
     * @throws Exception
     */
    private static String base2decimal(String number, int baseFrom, boolean log) throws Exception {
        int solution = 0;
        String[] calc = new String[number.length()];
        String[] simply = new String[number.length()];

        for (int i = number.length() - 1, j = 0; i >= 0; i--, j++) {
            String current = String.valueOf(number.charAt(i));
            int value = Converter.numberEquivalent(current);
            
            if (log) {
                calc[i] = String.format("%s * %d^(%d)", current, baseFrom, j);
                simply[i] = String.format("%d * %d^(%d)", value, baseFrom, j);
            }
            solution += (int) (value * Math.pow(baseFrom, j));
        }

        if (log) {
            System.out.print(String.join(" + ", calc));
            System.out.print(" = ");
            System.out.print(String.join(" + ", simply));
            System.out.print(" = ");
            System.out.println(solution);
            System.out.println("\n");
        }

        return String.valueOf(solution);
    }

    /**
     * Converter from binary to any base power of 2 (4, 8, 16, 32...)
     * @param number - Number as a string in binary.
     * @param baseTo - Desired base.
     * @param log - If log wanted.
     * @return Number representation in the given base.
     * @throws Exception - If help method failed.
     */
    private static String binary2powerTwoBase(String number, int baseTo, boolean log) throws Exception {
        String solution = "";
        int groupSize = (int) (Math.log(baseTo) / Math.log(2));

        for (int i = number.length(); i >= 1; i-= groupSize) {
            int start = i - 4;
            if (start < 0) {
                start = 0;
            }

            String piece = number.substring(start, i);

            String symbol = Converter.decimal2base(
                Integer.parseInt(Converter.base2decimal(
                        piece,
                        Converter.BINARY,
                        false
                )),
                baseTo,
                false
            );

            if (log) {
                System.out.println(piece + " -> " + symbol);
            }

            solution = symbol + solution;
        }

        return solution;
    }
    
    /**
     * Converter from any base power of 2 to binary.
     * @param number - Desired number.
     * @param baseFrom - Initial base of the number
     * @param log - If we want to see log
     * @return The number representation in binary
     * @throws Exception If any argument failed
     */
    private static String powerTwoBase2binary(String number, int baseFrom, boolean log) throws Exception {
        String solution = "";
        int groupSize = (int) (Math.log(baseFrom) / Math.log(2));
        
        for (int i = 0; i < number.length(); i++) {
            String c = String.valueOf(number.charAt(i));

            String symbols = Converter.decimal2base(
                Integer.parseInt(Converter.base2decimal(
                    c,
                    baseFrom,
                    false
                )),
                2,
                false
            );

            while (symbols.length() < groupSize) {
                symbols = "0" + symbols;
            }

            if (log) {
                System.out.println(c + " -> " + symbols);
            }
            solution += symbols;
        }
        return solution;
    }
    
    
    // Help methods

    /**
     * @param n - Number to use.
     * @return The number of digits of the number (3 -> 1; 123 -> 3; -234324 -> 7)
     */
    private static int lengthNumber(int n) {
        int extra = 0;
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            extra = 1;
            n *= -1;
        }

        return (int) (Math.log10(n) + 1) + extra;
    }

    /**
     * Converter number-symbol (ei: 5 -> 5; 14 -> F)
     * @param number - Value of the desired symbol
     * @return The symbol representing the given value
     * @throws Exception If the argument is not valid
     */
    private static String symbolEquivalent(int number) throws Exception {
        if (number < 0) {
            throw new Exception("The number can not be less than 0");
        }
        if (number < 10) {
            return String.valueOf(number);
        }
        return String.valueOf((char) (65 + number - 10));
    }

    /**
     * Returns the value associated to the given symbol (ei: 5 -> 5; F -> 15)
     * @param number - A single character string with the desired symbol.
     * @return The value associated to the symbol.
     * @throws Exception - If the argument is not valid.
     */
    private static int numberEquivalent(String number) throws Exception {
        if (number.length() > 1) { // If more than one character given
            throw new Exception("The input must be a single number");
        }

        int code = number.codePointAt(0); // ASCII code of the character

        if (code >= 65 && code <= 90) { // If upper case character
            return (code - 65) + 10;
        }
        else if (code >= 48 && code <= 57) {
            return code - 48;
        }
        
        int n = Integer.parseInt(number);

        return n;
    }

    /**
     * Checks if the given number is a power of m
     * @param n - Number to check.
     * @param m - Number to check power of.
     * @return Whenever n is power of m
     * 
     * @see n=8, m=2 => true;
     * @see n=7, m=2 => false;
     */
    static boolean isPowerOfM(int n, int m) {
        return (int)(Math.ceil((Math.log(n) / Math.log(m))))
            == (int)(Math.floor(((Math.log(n) / Math.log(m)))));
    }
    public static void main(String[] args) {

        String numero = "01000100001";

        int from = Converter.BINARY;
        int to = Converter.HEXADECIMAL;

        // String numero = "23AF6";

        // int from = Converter.HEXADECIMAL;
        // int to = Converter.BINARY;

        // String numero = "21";

        // int from = 3;
        // int to = 2;

        try {
            String output = converter(numero, from, to);    
            System.out.printf("The number '%s' in base %d is '%s' in base %d.\n\n", numero, from, output, to);
        }
        catch (Exception e) {
            System.out.printf("Not able to convert '%s' in base %d to base %d.\n", numero, from, to);
            System.out.println(e);
        }
    }
}