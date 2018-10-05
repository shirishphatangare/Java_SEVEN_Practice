
/* In Java SE 7, the integral types (byte, short, int, and long) can also be expressed using the binary number system. To specify a binary literal, add the prefix 0b or 0B to the number.*/
/* Binary literals can make relationships among data more apparent than they would be in hexadecimal or octal.*/


public class BinaryLiteralsTest {

	public static void main(String[] args) {
		
		// 8-bit byte
		byte instruction = 0b00110011;
		
		// 16-bit short
		short s = 0b0011001111001100;
		
		// 32-bit int
		int i1 = 0b00100001010001011010000101000101;
		int i2 = 0b101;
		int i3 = 0B101; // ob OR oB same
		
		// 64-bit long
		long l = 0b1010000101000101101000010100010110100001010001011010000101000101L;
		
		System.out.println("Byte: " + instruction);
		System.out.println("Short: " + s);
		System.out.println("Integer 1: " + i1);
		System.out.println("Integer 2: " + i2);
		System.out.println("Integer 3: " + i3);
		System.out.println("Long: " + l);
		
		// We can also use binary literals in switch-case
		switch (instruction & 0b11110000) {
	      case 0b00000000: System.out.println("Matched 0b00000000"); break;
	      case 0b00010000: System.out.println("Matched 0b00010000"); break;
	      case 0b00110000: System.out.println("Matched 0b00110000"); break; // 0b00110011 & 0b11110000 --> 0b00110000
	      default: throw new IllegalArgumentException();
	    }
		
		// Prior to Java 1.7 decimals, octals and Hexa-decimals can be expressed but Not binary
		int ia = 10; // Decimal
		int ib = 010; // Octal
		int ic = 0x10; // Hexa-decimal
		
		System.out.println("Decimal: " + ia);
		System.out.println("Octal: " + ib);
		System.out.println("Hexa-Decimal: " + ic);
	}

}
