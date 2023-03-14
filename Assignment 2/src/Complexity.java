// Aditi Deshmukh
// I pledge my honor that I have abided by the Stevens Honor System. 
public class Complexity {

	// method that has complexity n^2 
		//EDIT
		public static void method1(int n) {
			int accumulator = 0; // accumulator
			for (int i = 0; i<n; i++){
				for(int j = 0; j< n; j ++) {
					System.out.println("Operation " + accumulator);
					accumulator ++; 
				}
			}
			
		}
		
		//method that has complexity n^3
		public static void method2(int n) {
			int accumulator = 0; // accumulator
			for (int i = 0; i<n; i++){
				for(int j = 0; j< n; j ++) {
					for (int k = 0; k < n; k ++) {
						System.out.println("Operation " + accumulator);
						accumulator ++; 
					}
				}
			}
			
		}
		
		
		//method that has complexity log n 
		public static void method3(int n) {
			int accumulator = 0; // accumulator
			for (int i = 1; i<n; i*=2){
				System.out.println("Operation " + accumulator);
				accumulator ++; 
			}
			
		}
		
		
		/** Question 4 Part 1
		 Iteration | start | end
		 -----------------------
		      1    |   0   | 31
		      2    |   16  | 31
		      3    |   24  | 31
		      4    |   28  | 31
		      5    |   30  | 31
		      6    |   31  | 31
		      
		     Question 4 Part 2
		 Iteration | start | end
		 -----------------------
		      1    |   0   | 63
		      2    |   32  | 63
		      3    |   48  | 63
		      4    |   56  | 63
		      5    |   60  | 63
		      6    |   62  | 63
		      7    |   63  | 63
		    
		 */
		
		// Question 5 
				// log base 2 of input
		// Question 6
				// O(log n) 
		// Question 7 - double check 
		public static void method4(int n) {
			int accumulator = 0; // accumulator
			for (int i = 1; i<n; i*=2){
				for (int j = 0; j < n; j++)
				System.out.println("Operation " + accumulator);
				accumulator ++; 
			}
		}
		// Question 8
		public static void method5(int n) {
			int accumulator = 0; // accumulator
			for (int i = 1; i<n; i*=i){
				for (int j = 0; j < n; j*=2)
				System.out.println("Operation " + accumulator);
				accumulator ++; 
			}
		}
		// Question 9
		public static int method6(int n) {
			if (n <= 2) {
				return 0;
			} else {
				return method6(n-1) + method6(n-2);
			}
		}
		
		
		public static void main(String[] args) {
			method3(9);
		}
		



}
