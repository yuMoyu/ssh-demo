package interfaceArray;

public class Test {
	Bird bird1 = new Bird();
	Bird bird2 = new Bird();
	Bird[] birds = {bird1,bird2};
	BaiLingBird baiLingBird1 = new BaiLingBird();
	BaiLingBird baiLingBird2 = new BaiLingBird();
	BaiLingBird baiLingBird3 = new BaiLingBird();
	BaiLingBird[] baiLingBirds = {baiLingBird1,baiLingBird2,baiLingBird3};
	public static void main(String[] args) {
		Test test = new Test();
		//birds不是静态的，所以调用需要实例化
		System.out.println(test.lengt(test.birds));
		System.out.println(test.lengt(test.baiLingBirds));
	}
	//用接口数组作为形参，只要是继承过这个接口的参数都可以传递进来
	public int lengt(Pet[] pet){
		return pet.length;
	}

}
