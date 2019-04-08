#define _CRT_SECURE_NO_WARNINGS 1

#define ROW 9
#define COL 9

#include<stdio.h>

void menu();

void game();

void init(char show_map[ROW][COL], char mine_map[ROW][COL], int rows, int cols);

void printArray(char print_map[ROW][COL], int rows, int cols);

void random_mine(char mine_map[ROW][COL], int rows, int cols);

