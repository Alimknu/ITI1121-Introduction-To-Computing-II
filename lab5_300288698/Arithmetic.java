public class Arithmetic extends AbstractSeries {

    // instance variables

    double s0 = 0;
    double increment = 0;

    public double next() {

        increment = increment + 1;

        s0 = s0 + increment;
        
        return s0;
    }
}