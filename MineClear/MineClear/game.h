#define _CRT_SECURE_NO_WARNINGS 1

#define ROW 9
#define COL 9
#define COUNT 10

#include<stdio.h>

//菜单
void menu();

//游戏主函数
void game();

//初始化
void init(char show_map[ROW][COL], char mine_map[ROW][COL], int rows, int cols);

//打印输出
void printArray(char print_map[ROW][COL], int rows, int cols);

//随机生成雷
void random_mine(char mine_map[ROW][COL], int rows, int cols);

//更新显示
void update_show(char show_map[ROW][COL], char mine_map[ROW][COL], int rows, int cols, int row, int col);
