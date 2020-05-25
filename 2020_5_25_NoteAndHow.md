# 数据结构
分为两类：逻辑结构以及存储结构

## 逻辑结构
- 集合
- 线性结构
- 树形结构
- 图形结构

## 存储结构
- 顺序存储结构
- 链式存储结构
- 索引存储结构


## 时间复杂度
t(n) 与n的最高阶关系

T(n)=O(1)	常数阶  
T(n)=O(n)	线性阶  
T(n)=O(n2)	平方阶  
T(n)=O(n3)	立方阶  
T(n)=O(2n)	指数阶  
T(n)=O(logn)	对数阶  
常见的算法时间复杂度的比较（由小到大）

O(1) < O(logn) < O(n) < O(n * logn) < O(n<sup>2</sup>) < O(n<sup>3</sup>) < O(2<sup>n</sup>)


## 顺序表

```
typedef struct {
    int *head;      //顺序表指针变量
    int *length;    //记录当前顺序表的长度
    int capacity;   //记录顺序表分配的存储容量
}Seqlist;//Sequece List-- 顺序
```
 
 
 
```
//
//  main.c
//  SeqList
//
//  Created by zwy on 2020/5/25.
//  Copyright © 2020 edz. All rights reserved.
//

#include <stdio.h>
typedef struct {
    int* head;
    int length;
    int capacity;
} SeqList;

//顺序表的初始化
SeqList* iniList(int size) {
    SeqList* pMylist = malloc(sizeof(int));
    pMylist -> capacity = size;
    pMylist -> head = malloc(pMylist -> capacity * sizeof(int));
    pMylist -> length = 0;
    
    return pMylist;
}

//增
SeqList add (SeqList* pMylist, int elem, int position) {
    //判断插入位置在不在0~length内
    if (pMylist -> length < position || position < 0) {
        printf("插入位置有误！\n");
        return *pMylist;
    }
    
    //检查结构体长度，如果长度满了，那么重新申请一个容量+1的空间
    if (pMylist -> capacity == pMylist -> length) {
        int* temp = (int*) realloc (pMylist -> head, ((pMylist -> capacity + 1) * sizeof(int)));
        pMylist -> length++;
        
        if (!temp) {
            printf("重新分配内存失败!\n");
            return *pMylist;
        }
        
        pMylist -> head = temp;
        pMylist -> capacity += 1;
    }
    
    //插入位置及其后元素后移一位
    for (int i = pMylist->length - 1; i >= position; i--) {
        (*pMylist).head[i + 1] = (*pMylist).head[i];
    }
    
    (*pMylist).head[position] = elem;//把即将添加的值放入想要的位置
    
    return *pMylist;
    
}


//删
SeqList delete (SeqList* myList,int position) {
    //判断删除的位置是否正确，判断范围为0 ~ length - 1
    if (position > myList -> length || position < 0) {
        printf("删除的位置不对!\n");
        return *myList;
    }
    
    
    //删除位置的后面一位向前移
    for (int i = position; i < myList -> length; i ++) {
        (*myList).head[i] = (*myList).head[i + 1];
    }
    
    myList -> length -= 1;
    
    return *myList;
}

//改
SeqList modify (SeqList* myList, int position, int elem) {
    //判断要修改的位置是否在顺序表中
    if (position > (*myList).length - 1 || position < 0) {
        printf("要修改的位置不对！\n");
        return *myList;
    }
    
    //修改那一位的值
    (*myList).head[position] = elem;
    
    return *myList;
}


//查
int search(SeqList* myList, int elem) {
    for (int i = 0; i < (*myList).length - 1; i++) {
        if ((*myList).head[i] == elem) {
            return i;
        }
    }
    
    return -1;
}

//打印顺序表
void displayList (SeqList* mylist) {
    for (int i = 0; i < (*mylist).length; i++) {
        printf("%d  ", (*mylist).head[i]);
    }
    printf("\n");
}

int main(int argc, const char * argv[]) {
    
    SeqList* mylist = iniList(10);
    
    //初始化这个结构体
    for (int i = 0; i < mylist -> capacity; i++) {
        mylist -> head [i] = i + 1;
        (*mylist).length++;
    }
    
    displayList(mylist);
    
//    delete(mylist, 4);
//    displayList(mylist);
    
    add(mylist, 5, 4);
    displayList(mylist);
    
    printf ("%d\n", search(mylist, 6));
    
    return 0;
}

```
