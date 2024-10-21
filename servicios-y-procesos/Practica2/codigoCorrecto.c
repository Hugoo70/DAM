#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
//Faltan estas dos librerias
#include <sys/wait.h>
#include <sys/types.h>

	int main() {
 		// Cambio del int por un pid_t
 		pid_t pid;
 		// Crear un proceso hijo
 		pid = fork();
 		if (pid == -1) {
 			// Error al crear el proceso hijo
 			perror("fork failed");
 			exit(1);
 		}

		
 		if (pid == 0) {
 			// He quitado varios "\" dentro de los printf's
 			printf("Soy el proceso hijo, mi PID es %d\n", getpid());
			printf("Estoy bien, gracias por preguntar");
 		}else{
 			printf("Soy el proceso padre, mi PID es %d\n", getpid());
 			//Pongo este texto que estaba encima del if dentro del else
 			printf("Hola, ¿qué tal? Todo bien, gracias.\n");
			wait(NULL);
		}
			return 0;
	}
