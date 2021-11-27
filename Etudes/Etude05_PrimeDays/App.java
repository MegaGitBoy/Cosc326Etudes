import java.util.*;

class App {
	
  	public static boolean isPrime(int num) {
  		if (num <= 3) return num > 1;
  
  		if ((num % 2 == 0) || (num % 3 == 0)) return false;
  
  		int count = 5;
  
  		while (Math.pow(count, 2) <= num) {
    		if (num % count == 0 || num % (count + 2) == 0) return false;
    
    		count += 6;
  		}
  
  		return true;
	}
	
	
	public static void main(String[] args){
		int month[] = new int[args.length];
		int monthArray[] = new int[args.length];
		for(int i = 0; i < args.length; i++){
			month[i] = Integer.parseInt(args[i]);
			if(i != 0){
				monthArray[i] = Integer.parseInt(args[i]) + monthArray[i-1];
			}else{
				monthArray[i] = Integer.parseInt(args[i]);
			}
			
		}
		

		for(int i = 0; i < monthArray.length; i++){
			if(isPrime(i + 1)){
			
				
				for(int j = 1; j < month[i] + 1 ; j++){
					if(isPrime(j)){
						if(isPrime(monthArray[i - 1] + j)){
							System.out.print(monthArray[i - 1] + j + ": ");
							System.out.print(i + 1 + " ");
							System.out.print(j);
							System.out.println("");
						}
						
					
					
					}
				
				}
			
			
			}
		}
	}
	
};