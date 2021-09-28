package Classes;

import Classes.Converter;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConverterTest {

	@Test
	void testBinary() throws Exception {
		String[][] tests = {
				{"10", "1010"},
				{"12323", "11000000100011"}
		};
		
		for (int i = 0; i < tests.length; i++) {
			System
			assertEquals(
					Converter.converter(tests[i][0], Converter.DECIMAL, Converter.BINARY),
					tests[i][1]
			);
		}
	}

}
