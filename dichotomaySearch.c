//
//  main.c
//  dichotomySearch
//
//  Created by zwy on 2020/6/2.
//  Copyright © 2020 edz. All rights reserved.
//  二分查找

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define SIZE 10

//交换函数
void swap(int a[], int i, int j) {
    int temp = 0;
    
    temp = a[i];
    a[i] = a[j];
    a[j] = temp;
}

//快速排序
void quick_sort (int a[], int left, int right) {
    int i, j, temp;
    //判断如果右边大于左边 直接返回
    if (left > right) {
        return;
    }
    
    i = left;
    j = right;
    temp = a[i];//以a[i]为基准点
    
    while (i != j) {
        while (a[j] >= temp && i < j) {
            j--;
        }
        
        while (a[i] <= temp && i < j) {
            i++;
        }
        
        if (i < j) {
            swap(a, i, j);
        }
    }
    
    //当我找到i这个位置时，i左边的数都是小于基准点的，因此把i的值丢给最左边后面再进行排序就可以了
    a[left] = a[i];
    a[i] = temp;
    
    quick_sort(a, left, i - 1);
    quick_sort(a, i + 1, right);
    
}

//打印这个数组
void printArr(int a[], int len) {
    for (int i = 0; i < len; i++) {
        printf("%4d", a[i]);
    }
    printf("\n");
}

//二分法查找
int DichotomySearch(int a[], int key) {
    //根据关键字key在数组中进行查找
    int low = 0;
    int high = SIZE - 1;
    int mid;
    
    while (low < high) {
        mid = (low + high) / 2;
        if (a[mid] == key) {
            return mid;
        } else if (a[mid] < key) {
            low = mid + 1;
        } else if (a[mid] > key) {
            high = mid - 1;
        }
    }
    return -1;
}

int main(int argc, const char * argv[]) {
    srand((unsigned int)time(NULL));
    int a[SIZE];
    
    for (int i = 0; i < SIZE; i++) {
        a[i] = rand() % 100;
    }
    
    printArr(a, SIZE);
    
    quick_sort(a, 0, SIZE - 1);
    
    printArr(a, SIZE);
    
    printf("------------以下为查找板块----------------\n");
    
    int key = 0;
    printf("请输入您想查找的数字:\n");
    scanf("%d", &key);
    
    if (DichotomySearch(a, key) == -1) {
        printf("没有该数\n");
    } else {
        printf("您要查找的%d的位置为%d\n", key, DichotomySearch(a, key));
    }
    
    return 0;
}
