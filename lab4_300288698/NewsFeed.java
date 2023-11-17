/* *
 * Use static array for NewsFeed
 * with constant MAX_SIZE
 * */

public class NewsFeed {

    private Post[] messages;
    private int size;
    public static final int MAX_SIZE = 25;

    public NewsFeed() {
    	Post x[] = new Post[MAX_SIZE];
		this.messages = x;
		this.size = 0;
    }

    public void add(Post message) {
		if (size < 25){
			messages[size] = message;
			size++;
		}
    }

    public Post get(int index) {
		return messages[index];
    }

    public int size() {
	     return size;
    }

	public void sort(){
		int i, j, argMin;
		Post tmp;
		for (i = 0; i < size - 1; i++) {
			argMin = i;
			for (j = i + 1; j < size(); j++) {
				if (messages[j].compareTo(messages[argMin]) < 0) {
					argMin = j;
				}
			}

  			tmp = messages[argMin];
  			messages[argMin] = messages[i];
  			messages[i] = tmp;
		  }
	}

  	public NewsFeed getPhotoPost(){
  		NewsFeed nouveaux = new NewsFeed();
		for (int i = 0; i < MAX_SIZE; i++){
			if (messages[i] instanceof PhotoPost){
				nouveaux.add(messages[i]);
			}
		}
		return nouveaux;
  	}

  	public NewsFeed plus(NewsFeed other){
		NewsFeed nouveaux = new NewsFeed();
		for (int i = 0; i < MAX_SIZE; i++){
			if (messages[i] != null){
				nouveaux.add(messages[i]);
			}
			if (other.messages[i] != null){
				nouveaux.add(other.messages[i]);
			}
		}
		nouveaux.sort();
		return nouveaux;
  	}

}
