class ObjectDemo {
  static Elephant createElephant(int heightInCM) {
    System.out.printf("创建一个大象，高度%d厘米\n", heightInCM);
    // 使用定义的类作为数据类型
    // 使用new 创建一个对象（实例）
    Elephant elephant = new Elephant();
    // 使用. 访问对象的属性，可以对属性赋值或使用
    elephant.heightInCM = heightInCM;
    return elephant;
  }

  static Fridge createFridge(int heightInCM) {
    System.out.printf("创建一个冰箱，高度%d厘米\n", heightInCM);
    Fridge fridge = new Fridge();
    fridge.heightInCM = heightInCM;
    return fridge;
  }

  static Lion createLion(int heightInCM) {
    System.out.printf("创建一个狮子，高度%d厘米\n", heightInCM);
    Lion lion = new Lion();
    lion.heightInCM = heightInCM;

    return lion;
  }

  static void putInElephant(Elephant elephant, Fridge fridge) {
    System.out.printf("把%d厘米高的大象装进%d厘米高的冰箱\n", elephant.heightInCM, fridge.heightInCM);

    //先判断冰箱是不是满的
    if (fridge.storageL != null || fridge.storageE != null) {
        System.out.println("冰箱已经满了不能再装了！");
        return;
    }

    //判断大象的高度是不是能放进冰箱
    if (elephant.heightInCM < fridge.heightInCM) {

      // 使用对象方法
      fridge.store(elephant);
      System.out.printf("冰箱里面的大象高度是%d厘米\n", fridge.storageE.heightInCM);
    } else {
      System.out.printf("冰箱装不下!\n");
    }

  }

  static void putInLion(Lion lion, Fridge fridge) {
    System.out.printf("把%d厘米高的狮子装进%d厘米高的冰箱\n", lion.heightInCM, fridge.heightInCM);

    //先判断冰箱是不是满的
    if (fridge.storageL != null || fridge.storageE != null) {
        System.out.println("冰箱已经满了不能再装了！");
        return;
    }

    //判断狮子的高度是不是能放进冰箱
    if (lion.heightInCM < fridge.heightInCM) {
        fridge.store(lion);
        System.out.printf("冰箱里面的狮子高度是%d厘米\n", fridge.storageL.heightInCM);
    } else {
        System.out.println("冰箱塞不下!");
    }
  }

  public static void main(String[] args) {
    Elephant elephant = createElephant(300);
    Fridge fridge = createFridge(500);
    Lion lion = createLion(450);

    //装进去大象
    putInElephant(elephant, fridge);

    //取出大象并输出取出大象的身高
    Elephant outElephant = fridge.removeE();
    System.out.println("取出的大象身高为" + outElephant.heightInCM);

    //不取出大象装进去狮子
    putInLion(lion, fridge);

    //测试当冰箱里没有大象只有狮子时是否能正常提示
    outElephant = fridge.removeE();

  }
}


