package king_jcy.king_jcy;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Observable;
import java.util.UUID;

public class Test extends Observable {
	
	public static void main(String[] args) {



	}
	

	public static void test01(){
		Test test = new Test();
		//添加观察者
		test.addObserver((o, arg)->{
			System.out.println("数据通知变化---1");
		});
		test.addObserver((o, arg)->{
			System.out.println("数据通知变化---2");
		});
		test.addObserver((o, arg)->{
			System.out.println("数据通知变化---3");
		});

		test.setChanged();
		test.notifyObservers();
		System.out.println("代码执行完成----4");
	}


	public static void test02(){
		Flux.just("张三", "李四", "王五", "赵六").subscribe((value)->{System.out.println(value);});
		System.out.println("====================================================");
		// 2.2.Mono 实现发布者，返回 0 或者 1 个元素 （实际开发中我们可以放入根据id查询的单条数据）
		//Mono.just("张无忌").subscribe(System.out::println);
		Mono.just("熏悟空").subscribe((value)->{System.out.println(value);});
	}


	public static void test03(){
		Integer[] arr = {1,2,3,4,5,6};
		Flux.fromArray(arr).subscribe(System.out::println);


	}
}
