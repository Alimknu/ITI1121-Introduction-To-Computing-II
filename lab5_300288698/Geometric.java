public class Geometric extends AbstractSeries {

    // instance variables

    double s0 = 0;
    double increment = 1;
    
    public double next() {

        s0 = s0 + increment;

        increment = increment/2;

        return s0;

    }
}