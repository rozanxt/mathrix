package zan.mathrix.number;

import zan.mathrix.elem.EuclideanRingElem;
import zan.mathrix.elem.MetricElem;
import zan.mathrix.elem.OrderedElem;
import zan.mathrix.elem.SignedElem;

/** Integer numbers */
public final class Int implements EuclideanRingElem<Int>, MetricElem<Int>, OrderedElem<Int>, SignedElem<Int> {

	public static final Int zero = new Int(0);
	public static final Int one = new Int(1);
	public static final Int minusOne = new Int(-1);

	private final int value;

	public Int(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}

	public ModInt toModInt(int mod) {
		return new ModInt(mod, value);
	}

	public Ratio toRatio() {
		return new Ratio(value, 1);
	}

	public Real toReal() {
		return new Real(value);
	}

	public Complex toComplex() {
		return new Complex(value, 0);
	}

	@Override
	public Int abs() {
		return new Int(Math.abs(value));
	}

	@Override
	public Int add(Int elem) {
		return new Int(value + elem.value);
	}

	@Override
	public Int addId() {
		return zero;
	}

	@Override
	public Int addInv() {
		return new Int(-value);
	}

	@Override
	public Int mul(Int elem) {
		return new Int(value * elem.value);
	}

	@Override
	public Int mulId() {
		return one;
	}

	@Override
	public Int mulInv() {
		switch (value) {
		case 1:
			return one;
		case -1:
			return one.addInv();
		default:
			return null;
		}
	}

	@Override
	public Int efn() {
		return abs();
	}

	@Override
	public Int quo(Int elem) {
		return new Int(value / elem.value);
	}

	@Override
	public Int rem(Int elem) {
		return new Int(value % elem.value);
	}

	@Override
	public Real dist(Int elem) {
		return new Real(add(elem.addInv()).abs().value);
	}

	@Override
	public boolean leq(Int elem) {
		return (value <= elem.value);
	}

	@Override
	public boolean geq(Int elem) {
		return (value >= elem.value);
	}

	@Override
	public Int sgn() {
		if (value > 0) return one;
		else if (value < 0) return minusOne;
		return zero;
	}

	@Override
	public boolean is(Int elem) {
		return (value == elem.value);
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}

}
