class Converter {

    static BINARY = 2;
    static OCTAL = 8;
    static DECIMAL = 10;
    static HEXADECIMAL = 16;

    /**
     * Number converter.
     * @param number - with the number in any base.
     * @param baseFrom - Base of the number.
     * @param baseTo - Desired base.
     * @return The equivalent number on the given base.
     * @throws Exception
     */
    static converter(number, baseFrom, baseTo) {
        Converter.log(`\nConverter:\n- Converting ${number} from base ${baseFrom} to base ${baseTo}:\n\n`);
        
        if (baseFrom === baseTo) { // If same base.
            Converter.log("Same base!");
            return number;
        }

        if (baseFrom === Converter.DECIMAL) {
            let decimalNumber = parseInt(number);
            return Converter.decimal2base(decimalNumber, baseTo);
        }
        return "";
    }

    /**
     * Converter from decimal to any base
     * @param number - Desired number (Decimal)
     * @param baseTo - Desired base
     * @return String with the look of number on the desired base.
     * @throws Exception
     */
    static decimal2base(number, baseTo) {
        const STEPS_PER_LINE = 5;
        let steps = [];
        
        let solution = "";
        const nDigitsDivisor = Converter.lengthNumber(baseTo);

        while (number >= baseTo) {
            let quotient = parseInt(number / baseTo);
            let remainder = number % baseTo;

            // Get number of digits of Divisor and remainder
            let nDigitsNumber = Converter.lengthNumber(number);
            let nDigitsQuotient = Converter.lengthNumber(quotient);
            let nDigitsRemainder = Converter.lengthNumber(remainder);
            
            // Make step (3 lines)
            let s = ["", "", ""];
            
            // Start line
            s[0] = `${number} │${baseTo}`;
            for (let i = 0; i < nDigitsQuotient - nDigitsDivisor + 1; i++) {
                s[0] += " ";
            }

            // Middle line
            for (let i = 0; i < nDigitsNumber; i++) {
                s[1] += " ";
            }
            s[1] += " └";
            
            for (let i = 0; i < Math.max(nDigitsDivisor, nDigitsQuotient) + 1; i++) {
                s[1] += "─";
            }

            // last line
            for (let i = 0; i < nDigitsNumber - nDigitsRemainder; i++) {
                s[2] += " ";
            }
            s[2] += `${remainder}  ${quotient} `;
            
            steps.push(s);

            number = quotient;
            solution = Converter.symbolEquivalent(remainder) + solution;
        }
        solution = Converter.symbolEquivalent(number) + solution; // Add the final number


        let divider = [
            "      ",
            "  =>  ",
            "      "
        ];

        for (let k = 0; k < steps.length; k += STEPS_PER_LINE) {
            for (let i = 0; i < 3; i++) {
                let str = "";
                for (let j = 0; j < STEPS_PER_LINE && j + k < steps.length; j++) {
                    str += steps[k + j][i];
                    str += divider[i];
                }
                Converter.log(str);
            }
            Converter.log("\n");
        }
        

        // Add the final Message
        Converter.log(" Done\n\n");
        

        return solution;
    }

    /**
     * @param n - Number to use.
     * @return The number of digits of the number (3 -> 1; 123 -> 3; -234324 -> 7)
     */
    static lengthNumber(n) {
        let extra = 0;
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            extra = 1;
            n *= -1;
        }

        return parseInt((Math.log10(n) + 1)) + extra;
    }

    static symbolEquivalent(number) {
        if (number < 0) {
            throw new Exception("The number can not be less than 0");
        }
        if (number < 10) {
            return "" + number;
        }
        return String.fromCharCode(65 + number - 10);
    }

    static main() {

        let numero = "10";

        let from = Converter.DECIMAL;
        let to = Converter.BINARY;

        try {
            let output = Converter.converter(numero, from, to);    
            Converter.log(`The number '${numero}' in base ${from} is '${output}' in base ${to}.\n\n`);
        }
        catch (e) {
            console.error("Not able to convert '%s' in base %d to base %d.\n", numero, from, to);
            console.error(e);
        }

        return Converter.logBuffer;
    }

    static logBuffer = [];
    static log(s) {
        // console.log(s);
        // s = s.replaceAll("└", "&#x2514;");
        s = s.replaceAll("└", "+");
        // s = s.replaceAll("─", "&#x2500;");
        s = s.replaceAll("─", "-");
        // s = s.replaceAll("│", "&#x2502;");
        s = s.replaceAll("│", "|");
        // s = s.replaceAll(" ", "&nbsp;");

        // s = s.replaceAll("\n", "<br>");

        Converter.logBuffer.push(s);
    }
}




window.onload = () => {    
    

    let output = Converter.main();

    // document.getElementById("p").innerHTML = output.join("<br>");
    console.log(output.join("\n"));
};