public class Iterative {

	public static BitList complement( BitList in ) {
        BitList result = new BitList();

        Iterator it = in.iterator();
        Iterator other = result.iterator();

        while (it.hasNext()){
            int bit = it.next();

            if(bit == BitList.ONE){
                other.add(BitList.ZERO);
            }
            else if(bit == BitList.ZERO){
                other.add(BitList.ONE);
            }
        }

        return result;
	}

	public static BitList or( BitList a, BitList b ) {
        BitList result = new BitList();

        Iterator one = a.iterator();
        Iterator two = b.iterator();
        Iterator three = result.iterator();

        if(!one.hasNext()){
            throw new IllegalArgumentException("a is empty");
        }
        if(!two.hasNext()){
            throw new IllegalArgumentException("b is empty");
        }
        while(one.hasNext()){
            if(!two.hasNext()){
                throw new IllegalArgumentException("a and b are not the same size");
            }
            int bitOne = one.next();
            int bitTwo = two.next();

            if(bitOne == BitList.ONE || bitTwo == BitList.ONE){
                three.add(BitList.ONE);
            }
            else{
                three.add(BitList.ZERO);
            }
        }
        if(two.hasNext()){
            throw new IllegalArgumentException("a and b are not the same size");
        }
        return result;
	}

	public static BitList and( BitList a, BitList b ) {
        BitList result = new BitList();

        Iterator one = a.iterator();
        Iterator two = b.iterator();
        Iterator three = result.iterator();

        if(!one.hasNext()){
            throw new IllegalArgumentException("a is empty");
        }
        if(!two.hasNext()){
            throw new IllegalArgumentException("b is empty");
        }

        while(one.hasNext()){
            if(!two.hasNext()){
                throw new IllegalArgumentException("a and b are not the same size");
            }

            int bitOne = one.next();
            int bitTwo = two.next();

            if(bitOne == BitList.ZERO || bitTwo == BitList.ZERO){
                three.add(BitList.ZERO);
            }
            else{
                three.add(BitList.ONE);
            }
        }
        if(two.hasNext()){
            throw new IllegalArgumentException("a and b are not the same size");
        }

        return result;
	}

	public static BitList xor( BitList a, BitList b ) {
        BitList result = new BitList();

        Iterator one = a.iterator();
        Iterator two = b.iterator();
        Iterator three = result.iterator();

        if(!one.hasNext()){
            throw new IllegalArgumentException("a is empty");
        }
        if(!two.hasNext()){
            throw new IllegalArgumentException("b is empty");
        }

        while(one.hasNext()){
            if(!two.hasNext()){
                throw new IllegalArgumentException("a and b are not the same size");
            }

            int bitOne = one.next();
            int bitTwo = two.next();

            if(bitOne == bitTwo){
                three.add(BitList.ZERO);
            }
            else{
                three.add(BitList.ONE);
            }
        }
        if(two.hasNext()){
            throw new IllegalArgumentException("a and b are not the same size");
        }
        
        return result;
    }
}