//
//  main.c
//  Array
//
//  Created by edz on 2020/5/19.
//  Copyright © 2020 edz. All rights reserved.
//

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define Num 50

//判断是否为回文即逆序数组顺序不变，如：ASA、ASFDDFSA
int Palindrome(char a[]){
    long j = strlen(a);
    char result[Num];
    
    //将这个数组逆序过来输出至新数组
    for (int i = 0; i <= strlen(a); i++) {
        result[i] = a[j - i - 1];
    }
    
    
    
    if (strcmp(a, result) == 0) {
        printf("您所输入的字符串是回文数组！");
    }else {
        printf("您输入的字符串不是回文数组！");
    }
    
    return 0;
}

void scopy (char a[], char b[]) {
    int count = 0;
    while (b[count] != 0) {
        a[count] = b[count];
        count++;
    }
    a[count] = 0;
    
    printf("%s\n", a);
}

int main() {
    
    char priNum[Num];
    
    printf("请输入您想要判断是否为回文的字符串：\n");
    scanf("%s", priNum);//scanf函数返回值为1
    
    //Palindrome(priNum);
    
    scopy(priNum, "lisi");
    
    return 0;
}



