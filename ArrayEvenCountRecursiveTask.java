import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/* Fork/Join framework is designed to simplify parallel programming for Java programmers. */
/* RecursiveTask is a ForkJoinTask that returns a result. */
/* ForkJoinPool is the heart of Fork/Join framework. It allows many ForkJoinTasks to be executed by a small number of actual threads, 
 * with each thread running on a separate processing core.*/
/* ForkJoinPool is different than other pools as it uses work stealing algorithm which allows a thread that runs out of things to do, 
 * to steal tasks from other threads that are still busy. */
/* Threads in ForkJoinPool are daemon. You don’t have to explicitly shutdown the pool. */

 


public class ArrayEvenCountRecursiveTask {
	private static final int SIZE = 10_000_000;
	private static int array[] = new int[SIZE];
	
	public static void main(String[] args) {
		randomArray();
		
		int threshold = Integer.parseInt(args[0]); 
		
		ArrayEvenCountTask mainTask = new ArrayEvenCountTask(array,threshold,0,SIZE);
		ForkJoinPool pool = new ForkJoinPool();
		//ForkJoinPool pool = new ForkJoinPool(4); // creates a pool with a custom level of parallelism which must be greater than 0 and not more than the actual number of processors available
		
		/*	<T> T invoke(ForkJoinTask<T> task): executes the specified task and returns its result upon completion. This call is synchronous, meaning that the calling thread waits until this method returns. For a resultless task (RecursiveAction), the type parameter Tis Void.
			void execute(ForkJoinTask<?> task): executes the specified task asynchronously - the calling code doesn’t wait for the task’s completion - it continues to run.*/
		
		int evenArrayCounter = pool.invoke(mainTask); // synchronous - waits for completion
		//pool.execute(mainTask); // asynchronous - do not wait for completion - (sleep will be required to print results properly)

		System.out.println("Number of even integers in an Array: " + evenArrayCounter);
	}
	
	
	// Fill main array with random elements
		private static void randomArray(){
			Random random = new Random();
			for(int i=0;i<SIZE;i++){
				array[i] = random.nextInt(100);
			}
		}
}


class ArrayEvenCountTask extends RecursiveTask<Integer>{
	private static final long serialVersionUID = 1L;
	
	int array[];
	int threshold;
	int start;
	int end;
	
	public ArrayEvenCountTask(int[] array, int threshold, int start, int end) {
		super();
		this.array = array;
		this.threshold = threshold;
		this.start = start;
		this.end = end;
	}

	
	@Override
	protected Integer compute() {
		if(end-start < threshold){
			return computeDirectly();
		}else{
			int mid = (start + end) / 2 ;
			
			ArrayEvenCountTask subTask1 = new ArrayEvenCountTask(array, threshold, start, mid);
			ArrayEvenCountTask subTask2 = new ArrayEvenCountTask(array, threshold, mid, end);
			
			return subTask1.invoke() + subTask2.invoke();
		}
	}
	
	private Integer computeDirectly(){
		int evenCounter = 0;
		
		for(int i=start;i<end;i++){
			if(array[i] % 2 == 0){
				evenCounter++;
			}
		}
		return evenCounter;
	}
	
}
