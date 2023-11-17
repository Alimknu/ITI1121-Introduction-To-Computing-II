public class Q3_ArrayInsertionDemo {

    public static int[] insertIntoArray(int[] beforeArray, int indexToInsert, int valueToInsert){
        int[] afterArray = new int[(beforeArray.length + 1)];
        for (int i = 0; i < (beforeArray.length+1); i++){
            if (i == indexToInsert){
                afterArray[i] = valueToInsert;
            }
            else if (i < indexToInsert){
                afterArray[i] = beforeArray[i];
            }
            else{
                afterArray[i] = beforeArray[i-1];
            }
        }
        return afterArray;
	}

	public static void main(String[] args){
        int index = 3;
        int value = 15;
		int[] array = new int[]{ 1,5,4,7,9,6};
        int[] newArray = insertIntoArray(array, index, value);
        System.out.println("Array before insertion:");
        for (int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
        System.out.println("Array after insertion of " + value + " at position " + index);
        for (int i = 0; i < newArray.length; i++){
            System.out.println(newArray[i]);
        }
	}
}