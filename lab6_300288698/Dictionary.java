public class Dictionary implements Map<String, Integer> {

    private final static int INITIAL_CAPACITY = 10;
    private final static int INCREMENT = 5;
    private int count;

    private Pair[] elems;

    public int getCount() {
      return count;
    }

    public int getCapacity() {
      return elems.length;
    }

    public Dictionary() {
        elems = new Pair[INITIAL_CAPACITY];
        count = 0;
    }

    @Override
    public void put(String key, Integer value) {
        if (count == elems.length){
            increaseCapacity();
        }
        Pair pair = new Pair(key, value);
        elems[count++] = pair;
    }

    private void increaseCapacity() {
       Pair[] temp = new Pair[count + INCREMENT];
       for (int i = 0; i < elems.length; i++){
        temp[i] = elems[i];
       }
       elems = temp;
    }

    @Override
    public boolean contains(String key) {
        if (count == 0){
            return false;
        }

        for (int i = 0; i < count; i++){
            if (elems[i].getKey() == key){
                return true;
            }
        }
        
        return false;
    }

    @Override
    public Integer get(String key) {
        int x = 0;
        for (int i = 0; i < count; i++){
            if (elems[i].getKey() == key){
                x = elems[i].getValue();
            }
        }
        return Integer.valueOf(x);
    }

    @Override
    public void replace(String key, Integer value) {
        for (int i = 0; i < count; i++){
            if (elems[i].getKey() == key){
                elems[i].setValue(value);
            }
        }
    }

    @Override
    public Integer remove(String key) {
        int x = 0;
        int index = 0;
        if (contains(key) == true){
            x = get(key);
            for (int i = 0; i < count; i++){
                if (elems[i].getKey() == key){
                    index = i;
                }
            }

            for (int j = index; j < count-1; j++){
                elems[j] = elems[j+1];
            }

            elems[elems.length-1] = null;
            count = count - 1;
        }
        return Integer.valueOf(x);
    }

    @Override
    public String toString() {
      String res;
      res = "Dictionary: {elems = [";
      for (int i = count-1; i >= 0 ; i--) {
          res += elems[i];
          if(i > 0) {
              res += ", ";
          }
      }
      return res +"]}";
    }

}