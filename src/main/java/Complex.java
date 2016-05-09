public class Complex {
    private final double real;
    private final double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public Complex plus(Complex another){
        double real = this.real + another.real;
        double imaginary = this.imaginary + another.imaginary;
        return new Complex(real, imaginary);
    }

    public Complex minus(Complex another){
        double real = this.real - another.real;
        double imaginary = this.imaginary - another.imaginary;
        return new Complex(real, imaginary);
    }

    public Complex times(Complex another){
        double real = this.real * another.real - this.imaginary * another.imaginary;
        double imaginary = this.real * another.imaginary + this.imaginary * another.real;
        return new Complex(real, imaginary);
    }
}
