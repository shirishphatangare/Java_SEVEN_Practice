import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


/* RecursiveAction is a ForkJoinTask that doesn’t return a result.*/

public class ArrayTransformRecursiveAction {
	
	private static final int SIZE = 1_00_000;
	private static final int threshold = 1_00;
	private static int array[] = new int[SIZE];
	
	public static void main(String[] args){
		
		// Fill main array with random elements
		randomArray();
		
		// Print first 10 elements of original array
		print();
		
		ArrayTransformTask mainTask = new ArrayTransformTask(array,threshold,0,SIZE);
		ForkJoinPool pool = new ForkJoinPool();
		//ForkJoinPool pool = new ForkJoinPool(4); // creates a pool with a custom level of parallelism which must be greater than 0 and not more than the actual number of processors available
		
		/*	<T> T invoke(ForkJoinTask<T> task): executes the specified task and returns its result upon completion. This call is synchronous, meaning that the calling thread waits until this method returns. For a resultless task (RecursiveAction), the type parameter Tis Void.
			void execute(ForkJoinTask<?> task): executes the specified task asynchronously - the calling code doesn’t wait for the task’s completion - it continues to run.*/
		
		pool.invoke(mainTask); // synchronous - waits for completion
		//pool.execute(mainTask); // asynchronous - do not wait for completion - (sleep will be required to print results properly)

		
		//mainTask.invoke(); // commonpool will be used
		
		// Print first 10 elements of transformed array
		print();
		
		//pool.shutdown(); // ForkJoinPool uses daemon threads that are terminated when all user threads are terminated. That means you don’t have to explicitly shutdown a ForkJoinPool (though it is possible)
		
	}
	
	// Fill main array with random elements
	private static void randomArray(){
		Random random = new Random();
		for(int i=0;i<SIZE;i++){
			array[i] = random.nextInt(100);
		}
	}
	
	// Print first 10 elements of an array
	private static void print(){
		for(int i=0;i<10;i++){
			System.out.print(array[i] + ",");
		}
		System.out.println();
	}
	
}

/* RecursiveAction is a recursive ForkJoinTask that doesn’t return a result. 
 * “Recursive” means that the task can be split into subtasks of itself by divide-and-conquer strategy */

class ArrayTransformTask extends RecursiveAction {
	private static final long serialVersionUID = 1L;

	final int MULTIPLIER = 8;
	
	int array[];
	int threshold;
	int start;
	int end;
	
	public ArrayTransformTask(int[] array, int threshold, int start, int end) {
		super();
		this.array = array;
		this.threshold = threshold;
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		if(end-start <  threshold){
			computeDirectly();
		}else{
			int mid = (start+end)/2;
			
			ArrayTransformTask subTask1 = new ArrayTransformTask(array,threshold,start,mid);
			ArrayTransformTask subTask2 = new ArrayTransformTask(array,threshold,mid,end);
			
			//invokeAll(subTask1, subTask2); //  The ForkJoinTask provides static methods for invoking (executing synchronously) more than one task at a time.
			
			/* Alternatively, you can execute a ForkJoinTask by calling its own methods fork() or invoke(). 
			 * In this case, the common pool will be used automatically, if the task is not already running within a ForkJoinPool. 
			 */
			
			/* The fork() method submits the task to execute asynchronously. This method return this (ForkJoinTask) and the calling thread continues to run.
			   The join() method waits until the task is done and returns the result (void for RecursiveAction).
			   The invoke() method combines fork() and join() in a single call. It starts the task, waits for it to end and return the result. (void for RecursiveAction) */
			
			//subTask1.fork();
			//subTask2.fork(); // it is async (sleep will be required to print results properly if join NOT used)
			
			//subTask1.join();
			//subTask2.join();
			
			subTask1.invoke();
			subTask2.invoke();  // sync calls
			
		}
	}
	
	private void computeDirectly(){
		for(int i=start;i<end;i++){
			int element = array[i];
			array[i] = element * MULTIPLIER;
		}
	}
}


