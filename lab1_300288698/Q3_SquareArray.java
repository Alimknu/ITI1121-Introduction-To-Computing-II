public class Q3_SquareArray{

	public static int[] createArray(int size){
		int[] array;
		array = new int[size];
		for (int i = 0; i < size; i++){
				array[i] = i*i;
		}
		return array;
	}
	public static void main(String[] args){
		int[] mainArray = createArray(13);
		for (int i = 0; i < mainArray.length; i++){
			System.out.println("The square of " + i + " is:  " + mainArray[i]);
		}
	}
}