import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


public class RecursiveTaskTest {
	private static final int SIZE = 5_00_000_00;
	private static int array[] = new int[SIZE];
	
	public static void main(String[] args) {
		array[0] = 0;
		randomArray();
		
		int leap = 55555;
		
		Task task = new Task(array,leap,0);
		ForkJoinPool pool = new ForkJoinPool(4);
		boolean canWin = pool.invoke(task);
		System.out.println((canWin) ? "Yes" : "No") ; 
	}
	
	private static void randomArray(){
		Random random = new Random();
		for(int i=1;i<SIZE;i++){
			array[i] = random.nextInt(2);
		}
	}
	
}

class Task extends RecursiveTask<Boolean>{
	private static final long serialVersionUID = 1L;
	
	int leap;
	int array[];
	int currentPointer;
	
	public Task(int array[],int leap,int currentPointer) {
		this.array = array;
		this.leap = leap;
		this.currentPointer = currentPointer;
	}

	@Override
	protected Boolean compute() {
		if(currentPointer < 0 || array[currentPointer] == 1){
			return false;
		}
		if(currentPointer + 1 >= array.length || currentPointer + leap >= array.length){
			return true;
		}
		
		array[currentPointer] = 1;
		
		Task task1 = new Task(array,leap,currentPointer + leap);
		Task task2 = new Task(array,leap,currentPointer + 1);
		Task task3 = new Task(array,leap,currentPointer - 1);
		
		return task1.invoke() || task2.invoke() || task3.invoke(); 
	}
}

