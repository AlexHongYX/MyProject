#define _CRT_SECURE_NO_WARNINGS 1

#define ROW 9
#define COL 9
#define COUNT 10

#include<stdio.h>

//�˵�
void menu();

//��Ϸ������
void game();

//��ʼ��
void init(char show_map[ROW][COL], char mine_map[ROW][COL], int rows, int cols);

//��ӡ���
void printArray(char print_map[ROW][COL], int rows, int cols);

//���������
void random_mine(char mine_map[ROW][COL], int rows, int cols);

//������ʾ
void update_show(char show_map[ROW][COL], char mine_map[ROW][COL], int rows, int cols, int row, int col);
