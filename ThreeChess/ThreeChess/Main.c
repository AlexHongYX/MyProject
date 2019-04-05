#define _CRT_SECURE_NO_WARNINGS 1

#include"game.h"

int main(){
	
	int input = 0;
	do{
		menu();
		printf("ÇëÑ¡Ôñ£º>");
		scanf("%d", &input);
		switch (input){
			case 1:
				game();
				break;
			case 0:
				break;
			default:
				printf("ÊäÈë´íÎó\n");
				break;
		}
	} while (input);

	return 0;
}