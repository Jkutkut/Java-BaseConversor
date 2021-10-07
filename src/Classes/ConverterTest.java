package Classes;

//import Classes.Converter;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ConverterTest {
	

	@Test
	void testDecimal2Binary() throws Exception {
		String[][] tests = {
			{"10", "1010"},
			{"12323", "11000000100011"},
			{"128", "10000000"},
			{"35", "100011"},
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
			{"101011110", "350"}
		};
		this.executeTest(tests, Converter.BINARY, Converter.DECIMAL);	
	}
	
	
	//	Decimal-Octal
	@Test
	void testDecimal2Octal() throws Exception {
		String[][] tests = {
			{"124", "174"},
			{"235", "353"},
			{"23", "27"},
			{"87", "127"}
		};
		
		this.executeTest(tests, Converter.DECIMAL, Converter.OCTAL);
	}
	
	
	@Test
	void testOctal2Decimal() throws Exception {
		String[][] tests = {
			{"345", "229"},
			{"123", "83"},
			{"52", "42"},
			{"67", "55"}
		};
		this.executeTest(tests, Converter.OCTAL, Converter.DECIMAL);
	}
	
	
	//	Decimal-Hexadecimal
	
	@Test
	void testDecimal2Hexadecimal() throws Exception {
		String[][] tests = {
				{"231", "E7"},
				{"89", "59"},
				{"3456", "D80"},
				{"12", "C"}
		};
		
		this.executeTest(tests, Converter.DECIMAL, Converter.HEXADECIMAL);
	}
	
	@Test
	void testHexadecimal2Decimal() throws Exception {
		String[][] tests = {
		};
		
		this.executeTest(tests, Converter.HEXADECIMAL, Converter.DECIMAL);
	}
	
	
	// Hexadecimal-Octal
	
	@Test
	void testHexadecimal2Octal() throws Exception {
		String[][] tests = {
				{"15D5", "12725"},
				{"3569", "32551"},
				{"52BB", "51273"},
				{"5769", "53551"}
		};
		
		this.executeTest(tests, Converter.HEXADECIMAL, Converter.OCTAL);
	}
	
	@Test
	void testOctal2Hexadecimal() throws Exception {
		String[][] tests = {
		};
		
		this.executeTest(tests, Converter.OCTAL, Converter.HEXADECIMAL);
	}
	
	// Binario-Hexadecimal
	
	@Test
	void testBinario2Hexadecimal() throws Exception {
		String[][] tests = {
		};
		
		this.executeTest(tests, Converter.BINARY, Converter.HEXADECIMAL);
	}
	
	@Test
	void testHexadecimal2Binario() throws Exception {
		String[][] tests = {
				{"234", "1000110100"},
				{"A234F", "10100010001101001111"},
				{"3572", "11010101110010"},
				{"3567", "10010101100111"}
		};
		
		this.executeTest(tests, Converter.HEXADECIMAL, Converter.BINARY);
	}
	
	
	// Binario-Octal
	
	@Test
	void testBinario2Octal() throws Exception {
		String[][] tests = {
				{"1010111010101", "12725"},
				{"11010101101001", "32551"},
				{"101001010111011", "51273"},
				{"101011101101001", "51273"},
				{"0101011101101001", "53551"}
		};
		
		this.executeTest(tests, Converter.BINARY, Converter.OCTAL);
	}
	
	@Test
	void testOctal2Binario() throws Exception {
		String[][] tests = {
				{"234", "10011100"},
				{"3572", "11101111010"},
				{"3567", "11101110111"}
		};
		
		this.executeTest(tests, Converter.OCTAL, Converter.BINARY);
	}
	
	//	Help functions
	void executeTest (String[][] tests, int baseFrom, int baseTo) throws Exception {
		int i = 0;
		try {
			for (; i < tests.length; i++) {
				assertEquals(
						Classes.Converter.converter(tests[i][0], baseFrom, baseTo, false),
						tests[i][1]
				);
			}
			for (; i < tests.length; i++) {
				assertEquals(
						Classes.Converter.converter(tests[i][1], baseTo, baseFrom, false),
						tests[i][0]
				);
			}
		}
		catch (Exception e){
			fail("Not able to execute the test" + i);
		}
	}

}
