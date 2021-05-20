package king_jcy.king_jcy;

public class Father  {
	
	int a;
	
	static String name = "123";
	
	static{
		System.out.println("我是父类静态代码块");
	}
	
	{
		System.out.println("我是父类代码块");
		name = "234";
	}
	
	public Father(int c){
		System.out.println("我是父类空的构造方法");
		a = c;
	}
	
	void work() {
		
	}
}
