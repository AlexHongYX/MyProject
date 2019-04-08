#define _CRT_SECURE_NO_WARNINGS 1

#include "game.h"
#include<stdlib.h>
#include<time.h>

//菜单
void menu(){
	printf("**************************\n");
	printf("*****     1.play     *****\n");
	printf("*****     0.exit     *****\n");
	printf("**************************\n");
}

//初始化两个数组
void init(char show_map[ROW][COL], char mine_map[ROW][COL], int rows, int cols){
	int row = 0, col = 0;
	for (row = 0; row < rows; row++){
		for (col = 0; col < cols; col++){
			show_map[row][col] = '*';
			mine_map[row][col] = '0';
		}
	}
}

//数组输出
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
	//使用两个二维数组表示地图的两种格式
	//show_map表示每个位置是否被翻开: *表示未翻开，'1'-'8'表示周围8个格子中有几个地雷
	//mine_map表示每个位置是否是地雷:‘0’表示不是地雷，‘1’表示是地雷
	char show_map[ROW][COL];
	char mine_map[ROW][COL];

	//初始化两个数组
	init(show_map, mine_map,ROW,COL);

	//随机生成10个地雷
	random_mine(mine_map,ROW,COL);

	//打印数组
	printArray(show_map,ROW,COL);
	printArray(mine_map, ROW, COL);

	//让用户输入一组坐标，需要检查坐标的合法性
	printf("请输入坐标：");
	scanf("%d%d", &row, &col);



}