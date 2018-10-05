
/* Using Strings in switch-case is an Expensive operation. Hence use it cautiously and only when really required 
 * This approach is better than if-else using string comparisons. The Java compiler generates generally more efficient bytecode from switch statements that use String objects than from chained if-then-else statements.*/


public class SwitchWithStrings {

	public static void main(String[] args) {
		String target = "two";
		//String target = null;
		
		switch(target){ // target should not be NULL: otherwise will get NULLPointerException
			case "ONE": {
				System.out.println("Inside ONE case");
				break;
			}
			case "TWO": {
				System.out.println("Inside TWO case");
				break;
			}
			case "two": { // Case-sensitive Strings in case
				System.out.println("Inside two case");
				break;
			}
			case "THREE": {
				System.out.println("Inside THREE case");
				break;
			}
			default:{
				System.out.println("Inside default case");
			}
		}
	}
}
