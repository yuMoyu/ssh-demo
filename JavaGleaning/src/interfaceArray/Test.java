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
		//birds���Ǿ�̬�ģ����Ե�����Ҫʵ����
		System.out.println(test.lengt(test.birds));
		System.out.println(test.lengt(test.baiLingBirds));
	}
	//�ýӿ�������Ϊ�βΣ�ֻҪ�Ǽ̳й�����ӿڵĲ��������Դ��ݽ���
	public int lengt(Pet[] pet){
		return pet.length;
	}

}
