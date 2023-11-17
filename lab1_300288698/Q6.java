import java.util.Scanner;

public class Q6{
	public static void main(String[] args){
        double[] values = new double[10];
		for (int i = 0; i < 10; i++){
            System.out.println("Enter the grade of student " + (i+1) + " : ");
            Scanner scan = new Scanner(System.in);
            double val = scan.nextDouble();
            values[i] = val;
        }
        /*This was for code testing
        double avg = calculateAverage(values);
        System.out.println("The calculated average: " + avg);
        double med = calculateMedian(values);
        System.out.println("The calculated median: " + med);
        int fail = calculateNumberFailed(values);
        System.out.println("The number of students that failed: " + fail);
        int passed = calculateNumberPassed(values);
        System.out.println("The number of students that passed: " + passed);*/
	}

	public static double calculateAverage(double[] notes){
		double sum = 0;
        for (int i = 0; i < notes.length; i++){
            sum = sum + notes[i];
        }
        double average = sum/(notes.length);
        return average;
	}

	public static double calculateMedian(double[] notes){
		double median = 0;
            for (int i = 0; i < notes.length; i++){
                for (int j = i + 1; j < notes.length; j++){
                    if (notes[i] > notes[j]){
                        double temp = notes[i];
                        notes[i] = notes[j];
                        notes[j] = temp;
                }
                }
            }
        if (notes.length % 2 == 0){//if there's 2 middle values
            int mid1 = notes.length/2 - 1;
            int mid2 = notes.length/2;
            median = notes[mid1] + notes[mid2];
            median = median/2;
        }
        else {//if there's 1 middle value
            int mid = notes.length/2;
            median = notes[mid];
        }
        return median;
	}

	public static int calculateNumberFailed(double[] notes){
		int failed = 0;
        for (int i = 0; i < notes.length; i++){
            if (notes[i] < 50.0){
                failed++;
            }
        }
        return failed;
	}

	public static int calculateNumberPassed(double[] notes){
		int passed = 0;
        for (int i = 0; i < notes.length; i++){
            if (notes[i] >= 50.0){
                passed++;
            }
        }
        return passed;
	}

}