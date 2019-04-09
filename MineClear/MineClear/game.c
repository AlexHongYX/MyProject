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

//�������10����
void random_mine(char mine_map[ROW][COL], int rows, int cols){
	int count = COUNT;
	int row = 0;
	int col = 0;
	while (count>0){
		row = rand() % rows;
		col = rand() % cols;
		if (mine_map[row][col] == '1'){
			continue;
		}
		mine_map[row][col] = '1';
		--count;
	}
}

//���������һ�����ִ�����Χ8�������м�����
void update_show(char show_map[ROW][COL], char mine_map[ROW][COL], int rows, int cols,int row,int col){

	int count = 0;
	//�ж�row,col��Χ����
	if (row - 1 >= 0 && col - 1 >= 0 && mine_map[row - 1][col - 1] == '1'){
		++count;
	}
	if (row - 1 >= 0 && mine_map[row - 1][col] == '1'){
		++count;
	}
	if (row - 1 >= 0 && col + 1 <cols && mine_map[row - 1][col + 1] == '1'){
		++count;
	}
	if (col - 1 >=0 && mine_map[row][col - 1] == '1'){
		++count;
	}
	if (col + 1 <cols && mine_map[row][col + 1] == '1'){
		++count;
	}
	if (row + 1 <rows && col - 1 >=0 && mine_map[row + 1][col - 1] == '1'){
		++count;
	}
	if (row + 1 <rows && mine_map[row + 1][col] == '1'){
		++count;
	}
	if (row + 1 <rows && col + 1 <cols && mine_map[row + 1][col + 1] == '1'){
		++count;
	}
	show_map[row][col] = count + '0';
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

	
	int count = 0;
	while (1){
		//���û�����һ�����꣬��Ҫ�������ĺϷ���
		printf("����������(row col)��");
		scanf("%d%d", &row, &col);
		if (row<0 || row >= ROW || col<0 || col >= COL){
			printf("��������ȷ������\n");
			continue;
		}
		if (show_map[row][col] != '*'){
			printf("��λ���ѱ��򿪣�����������\n");
			continue;
		}
		if (mine_map[row][col] == '1'){
			printf("GameOver!\n");
			printArray(mine_map, ROW, COL);
			break;
		}
		if (row >= 0 && row < ROW&&col >= 0 && col < COL&&show_map[row][col] == '*'){
			update_show(show_map,mine_map, ROW, COL,row,col);
			//���ڲ���
			//show_map[row][col] = '#';
		}
		count++;
		if (count == ROW*COL - COUNT){
			printArray(mine_map, ROW, COL);
			printf("You Win\n");
			break;
		}
		//��ӡ����
		printArray(show_map, ROW, COL);
		printArray(mine_map, ROW, COL);
	}
	//�������������Ϸ����
	

}