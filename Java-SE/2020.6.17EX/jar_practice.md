### 目的

熟悉jar打包的相关命令行操作

### 练习

dist下的main.jar 中缺少了club/banyuan/animal/Dog.class和META-INF/MANIFEST.MF。 请编译src下的Dog.java生成Dog.class。

​		运行步骤：

​		javac -cp dist/main.jar src/club/banyuan/animal/Dog.java 



1. 将Dog.class打包成Dog.jar，放置到lib路径下，使用java运行main.jar中club.banyuan.Main类中的main方法

   运行步骤

   1. jar cf Dog.jar -C src club/banyuan/animal/Dog.class 
   2. 在dist同级目录下创建lib文件夹，将Dog.jar移动到里面
   3. java -cp dist/main.jar:lib/Dog.jar club.banyuan.Main

   

1. 将Dog.class添加到main.jar中，使用java -jar main.jar 运行程序(需要增加META-INF/MANIFEST.MF到jar包中，指定Main-Class)。

   运行步骤：

   1. jar uf dist/main.jar -C src club/banyuan/animal/Dog.class
   2. java -cp dist/main.jar club.banyuan.Main

   

程序成功运行后输出

```
训练狗狗小强
小强: 汪汪
狗狗小强和猫咪汤姆一起玩
汤姆: 喵..
```