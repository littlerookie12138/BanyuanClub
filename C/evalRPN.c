//
//  main.c
//  evalRPN
//
//  Created by zwy on 2020/5/29.
//  Copyright © 2020 edz. All rights reserved.

//  根据逆波兰表示法，求表达式的值。
//  有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
//  使用栈来处理

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#define len 20

//定义存储运算式的栈
typedef struct stack{
    int data[len];
    int top;
} Stack;

//初始化栈
Stack* initStack() {
    Stack* s = malloc(sizeof(Stack));
    s->top = -1;
    
    return s;
}

//判断栈是否为满 成立返回1 否则返回0
int is_full (Stack* s) {
    if (s->top < len) {
        return 0;
    } else {
        return 1;
    }
}

//判断栈是否为空
int is_empty (Stack* s) {
    if (s->top == -1) {
        return 1;
    } else {
        return 0;
    }
}

//压栈 push
Stack* push(Stack* s, int elem) {
    //判断栈满没满
    if (is_full(s)) {
        //栈满了
        return s;
    } else {
        s->top++;
        s->data[s->top] = elem;
    }
    
    return s;
}

//出栈 pop 返回一个出栈的值
Stack* pop(Stack* s, int* first) {
    if (is_empty(s)) {
        //栈是空的没法出栈
        return s;
    } else {
        *first = s->data[s->top];
        s->data[s->top] = 0;
        s->top--;
        
        return s;
    }
}

//将+ - * / 对应完毕
int switchCase (char operation, int a, int b) {
    int t = 0;
    switch (operation) {
        case '+':
            t = a + b;
            break;
            
        case '-':
            t = a - b;
            break;
        
        case '*':
            t = a * b;
            break;
            
        case '/':
            t = a / b;
            break;
            
        default:
            break;
    }
    
    return t;
}

//运算式
int caluate (char* operation, int a, int b) {
    int result = 0;
    if (strcmp(operation, "+") == 0 || strcmp(operation, "-") == 0 || strcmp(operation, "*") == 0 || strcmp(operation, "/") == 0) {
        
        result = switchCase(*operation, b, a);
    }
    return result;
}

int main(int argc, const char * argv[]) {
    Stack* s = initStack();
    char temp[len][len];//接受键盘要输入的数据
    int elem = 0;//将输入的数据转换为整形后入栈
    int a ,b;//存储运算符前两个出栈的数据
    a = b = 0;
    int result = 0;//接受计算后的数
    
    //从键盘获取想要运算的运算式,把输入的字符串转化为整数存入data中
    printf("请输入想要运算的运算式，以exit结束\n");
    
    for (int i = 0; i < len; i++) {
        scanf("%s", temp[i]);
        //判断程序是否终止
        if (strcmp(temp[i], "exit") == 0) {
            strcpy(temp[i], "0");
            break;
        }
        //判断输入是否为运算符 如果是 进行运算
        if (strcmp(temp[i], "+") == 0 || strcmp(temp[i], "-") == 0 || strcmp(temp[i], "*") == 0 || strcmp(temp[i], "/") == 0) {
            s = pop(s, &a);
            s = pop(s, &b);
            result = caluate(temp[i], a, b);
            s = push(s, result);
            continue;
        }
        
        for (int j = 1; j <= strlen(temp[i]); j++) {
            elem = elem * 10 + (temp[i][j - 1] - '0');
        }
        s = push(s, elem);
        elem = 0;
    }
    
    printf("该表达式的结果为%d\n", result);
    
    return 0;
}
