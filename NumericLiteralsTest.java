
public class NumericLiteralsTest {

	public static void main(String[] args) {
		int inum = 1_00_00;
		System.out.println("Interger: " + inum);
		
		long lnum = 1_00_00_000L;
		System.out.println("Long: " + lnum);
		
		float fnum = 2.10_12F;
		System.out.println("Float: " + fnum);
		
		double dnum = 2.10_12_555;
		System.out.println("Double: " + dnum);

	}

}
