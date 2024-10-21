#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    pid_t pid, pid2, pid3, pid4;
    int status;

    printf("Padre:\nPID: %d\t PPID: %d\n", getpid(), getppid());

    // Hijo1
    pid = fork();
    if (pid == 0) {
        printf("Hijo1:\nPID:%d\t PPID:%d\n", getpid(), getppid());
        exit(0); // El Hijo1 termina con estado 0
    } else {
        waitpid(pid, &status, 0); // El padre espera al Hijo1
        if (WIFEXITED(status)) {
            printf("Hijo1 terminó con status: %d\n", WEXITSTATUS(status));
        }
    }

    // Hijo2
    pid2 = fork();
    if (pid2 == 0) {
        printf("Soy el Hijo2, mi PID: %d\t PPID: %d\n", getpid(), getppid());

        // Nieto1
        pid3 = fork();
        if (pid3 == 0) {
            printf("Nieto1:\nPID:%d\t PPID:%d\n", getpid(), getppid());
            exit(2); // El Nieto1 termina con estado 2
        }

        // Nieto2
        pid4 = fork();
        if (pid4 == 0) {
            printf("Nieto2:\nPID:%d\t PPID:%d\n", getpid(), getppid());
            exit(2); // El Nieto2 termina con estado 2
        }

        // Esperamos a Nieto1 y Nieto2
        waitpid(pid3, &status, 0);
        if (WIFEXITED(status)) {
            printf("Nieto1 terminó con status: %d\n", WEXITSTATUS(status));
        }

        waitpid(pid4, &status, 0);
        if (WIFEXITED(status)) {
            printf("Nieto2 terminó con status: %d\n", WEXITSTATUS(status));
        }

        printf("Hijo2:\nEl PID de mis hijos (Nietos): %d, %d\n", pid3, pid4);
        exit(1); // El Hijo2 termina con estado 1
    }

    // El padre espera a Hijo2
    waitpid(pid2, &status, 0);
    if (WIFEXITED(status)) {
        printf("Hijo2 terminó con status: %d\n", WEXITSTATUS(status));
    }

    return 0;
}

