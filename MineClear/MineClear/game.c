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

//随机生成10个雷
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

//翻开后更新一个数字代表周围8个格子有几个雷
void update_show(char show_map[ROW][COL], char mine_map[ROW][COL], int rows, int cols,int row,int col){

	int count = 0;
	//判断row,col周围的雷
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

	
	int count = 0;
	while (1){
		//让用户输入一组坐标，需要检查坐标的合法性
		printf("请输入坐标(row col)：");
		scanf("%d%d", &row, &col);
		if (row<0 || row >= ROW || col<0 || col >= COL){
			printf("请输入正确的坐标\n");
			continue;
		}
		if (show_map[row][col] != '*'){
			printf("该位置已被打开，请重新输入\n");
			continue;
		}
		if (mine_map[row][col] == '1'){
			printf("GameOver!\n");
			printArray(mine_map, ROW, COL);
			break;
		}
		if (row >= 0 && row < ROW&&col >= 0 && col < COL&&show_map[row][col] == '*'){
			update_show(show_map,mine_map, ROW, COL,row,col);
			//用于测试
			//show_map[row][col] = '#';
		}
		count++;
		if (count == ROW*COL - COUNT){
			printArray(mine_map, ROW, COL);
			printf("You Win\n");
			break;
		}
		//打印数组
		printArray(show_map, ROW, COL);
		printArray(mine_map, ROW, COL);
	}
	//如果跳出来则游戏结束
	

}