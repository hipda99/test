package t;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Future ȱ�㣺
 * Future��Ȼ����ʵ�ֻ�ȡ�첽ִ�н�������󣬵�����û���ṩ֪ͨ�Ļ��ƣ������޷���֪Futureʲôʱ����ɡ�
 * Ҫôʹ����������future.get()�ĵط��ȴ�future���صĽ������ʱ�ֱ��ͬ��������
 * Ҫôʹ��isDone()��ѯ���ж�Future�Ƿ���ɣ�������ķ�CPU����Դ��
 */
public class TestFutureTask {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		testRunnable();
		
		testExecutors();
		
//		testCallable();// �� TestCallable
	}
	
	public static void testExecutors() throws InterruptedException, ExecutionException {
		ExecutorService exe = Executors.newCachedThreadPool();
		Future<String> future = exe.submit(() -> {
			Thread.sleep(1000*2);
			return "1";
		});
		
		System.out.println("2");
		
		System.out.println(future.get());
		
		System.out.println("3");
	}
	
	public static void testRunnable() throws InterruptedException, ExecutionException {
		class Result{
			int a = 0;
		}
		
		Result result = new Result();
		
		Runnable run = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				result.a = 1;
			}
		};
		
		FutureTask<Result> task = new FutureTask<Result>(run, result);
		
		Thread t = new Thread(task);
		
		t.start();
		
		System.out.println(2);
		
		System.out.println(task.get().a);
		
		System.out.println(3);
	}
}
