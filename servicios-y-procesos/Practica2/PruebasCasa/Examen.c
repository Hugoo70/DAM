#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>

int esPrimo(int num){
	if(num==1)
		return 0;
	for(int i = 2;i<num;i++){
		if(num%i==0){
			return 0;
		}
	}
return 1;

}

int esImpar(int num){
	return num%2;
}

int main(int argc, char *argv[]){
	
	pid_t pid1, pid2;
	
	
	
	printf("Soy el padre, mi PID es: %d\n", getpid());
	pid1=fork();
	if(pid1==-1){
		printf("No se ejecuto bien el Hijo1");
		return 1;
	}
	if(pid1==0){
		printf("Soy el Hijo 1 voy a mostrar los primos,\t mi PID es: %d\t mi PPID es: %d\n", getpid(), getppid());
		int sumaPrimo = 0;
		for(int i = 0; i<atoi(argv[1]);i++){
			if(esPrimo(i))
			sumaPrimo += i;
		}
		printf("La suma de los primos es: %d\n", sumaPrimo);
		exit(0);
	}
	
		pid2=fork();
		if(pid2==-1){
		printf("No se ejecuto bien el Hijo2\n");
		return 1;
	}
	if(pid2==0){
		printf("Soy el Hijo 2 voy a mostrar los impares,\t mi PID es: %d\t mi PPID es: %d\n", getpid(), getppid());
		
		int sumaImpar = 0;
		for(int i = 0; i<atoi(argv[2]);i++)
			if(esImpar(i)){
			sumaImpar += i;
		}
		printf("La suma de los impares es: %d\n", sumaImpar);
		
		exit(0);
	}
	else // Nos encontramos en el proceso padre                                         
        {
		int status;
		pid_t proceso_terminado;
		for (int i=0;i<2;i++){
                proceso_terminado = wait(&status);
		if (proceso_terminado == pid1){
			printf("Terminó el de los primos\n");
                	if (WIFEXITED(status)) {
				printf("Proceso hijo terminó con estado %d.\n", WEXITSTATUS(status));
			} else {
				printf("El proceso hijo terminó de manera anormal.\n");                 
                	}
		} else if (proceso_terminado == pid2){
			printf("Terminó el de los impares\n");
                	if (WIFEXITED(status)) {
				printf("Proceso hijo terminó con estado %d.\n", WEXITSTATUS(status));
			} else {
				printf("El proceso hijo terminó de manera anormal.\n");                 
                	}
		}else{
			printf("No sé cuál ha terminado...\n");
		}
		}
                exit(0);                                                                    
        }       

}
