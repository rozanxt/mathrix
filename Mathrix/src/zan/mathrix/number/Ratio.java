package zan.mathrix.number;

import zan.mathrix.elem.FieldElem;
import zan.mathrix.elem.MetricElem;
import zan.mathrix.elem.OrderedElem;
import zan.mathrix.elem.SignedElem;
import zan.mathrix.util.MathUtil;

/** Rational numbers */
public final class Ratio implements FieldElem<Ratio>, MetricElem<Ratio>, OrderedElem<Ratio>, SignedElem<Ratio> {

	public static final Ratio zero = new Ratio(0, 1);
	public static final Ratio one = new Ratio(1, 1);

	private final int numerator, denominator;

	public Ratio(int numerator, int denominator) {
		if (denominator == 0) {
			numerator = 0;
		} else if (numerator == 0) {
			denominator = 1;
		} else if (denominator < 0) {
			numerator = -numerator;
			denominator = -denominator;
		}
		int gcd = MathUtil.GCD(new Int(numerator), new Int(denominator)).abs().value();
		this.numerator = numerator / gcd;
		this.denominator = denominator / gcd;
	}

	public int numerator() {
		return numerator;
	}

	public int denominator() {
		return denominator;
	}

	public Real toReal() {
		return new Real(numerator / (double)denominator);
	}

	@Override
	public Ratio abs() {
		return new Ratio(Math.abs(numerator), Math.abs(denominator));
	}

	@Override
	public Ratio add(Ratio elem) {
		return new Ratio(numerator * elem.denominator + elem.numerator * denominator, denominator * elem.denominator);
	}

	@Override
	public Ratio addId() {
		return zero;
	}

	@Override
	public Ratio addInv() {
		return new Ratio(-numerator, denominator);
	}

	@Override
	public Ratio mul(Ratio elem) {
		return new Ratio(numerator * elem.numerator, denominator * elem.denominator);
	}

	@Override
	public Ratio mulId() {
		return one;
	}

	@Override
	public Ratio mulInv() {
		return new Ratio(denominator, numerator);
	}

	@Override
	public Int efn() {
		return is(addId())?Int.zero:Int.one;
	}

	@Override
	public Ratio quo(Ratio elem) {
		return elem.is(addId())?null:mulInv();
	}

	@Override
	public Ratio rem(Ratio elem) {
		return elem.is(addId())?null:addId();
	}

	@Override
	public Real dist(Ratio elem) {
		Ratio dist = add(elem.addInv()).abs();
		return new Real(dist.numerator / dist.denominator);
	}

	@Override
	public boolean leq(Ratio elem) {
		return (numerator * elem.denominator <= elem.numerator * denominator);
	}

	@Override
	public boolean geq(Ratio elem) {
		return (numerator * elem.denominator >= elem.numerator * denominator);
	}

	@Override
	public Int sgn() {
		if (numerator == 0) return Int.zero;
		else if ((numerator > 0 && denominator > 0) || (numerator < 0 && denominator < 0)) return Int.one;
		return Int.minusOne;
	}

	@Override
	public boolean is(Ratio elem) {
		return (numerator == elem.numerator && denominator == elem.denominator);
	}

	@Override
	public String toString() {
		return numerator + "/" + denominator;
	}

}
