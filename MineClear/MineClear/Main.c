#define _CRT_SECURE_NO_WARNINGS 1

#include"game.h"

int main(){

	int input = 0;
	while (1){
		menu();
		scanf("%d", &input);
		if (input == 1){
			game();
		}
		else if(input == 0){
			break;
		}
		else{
			printf(" ‰»Î¥ÌŒÛ£¨«Î÷ÿ ‰£°\n");
		}
	}

	return 0;
}