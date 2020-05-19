//
//  main.c
//  FilterUn16
//
//  Created by edz on 2020/5/19.
//  Copyright © 2020 edz. All rights reserved.
//

#include <stdio.h>

int main() {
    //输入一个小于80个字符的字符串，过滤其中的非十六进制字符，并将其都转换为10进制
    
    //从键盘输入一个字符串
    char a[100];
    printf("请从键盘输入一串字符串：\n");
    scanf("%s", a);
    
    if (strlen(a) > 80) {
        printf("您输入的字符过长请重新输入！！");
        main();
    }
    
    int count = 0;
    int temp = 0;
    while (a[count] != 0) {
        if ('0' <= a[count] && a[count] <= '9') {
            temp = temp * 16 + a[count] - '0';
        }else if ('a' <= a[count] && a[count] <= 'f') {
            temp = temp * 16 + a[count] - 'a' + 10;
        }else if ('A' <= a[count] && a[count] <= 'F') {
            temp = temp * 16 + a[count] - 'A' + 10;
        }
        
        count++;
    }
    
    printf("%d\n", temp);
    
    return 0;
}
