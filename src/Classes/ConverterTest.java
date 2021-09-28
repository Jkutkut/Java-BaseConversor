package Classes;

import Classes.Converter;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConverterTest {

	@Test
	void testDecimal2Binary() throws Exception {
		String[][] tests = {
			{"10", "1010"},
			{"12323", "11000000100011"},
			{"128", "10000000"},
			{"35", "100101"},
			{"46", "101110"},
			{"322", "101000010"}
		};
		
		this.executeTest(tests, Converter.DECIMAL, Converter.BINARY);
	}
	
	@Test
	void testBinary2Decimal() throws Exception {
		String[][] tests = {
			{"1010", "10"},
			{"11000000100011", "12323"},
			{"101011110101", "2805"},
			{"11110", "30"},
			{"1010001110", "654"},
			{"0101011110", "550"}
		};
		this.executeTest(tests, Converter.BINARY, Converter.DECIMAL);	
	}
	
	
	//	Decimal-Octal
	
	@Test
	void testDecimalOctal() throws Exception {
		String[][] tests = {
			{"124", "174"},
			{"235", "353"},
			{"23", "27"},
			{"87", "127"}
		};
		
		this.executeTest(tests, Converter.DECIMAL, Converter.OCTAL);
	}
	
	@Test
	void testOctalDecimal() throws Exception {
		String[][] tests = {
			{"345", "229"},
			{"123", "83"},
			{"52", "42"},
			{"67", "55"}
		};
	}
	
	
	//	Decimal-Hexadecimal
	
	@Test
	void testDecimalHexadecimal() throws Exception {
		String[][] tests = {
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""}
		};
	}
	
	@Test
	void testHexadecimalDecimal() throws Exception {
		String[][] tests = {
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""}
		};
	}
	
	
	// Hexadecimal-Octal
	
	@Test
	void testHexadecimalOctal() throws Exception {
		String[][] tests = {
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""}
		};
	}
	
	@Test
	void testOctalHexadecimal() throws Exception {
		String[][] tests = {
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""}
		};
	}
	
	// Binario-Hexadecimal
	
	@Test
	void testBinarioHexadecimal() throws Exception {
		String[][] tests = {
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""}
		};
	}
	
	@Test
	void testHexadecimalBinariio() throws Exception {
		String[][] tests = {
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""}
		};
	}
	
	
	// Binario-Octal
	
	@Test
	void testBinarioOctal() throws Exception {
		String[][] tests = {
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""}
		};
	}
	
	@Test
	void testOctalBinario() throws Exception {
		String[][] tests = {
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""}
		};
	}
	
	//	Help functions
	void executeTest (String[][] tests, int baseFrom, int baseTo) throws Exception {
		int i = 0;
		try {
			for (; i < tests.length; i++) {
				assertEquals(
						Converter.converter(tests[i][0], baseFrom, baseTo, false),
						tests[i][1]
				);
			}
		}
		catch (Exception e){
			fail("Not able to execute the test" + i);
		}
	}

}
