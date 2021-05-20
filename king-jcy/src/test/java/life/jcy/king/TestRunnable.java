package life.jcy.king;

public class TestRunnable implements Runnable {
	
	private Accumulation accumulation;
	
	public TestRunnable(Accumulation accumulation) {
		this.accumulation = accumulation;
	}
	
	@Override
	public void run() {
		accumulation.add();
	}

}
