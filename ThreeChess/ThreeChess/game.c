#define _CRT_SECURE_NO_WARNINGS 1

#include"game.h"
#include<stdlib.h>
#include<time.h>

void menu(){
	printf("*************************\n");
	printf("*****     1.play    *****\n");
	printf("*****     0.exit    *****\n");
	printf("*************************\n");
}

void initBoard(int row,int col,char board[][COL]){
	
	int i = 0;
	int j = 0;
	for (i = 0; i < row; i++){
		for (j = 0; j < col; j++){
			board[i][j] = ' ';
		}
	}
}

void printBoard(int row, int col, char board[][COL]){
	int i = 0;
	int j = 0;
	for (i = 0; i < row; i++){
		for (j = 0; j < col; j++){
			printf(" %c ", board[i][j]);
			if (j != col - 1){
				printf("|");
			}
		}
		printf("\n");
		if (i != row - 1){
			for (j = 0; j < col; j++){
				printf("---");
				if (j != col - 1){
					printf("|");
				}
			}
			printf("\n");
		}
	}
}

void playerMove(int row, int col, char board[][COL]){
	int i = 0, j = 0;
	printf("玩家走（输入坐标，eg:3 5）：");
	while (1){
		scanf("%d%d", &i, &j);
		if (i <= row&&j <= col&&board[i-1][j-1]==' '){
			board[i - 1][j - 1] = '*';
			break;
		}
		else if(i>row||j>col){
			printf("坐标错误，请重输:");
		}
		else{
			printf("坐标被占用，请重输：");
		}
	}
}

void computerMove(int row, int col, char board[][COL]){
	int i=0, j=0;
	printf("电脑走：\n");
	while (1){
		i = rand() % row;
		j = rand() % col;
		if (i < row&&j < col&&board[i][j] == ' '){
			board[i][j] = '#';
			break;
		}
	}
}

int isFull(int row, int col, char board[][COL]){
	int i = 0, j = 0;
	for (i = 0; i < row; i++){
		for (j = 0; j < col; j++){
			if (board[i][j] == ' '){
				return 0;
			}
		}
	}
	return 1;
}

char check_win(int row, int col, char board[][COL]){
	int judge = 0;
	char ret = 0;
	int i = 0, j = 0;
	for (i = 0; i < row; i++){
		if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][1] == board[i][2] && board[i][1] != ' '){
			return board[i][1];
		}
	}
	for (j = 0; j < col; j++){
		if (board[0][j] == board[1][j] && board[0][j] == board[2][j] && board[1][j] == board[2][j] && board[1][j] != ' '){
			return board[1][j];
		}
	}
	if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[1][1] == board[2][2] && board[1][1] != ' '){
		return board[1][1];
	}
	if (board[0][2] == board[1][1] && board[0][2] == board[2][1] && board[1][1] == board[2][1] && board[1][1] != ' '){
		return board[1][1];
	}
	if (isFull(row,col,board))
	{
		return 'E';
	}
	return 'C';
	
}

void game(){
	srand((unsigned int)time(NULL));
	char ret;
	char board[ROW][COL];
	initBoard(ROW, COL, board);
	printBoard(ROW, COL, board);
	//玩家赢 *
	//电脑赢 #
	//平局   E
	//继续走 C
	while (1){
		playerMove(ROW, COL, board);
		printBoard(ROW, COL, board);
		if ((ret = check_win(ROW, COL, board)) != 'C'){
			break;
		}
		
		computerMove(ROW, COL, board);
		printBoard(ROW, COL, board);
		if ((ret = check_win(ROW, COL, board)) != 'C'){
			break;
		}
		
	}
	if (ret == '*'){
		printf("玩家赢\n");
	}
	else if (ret == '#'){
		printf("电脑赢\n");
	}
	else{
		printf("平局\n");
	}
}