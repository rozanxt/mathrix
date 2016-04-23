package zan.mathrix.num;

import zan.mathrix.elem.FieldElem;
import zan.mathrix.util.MathUtil;

/** Integer modulo numbers */
public final class ModInt implements FieldElem<ModInt> {	// TODO not a Field if mod is not a prime number

	private final int mod;
	private final int value;

	public ModInt(int mod, int value) {
		value %= mod;
		if (value < 0) value += mod;
		this.mod = mod;
		this.value = value;
	}

	public int mod() {
		return mod;
	}

	public int value() {
		return value;
	}

	public Int toInt() {
		return new Int(value);
	}

	@Override
	public ModInt add(ModInt elem) {
		if (mod == elem.mod) return new ModInt(mod, value + elem.value);
		return null;
	}

	@Override
	public ModInt addId() {
		return new ModInt(mod, 0);
	}

	@Override
	public ModInt addInv() {
		return new ModInt(mod, -value);
	}

	@Override
	public ModInt mul(ModInt elem) {
		if (mod == elem.mod) return new ModInt(mod, value * elem.value);
		return null;
	}

	@Override
	public ModInt mulId() {
		return new ModInt(mod, 1);
	}

	@Override
	public ModInt mulInv() {
		if (MathUtil.GCD(new Int(mod), new Int(value)).is(Int.one)) {
			return new ModInt(mod, MathUtil.BezoutCoef(new Int(mod), new Int(value)).get(1).value());
		}
		return null;
	}

	@Override
	public Int efn() {	// TODO not an Euclidean Ring if mod is not a prime number
		return is(addId())?Int.zero:Int.one;
	}

	@Override
	public ModInt quo(ModInt elem) {	// TODO not an Euclidean Ring if mod is not a prime number
		return elem.is(addId())?null:mulInv();
	}

	@Override
	public ModInt rem(ModInt elem) {	// TODO not an Euclidean Ring if mod is not a prime number
		return elem.is(addId())?null:addId();
	}

	@Override
	public boolean is(ModInt elem) {
		return (mod == elem.mod && value == elem.value);
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}

}
