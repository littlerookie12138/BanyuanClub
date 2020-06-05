//
//  main.c
//  TestAnswer
//
//  Created by zwy on 2020/5/22.
//  Copyright © 2020 edz. All rights reserved.
//

#include <stdio.h>
#include <math.h>


# if 0
//求所有四位数各位的四次方相加为这个数本身的数
int main () {
    int n = 1000;
    int count = 0;
    int result[100];
    
    while (n < 10000) {
        if (pow(n % 10, 4) + pow(n / 10 % 10, 4) + pow(n / 100 % 10, 4) + pow(n / 1000, 4) == n) {
            result[count] = n;
            count++;
        }
        n++;
    }
    
    for (int i = 0; i < count; i++) {
        printf("%d ", result[i]);
    }
    
    printf("\n");
    
    return 0;
}
#endif

# if 0
//小孩上台阶问题(斐波那契数列)
int solution(int n) {
    if (n == 1) {
        return 1;
    }else if (n == 2) {
        return 2;
    }else {
        return solution(n-1) + solution(n - 2);
    }
    
}
int main () {
    int n = 0;
    printf("请输入一共有多少个台阶:\n");
    scanf("%d", &n);
    
    printf("%d阶台阶共有%d种上法\n", n, solution(n));
}
#endif

# if 1
int main(int argc, const char * argv[]) {
    int i = 0;
    int count;
    while (i < 10) {
      if (i & 1)
        i++;
      if (i == 5 || i == 8)
        break;
      i++;
        count++;
     }
    
    printf("%d\n", count);
    return 0;
}
#endif
