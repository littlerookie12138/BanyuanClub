# C语言
数据类型、分支循环、结构体、指针、函数、文件、网络。

## 引言

输出倒三角等腰三角形：<br>
#include <stdio.h>

int main() {
    for (int i = 0;i < 4; i++) {
        
        for (int j = 0; j <= i; j++) {
            printf(" ");
        }
        
        for (int n = 4; n > i; n--) {
            printf("* ");
        }
        
        printf("\n");
    }
    
    return 0;
}

char类型为什么是使用单引号包裹起来？  
答：因为怕和其他变量名冲突。


C语言的语法要素
单词：常量、变量、变量类型、运算符、分隔符

语法单位：表达式、变量定义、语句、函数定义、函数调用、（输入输出）

# C语言导论作业

#include <stdio.h>


int Factorial (int a) {
    int fact = 1;
    for (int i = 1; i <= a; i++) {
        fact *= i;
    }
    
    return fact;
}

int main() {
    // insert code here...
    int a = 0;
    
    int Factorial(int a);
    
    printf("请输入您想求阶乘的数字：");
    scanf("%d", &a);
    
    
    printf("%d\n", Factorial(a));
    
    return 0;
}

当输入100会提示0的原因是因为100的阶乘结果太大，int型放不下；  
当输入大于20时出现溢出，需要使用负数来存放，大于24时负数也放不下又绕回来存放结果。