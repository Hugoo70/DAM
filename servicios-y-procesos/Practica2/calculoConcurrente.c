#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>

#define Max_Primo 10
#define Max_Impar 5

// Función que nos dice si un número es primo o no
int esPrimo(int num){
	if(num==1)
	return 0;
 	for(int i = 2; i <num; i++){
	 	if(num % i == 0)
		return 0;
 	}
  return 1;
 }
 int impar(int num){
 	return num%2;
 }


int main() {

	pid_t pid1, pid2;
	
	
	pid1 = fork();
	
	if(pid1==0){
	    unsigned long long int sumaPrimo = 0;
	        for(int i = 2; i<=Max_Primo;i++){
    	if(esPrimo(i)){
    	sumaPrimo +=i;
    	}
    }
    printf("Resultado primos con PID%d: %llu\n",getpid(), sumaPrimo);
    exit(1);
	}
	
	pid2 = fork();
	if(pid2==0){
	    unsigned long long int sumaImp = 0;    


    
    	for(int i = 1; i <=Max_Impar; i+=2){
    		if(impar(i)){
		sumaImp += i;
		}
	}
        printf("Resultado impares con PID%d: %llu\n",getpid(), sumaImp);
        exit(2);
	}else{
		wait(NULL);
		wait(NULL);
		printf("Soy el padre he creado dos hijos con los pid's: %d y %d\n", pid1, pid2);
	}
  
        return 0;

}


