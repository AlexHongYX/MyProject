#define _CRT_SECURE_NO_WARNINGS 1

#include<stdio.h>

#define ROW 3
#define COL 3

void menu();

void initBoard(int row, int col, char board[][COL]);

void printBoard(int row, int col, char board[][COL]);

void playerMove(int row, int col, char board[][COL]);

void computerMove(int row, int col, char board[][COL]);

int isFull(int row, int col, char board[][COL]);

char check_win(int row, int col, char board[][COL]);

void game();