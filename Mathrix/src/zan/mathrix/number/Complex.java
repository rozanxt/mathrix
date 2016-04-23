package zan.mathrix.number;

import zan.mathrix.elem.FieldElem;
import zan.mathrix.elem.MetricElem;
import zan.mathrix.util.MathUtil;

/** Complex numbers */
public final class Complex implements FieldElem<Complex>, MetricElem<Complex> {

	public static final Complex zero = new Complex(0.0, 0.0);
	public static final Complex one = new Complex(1.0, 0.0);
	public static final Complex minusOne = new Complex(-1.0, 0.0);
	public static final Complex i = new Complex(0.0, 1.0);

	private final double real, imag;

	public Complex(double real, double imag) {
		this.real = real;
		this.imag = imag;
	}

	public double real() {
		return real;
	}

	public double imag() {
		return imag;
	}

	public Real toReal() {
		return new Real(real);
	}

	public Real toImag() {
		return new Real(imag);
	}

	public Complex abs() {
		return new Complex(Math.sqrt(real * real + imag * imag), 0.0);
	}

	public Complex abs2() {
		return new Complex(real * real + imag * imag, 0.0);
	}

	public Complex conj() {
		return new Complex(real, -imag);
	}

	@Override
	public Complex add(Complex elem) {
		return new Complex(real + elem.real, imag + elem.imag);
	}

	@Override
	public Complex addId() {
		return zero;
	}

	@Override
	public Complex addInv() {
		return new Complex(-real, -imag);
	}

	@Override
	public Complex mul(Complex elem) {
		return new Complex(real * elem.real - imag * elem.imag, real * elem.imag + elem.real * imag);
	}

	@Override
	public Complex mulId() {
		return one;
	}

	@Override
	public Complex mulInv() {
		return new Complex(real / abs2().real, -imag / abs2().real);
	}

	@Override
	public Int efn() {
		return is(addId())?Int.zero:Int.one;
	}

	@Override
	public Complex quo(Complex elem) {
		return elem.is(addId())?null:mulInv();
	}

	@Override
	public Complex rem(Complex elem) {
		return elem.is(addId())?null:addId();
	}

	@Override
	public Real dist(Complex elem) {
		return add(elem.addInv()).abs().toReal();
	}

	@Override
	public boolean is(Complex elem) {
		return (Math.abs(real - elem.real) < MathUtil.error && Math.abs(imag - elem.imag) < MathUtil.error);
	}

	@Override
	public String toString() {
		double re = Math.round(real * MathUtil.round) / MathUtil.round;
		double im = Math.round(imag * MathUtil.round) / MathUtil.round;
		if (im < 0.0) return re + "-" + Math.abs(im) + "i";
		return re + "+" + Math.abs(im) + "i";
	}

}
