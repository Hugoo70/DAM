#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>

int main(){
	pid_t pid;
	
	pid = fork();
	
	if(pid==0){//Proceso Hijo
		printf("Hola, soy el proceso hijo con PID:%d y PPID: %d",getpid(),getppid());
		exit(0);
		wait(NULL);
	}else{//Proceso Padre
		printf("Hola, soy el proceso padre con PID:%d y PPID:%d.\nHe creado un proceso con PID %d\n", getpid(), getppid(), pid);
		
	}
	wait(NULL);
}
