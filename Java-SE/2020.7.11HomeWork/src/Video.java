import java.io.Serializable;

class Video implements Serializable {
// 	Video对象至少具有以下属性（其他属性可以按需扩展）:
// 	- 片名
// 	- 是否被出租的标识 true 表示还在 ,false 表示出租
// 	- 用户的平均评分
	String name;
	String type;
	boolean isRentedOrNot;
	int scoresByCus;
	int[] a = new int[100];
	int count = 0;

	//设置影片的状态
	void setVideoStatus(String name, String type, boolean status) {
		this.name = name;
		this.type = type;
		this.isRentedOrNot = status;
	}

	//获取影片片名
	String getVideoName() {
		return name;
	}

	//获取影片类型
	String getVideoType() {
		return type;
	}

	//获取影片状态
	boolean getVideoStatus() {
		return isRentedOrNot;
	}

	//添加影片评分,只有借过的用户可以添加评分
	void setVideoScores(int temp) {
		a[count++] = temp;
	}

	//计算该影片平均评分
	int getVideoScoresByCus() {
		int temp = 0;
		for (int i = 0; i < count; i++) {
			temp += a[i];
		}
		if (count == 1) {
			temp = 0;
			return temp;
		}
		temp = temp / (count - 1);

		return temp;
	}


	@Override
	public String toString() {
		return "Video{" +
				"name='" + name + '\'' +
				", type='" + type + '\'' +
				", isRentedOrNot=" + isRentedOrNot +
				", scoresByCus=" + scoresByCus +
				", count=" + count +
				'}';
	}
}