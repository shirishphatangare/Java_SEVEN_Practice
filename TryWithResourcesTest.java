import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class TryWithResourcesTest {
	public static void main(String[] args) {
		String filePath = null;
		
		try(Scanner scan = new Scanner(System.in)){   /* Individual try without catch or finally is allowed in new syntax - ONLY in case of unchecked exceptions*/
			System.out.println("Please Enter File Path: ");
			filePath = scan.nextLine();
		}
		
		// Resources inside try brackets must implement AutoCloseable interface
		try(BufferedReader br = new BufferedReader(new FileReader(filePath)); Demo1 demo1 = new Demo1(); Demo2 demo2 = new Demo2())
		{
			String line = null;
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
		} // close method of resource is invoked by JVM as soon as try block finishes
		// Note here - New syntax "Caching Multiple Exceptions by single catch" do not allow subclass of an exception if superclass of that exception is there in list. For instance, FileNotFoundException and IOException is not allowed in same list in new syntax. However, it is allowed in old syntax in two separate catch blocks 
		/* catch (FileNotFoundException e1 | IOException e2) -- Not allowed in new syntax. Need to write 2 separate catch blocks if need to catch them separately */ 
		catch (FileNotFoundException e1) { 			
			e1.printStackTrace();
		}
		catch (IOException e2) { 			
			e2.printStackTrace();
		}
		catch (Exception e3) { 
			e3.printStackTrace();
		}
		// No need of finally block to close resources, resource cleanup is done automatically.
	}
}

class Demo1 implements AutoCloseable { // Closed in reverse order of initialization in try block
	@Override
	public void close() throws Exception {
		System.out.println("Closing instance of Demo1");
	}
	
}


class Demo2 implements AutoCloseable {
	@Override
	public void close() throws Exception {
		System.out.println("Closing instance of Demo2");
	}
	
}
