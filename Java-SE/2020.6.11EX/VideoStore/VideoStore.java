class VideoStore {
	// 将包含一个Video数组。

	// VideoStore中定义方法
	// addVideo(String): 添加一个新的电影
	// checkOut(String): 根据片名借出电影
	// returnVideo(String): 归还电影
	// receiveRating(String, int) : 设置用户对电影的评分(1~5)，收到评分之后，计算该电影的平均评分，然后保存到Video的评分属性中
	// listInventory(): 列出整个库存的电影。
	Video[] video = new Video[100];
	int count = 0;

	//添加一个新的电影，新电影默认未被借出
	void addVideo(String name, String type) {
		Video v = new Video();
		v.setVideoStatus(name, type, true);
		v.setVideoScores(0);
		video[count++] = v;
	}

	//根据片名借出电影
	void checkOut(String name) {
		for (int i = 0; i < count; i++) {
			if (video[i].getVideoName().equals(name)) {
				//找到该电影后判断电影是否在店
				if (video[i].isRentedOrNot == true) {
					video[i].isRentedOrNot = false;
					System.out.println("片片已经借出，请爱护哦~");
					System.out.println("欢迎下次再来!");
					System.out.println();
					return;
				} else {
					System.out.println("这部电影太火了已经被借走啦！");
					System.out.println();
					return;
				}

			}
		}
		System.out.println("小店还未引进这部电影，敬请期待！");
		System.out.println();

	}

	//归还电影并打分
	void returnAndScore(String name, int score) {
		for (int i = 0; i < count; i++) {
			if (video[i].getVideoName().equals(name)) {
				//找到该电影后判断电影是否在店
				if (video[i].isRentedOrNot == true) {
					System.out.println("片片不是我们店的哦");
					System.out.println();
					return;
				} else {
					video[i].isRentedOrNot = true;
					video[i].setVideoScores(score);
					System.out.println("感谢借阅~欢迎下次再来");
					System.out.println();
					return;
				}

			}
		}
	}

	//打印店铺电影列表
	void printVideoList() {
		//
		for (int i = 0; i < count; i++) {
			System.out.println("片名为" + video[i].getVideoName() + ",类型属于" + video[i].getVideoType() + ",评分是" + video[i].getVideoScoresByCus() + ",在店:" + video[i].getVideoStatus());
		}

	}

}