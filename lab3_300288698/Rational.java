public class Rational {

    private int numerator;
    private int denominator;

    // constructors

    public Rational(int numerator) {
	     this.numerator = numerator;
       this.denominator = 1;
       this.reduce();
    }

    public Rational(int numerator, int denominator) {
	     this.numerator = numerator;
       this.denominator = denominator;
       this.reduce();
    }

    // getters

    public int getNumerator() {
	     return numerator;
    }

    public int getDenominator() {
	     return denominator;
    }

    // instance methods

    public Rational plus(Rational other) {
      int added = (other.denominator*this.numerator) + (other.numerator*this.denominator);
      int under = (other.denominator*this.denominator);
      Rational sum = new Rational(added, under);
      return sum;
    }

    public static Rational plus(Rational a, Rational b) {
    	int added = (a.denominator*b.numerator) + (a.numerator*b.denominator);
      int under = (a.denominator*b.denominator);
      Rational sum = new Rational(added, under);
      return sum;
    }

    // Transforms this number into its reduced form

    private void reduce() {

      if (this.numerator < 0 && this.denominator < 0){
        this.numerator = 0 - this.numerator;
        this.denominator = 0 - this.denominator;
      }

      if (this.numerator > this.denominator){
        for (int i = this.denominator; i > 0; i--){
          if (this.denominator%i == 0 && this.numerator%i == 0){
            this.denominator = this.denominator/i;
            this.numerator = this.numerator/i;
          }
        }
      }
      else{
        for (int i = this.numerator; i > 0; i--){
          if (this.denominator%i == 0 && this.numerator%i == 0){
            this.denominator = this.denominator/i;
            this.numerator = this.numerator/i;
          }
        }
      }

    }

    // Euclid's algorithm for calculating the greatest common divisor
    private int gcd(int a, int b) {
      // Note that the loop below, as-is, will time out on negative inputs.
      // The gcd should always be a positive number.
      // Add code here to pre-process the inputs so this doesn't happen.

      //CHECK IF THIS IS WHAT'S WANTED
      if (a < 0){
        a = 0-a;
      } 
      if (b < 0){
        b = 0-a;
      }

    	while (a != b)
    	    if (a > b)
    		     a = a - b;
    	    else
    		     b = b - a;
    	return a;
    }

    public int compareTo(Rational other) {
      int num1 = this.numerator*other.denominator;
      int num2 = other.numerator*this.denominator;

      if (num1 > num2){
        return 1;
      }
      else if (num1 < num2){
        return -1;
      }
      return 0;
    }

    public boolean equals(Rational other) {
      if (this.numerator == other.numerator && this.denominator == other.denominator){
        return true;
      }
      return false;
    }

    public String toString() {
    	String result;
    	if (denominator == 1) {
    	  result = String.valueOf(numerator);
    	} else {
        result = String.valueOf(numerator) + "/" + String.valueOf(denominator);
    	}
    	return result;
    }

}