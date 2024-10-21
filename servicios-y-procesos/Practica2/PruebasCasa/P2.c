#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>


// Prueba de Hijos Huerfanos y Zombies
int main() {
    pid_t pid, pid2;

    // Crear el primer hijo
    pid = fork();
    if (pid == 0) {
        // Este es el proceso hijo (huérfano)
        sleep(2); // Esperamos para asegurarnos de que el padre termine antes
        printf("Hijo huérfano:\nPID: %d\t PPID (padre adoptivo): %d\n", getpid(), getppid());
        exit(0); // Termina normalmente
    } else {
        // Este es el proceso padre
        printf("Padre:\nPID: %d\t PPID: %d\n", getpid(), getppid());

        // Crear el segundo hijo (zombie)
        pid2 = fork();
        if (pid2 == 0) {
            // Este es el proceso hijo (zombie)
            printf("Hijo zombie:\nPID: %d\t PPID: %d\n", getpid(), getppid());
            exit(0); // Termina sin que el padre lo espere
        }

        // Padre termina sin esperar a los hijos
        // Le damos tiempo al hijo zombie para que termine
        printf("Padre termina sin hacer wait() para hijo zombie\n");
        exit(0); // Termina el proceso padre
    }

    return 0;
}

