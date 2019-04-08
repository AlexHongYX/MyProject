#define _CRT_SECURE_NO_WARNINGS 1

#include "game.h"
#include<stdlib.h>
#include<time.h>

//�˵�
void menu(){
	printf("**************************\n");
	printf("*****     1.play     *****\n");
	printf("*****     0.exit     *****\n");
	printf("**************************\n");
}

//��ʼ����������
void init(char show_map[ROW][COL], char mine_map[ROW][COL], int rows, int cols){
	int row = 0, col = 0;
	for (row = 0; row < rows; row++){
		for (col = 0; col < cols; col++){
			show_map[row][col] = '*';
			mine_map[row][col] = '0';
		}
	}
}

//�������
void printArray(char print_map[ROW][COL], int rows, int cols){
	int row = 0, col = 0;
	for (row = 0; row < rows; row++){
		for (col = 0; col < cols; col++){
			printf("%c ", print_map[row][col]);
		}
		printf("\n");
	}
	printf("\n\n");
}

void random_mine(char mine_map[ROW][COL], int rows, int cols){
	int count = 0;
	int row = 0;
	int col = 0;
	while (1){
		row = rand() % rows;
		col = rand() % cols;
		if (mine_map[row][col] == '0'){
			mine_map[row][col] = '1';
			++count;
			if (count == 10){
				break;
			}
		}
	}
	

}

void game(){
	
	srand((unsigned int)time(NULL));
	int row=0,col=0;
	//ʹ��������ά�����ʾ��ͼ�����ָ�ʽ
	//show_map��ʾÿ��λ���Ƿ񱻷���: *��ʾδ������'1'-'8'��ʾ��Χ8���������м�������
	//mine_map��ʾÿ��λ���Ƿ��ǵ���:��0����ʾ���ǵ��ף���1����ʾ�ǵ���
	char show_map[ROW][COL];
	char mine_map[ROW][COL];

	//��ʼ����������
	init(show_map, mine_map,ROW,COL);

	//�������10������
	random_mine(mine_map,ROW,COL);

	//��ӡ����
	printArray(show_map,ROW,COL);
	printArray(mine_map, ROW, COL);

	//���û�����һ�����꣬��Ҫ�������ĺϷ���
	printf("���������꣺");
	scanf("%d%d", &row, &col);



}