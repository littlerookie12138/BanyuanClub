

# 计算机网络
**网络**
- 局域网
- 广域网
- Internet



<html>
<!--在这里插入内容-->
<div class = "father">
    <div>
        <table style = "text-align:center;">
            <tr>
                <td>OSI参考模型</td>
                <td>TCP/IP参考模型</td>
            </tr>
            <tr>
                <td>应用层</td>
                <td>应用层</td>
            </tr>
            <tr>
                <td>表示层</td>
                <td></td>
            </tr>
            <tr>
                <td>会话层</td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>传输层</td>
                <td>传输层</td>
            </tr>
            <tr>
                <td>网络层</td>
                <td>网络层</td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>数据链路层</td>
                <td>主机至网络层</td>
            </tr>
            <tr>
                <td>物理层</td>
                <td></td>
            </tr>
        </table>
    </div>
    <div style = "float : right;">
        <p >自下至上
        </p>
    </div>

</div>
</html>




> 传输层中的“IP地址以及端口号”可以帮我们标识唯一的应用程序（进程）；网络中进程利用三元组【IP地址、协议、端口】可以实现网络通信了，因此，socket应运而生。
> {
> socket_stream:面向连接的传输方式（类似于TCP）
> socket_dgram:无连接的传输方式（类似于UDP）效率高
> }

**浏览器工作方式**

//https 表示安全的加密的http<br>
++https://www.baidu.com++  

http -> 80端口  
https -> 443端口

protocol -> https  
hostname -> www.baidu.com   ip地址（4个字节）  
port -> 一个数字，0 - 65535 16bit 4个字节  
path -> 路径

URL ---- 本质为一个资源（统一资源定位符）



1. 在文件中去查找对应关系：/etc/hosts
2. 查找 dns 缓存（存在内存中，每次开机会清掉）
3. 发送请求给dns sever


# 操作系统

**++什么是操作系统++？**  
答：负责管理所有应用程序

操作系统主要负责：
- 内存、虚拟内存
- 进程{
    进程调度--队列
    资源分配--死锁、饿死
}
- 文件系统--针对硬盘
- 设备管理器--针对硬件

1.操作系统的主要设计目标：  
- 有效的使用硬件  
- 容易的使用资源  

2.批处理系统  
3.分时系统  
4.个人系统  
5.并行系统  
6.分布式系统  
7.实时系统 

# 计算机网络作业
复习题：  
8.端口出现占用的情况。
客户端服务已经登录过，新副本线程被拒绝。
9.服务器端通常要存储数据，如果运行在本地客户端上会对客户端产生过多的需求。
练习题：
3.假设连接到因特网的主机在2010年是5亿台。这个数字以每年20%的速度增长，到2020年主机数量是多少?  
答：5亿 * (1.2)<sup>10</sup>   
9.比较16位端口地址(0〜65535)和32位IP地址(0〜4294967295)的范围，为什么我们需要IP地址有如此大的范围，但端口号却相对范围小呢?  
答：IP地址通常有一一对应关系，而端口会在进程结束时释放掉所占用的端口，因此端口号的使用跟IP地址比起来不是那么捉襟见肘。

输入www.baidu.com时浏览器会发生什么？  
答：当我们在地址栏中输入www.baidu.com时,会首先在本地文件中寻找是否有这个网址的信息，没有找到的话回去查看DNS 缓存中有没有与这个网址通信的记录，如果还是没有的话，根据域名去寻找服务器，通过各种网络设备传递报文寻找这个地址，当找到这个地址时，服务器会进行响应，解析请求，并将资源传回浏览器。

# 操作系统作业
**复习题:**  
9.为什么操作系统需要队列?  
答：因为在操作系统中各个进程执行通常有个先后顺序，队列能够很好的满足操作系统的这个需求，先进先出的处理方式。
10.死锁和饿死有何区别?
答：通俗来说死锁即意味着某个资源一直在被占用；而饿死意味着某个进程在等待某个资源时，被别的进程抢先使用这个资源，导致这个进程一直无法调用这个资源导致无法运行。

**练习题：**  
9.三个进程(A、B和C)同时运行，进程A占用File1但需要File2。进程B占用File3但需要File1。进程C占用File2但需要File3。为这几个进程画一个框图。这种情况是不是死锁?    
答：这种情况即发生了死锁。  ![](/Users/edz/Desktop/4C7BB0DA-48AE-4CDB-BF9C-D556ADB96272.png)  
10.三个进程(A、B和C)同时运行，进程A占有File1，进程B占有File2但需要File1,进程C占有File3但需要File2。为这几个进程画一个框图。这种情况是不是死锁?如果不是，说明进程怎样最后完成它们的任务。

答：这种情况不是死锁，三个进程等待A执行完毕释放File 1，然后B使用File 1，C等待B结束并释放File 2就可以完美实现各自的任务。![2](/Users/edz/Desktop/2.png)

