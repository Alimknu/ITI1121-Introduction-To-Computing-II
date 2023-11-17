public class ITIStringBuffer {

    private SinglyLinkedList<String> list;
    private int length;

    public ITIStringBuffer() {
        list = new SinglyLinkedList<>();
        length = 0;
    }

    public ITIStringBuffer(String  firstString){
        this();
        list.addFirst(firstString);
        length = firstString.length();
    }

    public void append(String nextString){
        list.add(nextString);
        length += nextString.length();
    }

    public String toString(){
        char result[] = new char[length];
        int i = 0;
        for(String s: list){
            for(char c: s.toCharArray()){
                result[i++] = c;
            }
        }
        return new String(result);
    }

}
