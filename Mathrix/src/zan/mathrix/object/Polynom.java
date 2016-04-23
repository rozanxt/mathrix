package zan.mathrix.object;

import java.util.ArrayList;

import zan.mathrix.elem.AlgebraElem;
import zan.mathrix.elem.RingElem;

/** Polynomial Element */
public final class Polynom<S extends RingElem<S>> implements AlgebraElem<Polynom<S>, S> {	// TODO Polynomial with multiple indeterminate

	private final static boolean PRINT_BRACKETS = true;

	private final ArrayList<S> data;

	public Polynom(ArrayList<S> data) {
		S zero = data.get(0).addId();
		while (data.size() > 1 && data.get(data.size()-1).is(zero)) data.remove(data.size()-1);
		this.data = data;
	}

	public int length() {
		return data.size();
	}

	public int degree() {
		if (length() == 1 && get(0).is(get(0).addId())) return -1;
		return length()-1;
	}

	public S get(int index) {
		if (index >= length()) return data.get(0).addId();
		return data.get(index);
	}

	public ArrayList<S> toArrayList() {
		return new ArrayList<S>(data);
	}

	public Vec<S> toVec() {
		return new Vec<S>(toArrayList());
	}

	@Override
	public Polynom<S> add(Polynom<S> elem) {
		ArrayList<S> result = new ArrayList<S>();
		for (int i=0;i<Math.max(length(), elem.length());i++) result.add(get(i).add(elem.get(i)));
		return new Polynom<S>(result);
	}

	@Override
	public Polynom<S> addId() {
		ArrayList<S> result = new ArrayList<S>();
		result.add(get(0).addId());
		return new Polynom<S>(result);
	}

	@Override
	public Polynom<S> addInv() {
		ArrayList<S> result = new ArrayList<S>();
		for (int i=0;i<length();i++) result.add(get(i).addInv());
		return new Polynom<S>(result);
	}

	@Override
	public Polynom<S> scalar(S scalar) {
		ArrayList<S> result = new ArrayList<S>();
		for (int i=0;i<length();i++) result.add(scalar.mul(get(i)));
		return new Polynom<S>(result);
	}

	@Override
	public Polynom<S> mul(Polynom<S> elem) {
		ArrayList<S> result = new ArrayList<S>();
		for (int i=0;i<length()+elem.length();i++) {
			S sum = get(0).addId();
			for (int j=0;j<i+1;j++) {
				sum = sum.add(get(j).mul(elem.get(i-j)));
			}
			result.add(sum);
		}
		return new Polynom<S>(result);
	}

	@Override
	public Polynom<S> mulId() {
		ArrayList<S> result = new ArrayList<S>();
		result.add(get(0).mulId());
		return new Polynom<S>(result);
	}

	@Override
	public Polynom<S> mulInv() {	// TODO only in Integral Domains
		if (degree() == 0) {
			ArrayList<S> result = new ArrayList<S>();
			result.add(get(0).mulInv());
			return new Polynom<S>(result);
		}
		return null;
	}

	@Override
	public boolean is(Polynom<S> elem) {
		if (length() != elem.length()) return false;
		for (int i=0;i<length();i++) if (!get(i).is(elem.get(i))) return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i=length()-1;i>=0;i--) {
			if (!get(i).is(get(i).addId())) {
				if (i < length()) str.append("+");
				if (PRINT_BRACKETS) str.append("(");
				str.append(get(i));
				if (PRINT_BRACKETS) str.append(")");
				if (i == 1) str.append("t");
				else if (i > 0) str.append("t").append(i);
			}
		}
		if (str.toString().isEmpty()) {
			if (PRINT_BRACKETS) str.append("(");
			str.append(get(0));
			if (PRINT_BRACKETS) str.append(")");
			return str.toString();
		}
		return str.toString().substring(1);
	}

}
