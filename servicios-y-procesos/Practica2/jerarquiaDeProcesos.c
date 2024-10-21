#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>

int main(){
    pid_t pid, pid2, pid3, pid4;
    
    printf("Padre:\nPID: %d\t PPID: %d\n", getpid(), getppid());
    
    pid = fork();
    if (pid == 0) {

        printf("Hijo1:\nPID:%d\t PPID:%d\n", getpid(), getppid());
        exit(0);
    } else {
         
    }
    
    pid2 = fork();
    if (pid2 == 0) {

        printf("Soy el Hijo2, mi PID: %d\t PPID: %d\n", getpid(), getppid());

        // Nieto1
        pid3 = fork();
        if (pid3 == 0) {
            printf("Nieto1:\nPID:%d\t PPID:%d\n", getpid(), getppid());
            exit(2);
        }

        // Nieto2
        pid4 = fork();
        if (pid4 == 0) {
            printf("Nieto2:\nPID:%d\t PPID:%d\n", getpid(), getppid());
            exit(2);
        }

        wait(NULL);
        wait(NULL);
	printf("Hijo2:\nEl PID de mis hijos (Nietos): %d, %d\n", pid3, pid4);
        exit(1);
    }

    wait(NULL); 
    wait(NULL);
    return 0;
}

