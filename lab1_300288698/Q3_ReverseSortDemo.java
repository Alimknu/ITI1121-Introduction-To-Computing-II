public class Q3_ReverseSortDemo {
	public static void main(String[] args){
		char[] unorderedLetters;
		unorderedLetters = new char[]{'b', 'm', 'z', 'a', 'u'};
		reverseSort(unorderedLetters);
		for (int i = 0 ; i < unorderedLetters.length; i++ )
			System.out.print(unorderedLetters[i]);
	}

	public static void reverseSort(char[] values){
		for (int i = 0; i < values.length; i++){
			for (int j = i; j < values.length; j++){
				if (values[i] < values[j]){
					char temp = values[j];
					values[j] = values[i];
					values[i] = temp;
				}
			}
		}
	}

}