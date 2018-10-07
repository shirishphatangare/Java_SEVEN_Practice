import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* You can replace the type arguments required to invoke the constructor of a generic 
 * class with an empty set of type parameters (<>) as long as the compiler can infer the type arguments from the context.
 * Diamond should be primarily used for variable declarations.
 */ 

public class TypeInferenceTest {

	public static void main(String[] args) {
		// prior to Java SE 7
		// Map<String, List<String>> myMap = new HashMap<String, List<String>>();

		// In Java SE 7 and after, you can substitute the parameterized type of the constructor with an empty set of type parameters (<>):
		List<String> names1 = new ArrayList<>();
		names1.add("Dada");
		names1.add("Papa");
		names1.add("Nana");
		
		List<String> names2 = new ArrayList<>();
		names2.add("Chacha");
		names2.add("Mama");
		names2.add("Jada");
		
		Map<String, List<String>> myMap = new HashMap<>(); // <> This pair of angle brackets is informally called the diamond.
		
		myMap.put("1", names1);
		myMap.put("2", names2);
		
		for(Entry<String, List<String>> e: myMap.entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue());
		}
	}

}
