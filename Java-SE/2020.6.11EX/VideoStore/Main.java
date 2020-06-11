import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean isSevered = false;//是否被服务过，默认为没有


		VideoStore myStore = new VideoStore();
		myStore.addVideo("黑客帝国", "科幻 惊悚");
		myStore.addVideo("教父", "黑帮");
		myStore.addVideo("星球大战", "科幻 惊悚");

		System.out.println("欢迎来到小店，以下是本店的电影:");
		myStore.printVideoList();
		System.out.println("请问今天来想来干什么呢?");


		int choice = sc.nextInt();
		//1.借电影 2.还电影 3.什么都不干就看看
		do {
			if (isSevered) {
				System.out.println("欢迎来到小店，以下是本店的电影:");
				myStore.printVideoList();
				System.out.println("请问今天来想来干什么呢?");
				choice = sc.nextInt();
				isSevered = false;
			}


			switch (choice) {
			case 1:
				System.out.println("请问您想借什么电影呢？");
				String movieName = sc.next();
				myStore.checkOut(movieName);
				isSevered = true;
				break;
			case 2:
				System.out.println("请输入您想还的电影并打分:");
				String returnMovieName = sc.next();
				int score = sc.nextInt();
				myStore.returnAndScore(returnMovieName, score);
				isSevered = true;
				break;
			case 3:
				System.out.println("感谢光临，欢迎下次再来~");
				isSevered = true;
				break;
			}
		} while (choice != 3);



		// System.out.println();

		// myStore.printVideoList();

	}
}