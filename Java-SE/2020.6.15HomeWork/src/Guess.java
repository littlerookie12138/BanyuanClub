import java.util.*;
class Guess {
	/*
	请选择难度等级
	1. 0~9
	2. 0~99
	3. 0~999
	0. 退出

	你当前选择的难度等级0~99
	请输入想要猜的次数，0返回上一级
	你的输入：

	现在开始猜数
	剩余次数10，请输入数字（0~999）：5
	输入的是5，没有猜中，猜小了

	剩余次数9，请输入数字：50
	输入的是50，没有猜中，猜大了

	剩余次数8，请输入数字：30
	输入的是30，恭喜你，猜中了

	是否继续，1.继续, 0. 退出

	猜题记录

	第1次 50 => -30
	第2次 70 => -10
	第3次 90 => +10
	第4次 80 => 正确

	*/
	public static void main(String[] args) {
		Random random = new Random();
		System.out.println("请选择难度等级" + "\n" + "1. 0~9" + "\n" + "2. 0~99" + "\n" + "3. 0~999" + "\n" + "0. 退出");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();

		while (choice != 0) {
			switch (choice) {
				case 1://0~9
					System.out.println("您当前选择的难度等级0~9");
					int result = random.nextInt(9);
					choice = guessLevel(result);
					break;
				case 2://0~99
					System.out.println("您当前选择的难度等级0~99");
					result = random.nextInt(99);
					choice = guessLevel(result);
					break;
				case 3://0~999
					System.out.println("您当前选择的难度等级0~999");
					result = random.nextInt(999);
					choice = guessLevel(result);
					break;
				case -1: //退出
					System.out.println("请选择难度等级" + "\n" + "1. 0~9" + "\n" + "2. 0~99" + "\n" + "3. 0~999" + "\n" + "0. 退出");
					choice = sc.nextInt();
					break;
			}
		}


	}

	public static int guessLevel(int num) {
		System.out.println("请输入想要猜的次数，0返回上一级:");
		Scanner sc = new Scanner(System.in);
		int times = sc.nextInt();
		int choice = -1;
		boolean isRight = false;

		if (times == 0) {
			return choice;
		}

		System.out.println("现在开始猜数");
		System.out.print("剩余次数" + times + "，请输入数字(0~9):");
		int answer = sc.nextInt();
		int[] arr = new int[100];
		int index = 0;

		while (!isRight) {
			if (answer == num) {
				System.out.println("输入的是" + answer + ",恭喜你猜中了");
				isRight = true;
				arr[index] = answer;
				System.out.println("是否继续,1继续,0退出:");
				choice = sc.nextInt();

				//猜题记录 
				for (int i = 0; i <= index; i++) {
					if (num == arr[i]) {
						System.out.println("第" + (i + 1) + "次  " + arr[i] + " => " + "正确");
						return choice;
					} else if (num > arr[i]) {
						System.out.println("第" + (i + 1) + "次  " + arr[i] + " => " + "+" + (num - arr[i]));
					} else {
						System.out.println("第" + (i + 1) + "次  " + arr[i] + " => " + (num - arr[i]));
					}
					
				}
				return choice;
			} else {
				if (answer < num) {
					System.out.println("输入的是" + answer + ",没有猜中,猜小了");
					times--;
					arr[index] = answer;
					index++;
				} else {
					System.out.println("输入的是" + answer + ",没有猜中,猜大了");
					times--;
					arr[index] = answer;
					index++;
				}

				if (times == 0) {
					System.out.println("次数用完了!");
					//猜题记录 
					for (int i = 0; i < index; i++) {
						if (num == arr[i]) {
							System.out.println("第" + (i + 1) + "次  " + arr[i] + " => " + "正确");
							return 0;
						} else {
							System.out.println("第" + (i + 1) + "次  " + arr[i] + " => " + (num - arr[i]));
						}
					}
					return 0;
				}
			}
			answer = sc.nextInt();
		}

		return 0;

	}
}