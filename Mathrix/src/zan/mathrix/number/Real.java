package zan.mathrix.number;

import zan.mathrix.elem.FieldElem;
import zan.mathrix.elem.MetricElem;
import zan.mathrix.elem.OrderedElem;
import zan.mathrix.elem.SignedElem;
import zan.mathrix.util.MathUtil;

/** Real numbers */
public final class Real implements FieldElem<Real>, MetricElem<Real>, OrderedElem<Real>, SignedElem<Real> {

	public static final Real zero = new Real(0.0);
	public static final Real one = new Real(1.0);
	public static final Real minusOne = new Real(-1.0);

	private final double value;

	public Real(double value) {
		this.value = value;
	}

	public double value() {
		return value;
	}

	public Real sqrt() {
		return new Real(Math.sqrt(value));
	}

	public Complex toComplex() {
		return new Complex(value, 0.0);
	}

	@Override
	public Real abs() {
		return new Real(Math.abs(value));
	}

	@Override
	public Real add(Real elem) {
		return new Real(value + elem.value);
	}

	@Override
	public Real addId() {
		return zero;
	}

	@Override
	public Real addInv() {
		return new Real(-value);
	}

	@Override
	public Real mul(Real elem) {
		return new Real(value * elem.value);
	}

	@Override
	public Real mulId() {
		return one;
	}

	@Override
	public Real mulInv() {
		return new Real(1.0 / value);
	}

	@Override
	public Int efn() {
		return is(addId())?Int.zero:Int.one;
	}

	@Override
	public Real quo(Real elem) {
		return elem.is(addId())?null:mulInv();
	}

	@Override
	public Real rem(Real elem) {
		return elem.is(addId())?null:addId();
	}

	@Override
	public Real dist(Real elem) {
		return add(elem.addInv()).abs();
	}

	@Override
	public boolean leq(Real elem) {
		return (value <= elem.value);
	}

	@Override
	public boolean geq(Real elem) {
		return (value >= elem.value);
	}

	@Override
	public Int sgn() {
		if (value > 0) return Int.one;
		else if (value < 0) return Int.minusOne;
		return Int.zero;
	}

	@Override
	public boolean is(Real elem) {
		return (Math.abs(value - elem.value) < MathUtil.error);
	}

	@Override
	public String toString() {
		return Double.toString(Math.round(value * MathUtil.round) / MathUtil.round);
	}

}
