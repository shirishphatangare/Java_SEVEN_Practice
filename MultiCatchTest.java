import java.util.InputMismatchException;
import java.util.Scanner;

/* A catch block that handles multiple exception types creates no duplication in the bytecode generated by the compiler,
 * that is, the byte-code has no replication of exception handlers.
 */
public class MultiCatchTest {

	public static void main(String[] args) {
		System.out.println("Please enter a number");
		try(Scanner scan = new Scanner(System.in)){
			int n = scan.nextInt();
			if(99 % n == 0){
				System.out.println(n + " is factor of 99");
			}else{
				System.out.println(n + " is not a factor of 99");
			}
		}catch (ArithmeticException | InputMismatchException e1) { /* If all the exceptions belong to the same class hierarchy, we should catching the base exception type. However, to catch each exception, it needs to be done separately in their own catch blocks.*/
			System.out.println("Exception Caught - " + e1);
		}
	}
}
