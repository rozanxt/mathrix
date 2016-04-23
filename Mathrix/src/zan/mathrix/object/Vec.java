package zan.mathrix.object;

import java.util.ArrayList;

import zan.mathrix.elem.ModuleElem;
import zan.mathrix.elem.RingElem;
import zan.mathrix.util.MathUtil;

/** Vector Element */
public class Vec<S extends RingElem<S>> implements ModuleElem<Vec<S>, S> {

	private final ArrayList<S> data;

	public Vec(ArrayList<S> data) {
		this.data = data;
	}

	public int length() {
		return data.size();
	}

	public S get(int index) {
		return data.get(index);
	}

	public Vec<S> get(int fromIndex, int toIndex) {
		return new Vec<S>(MathUtil.subList(data, fromIndex, toIndex));
	}

	public ArrayList<S> toArrayList() {
		return new ArrayList<S>(data);
	}

	public Mat<S> toMat(int rows, int cols) {
		if (rows * cols == length()) {
			ArrayList<ArrayList<S>> result = new ArrayList<ArrayList<S>>();
			for (int i=0;i<rows;i++) {
				ArrayList<S> temp = new ArrayList<S>();
				for (int j=0;j<cols;j++) {
					temp.add(get(cols*i+j));
				}
				result.add(temp);
			}
			return new Mat<S>(rows, cols, result);
		}
		return null;
	}

	public Mat<S> toRowVec() {
		return toMat(1, length());
	}

	public Mat<S> toColVec() {
		return toMat(length(), 1);
	}

	@Override
	public Vec<S> add(Vec<S> elem) {
		if (length() == elem.length()) {
			ArrayList<S> result = new ArrayList<S>();
			for (int i=0;i<length();i++) result.add(get(i).add(elem.get(i)));
			return new Vec<S>(result);
		}
		return null;
	}

	@Override
	public Vec<S> addId() {
		ArrayList<S> result = new ArrayList<S>();
		for (int i=0;i<length();i++) result.add(get(i).addId());
		return new Vec<S>(result);
	}

	@Override
	public Vec<S> addInv() {
		ArrayList<S> result = new ArrayList<S>();
		for (int i=0;i<length();i++) result.add(get(i).addInv());
		return new Vec<S>(result);
	}

	@Override
	public Vec<S> scalar(S scalar) {
		ArrayList<S> result = new ArrayList<S>();
		for (int i=0;i<length();i++) result.add(scalar.mul(get(i)));
		return new Vec<S>(result);
	}

	@Override
	public boolean is(Vec<S> elem) {
		if (length() != elem.length()) return false;
		for (int i=0;i<length();i++) if (!get(i).is(elem.get(i))) return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("(");
		for (int i=0;i<length();i++) {
			str.append(get(i));
			if (i < length()-1) str.append(",");
		}
		str.append(")");
		return str.toString();
	}

}
