package zan.mathrix.util;

import java.util.ArrayList;
import java.util.Arrays;

import zan.mathrix.elem.Elem;
import zan.mathrix.elem.EuclideanRingElem;
import zan.mathrix.elem.GroupElem;
import zan.mathrix.elem.MetricElem;
import zan.mathrix.elem.ModuleElem;
import zan.mathrix.elem.OrderedElem;
import zan.mathrix.elem.RingElem;
import zan.mathrix.elem.SignedElem;
import zan.mathrix.number.Complex;
import zan.mathrix.number.Int;
import zan.mathrix.number.ModInt;
import zan.mathrix.number.Ratio;
import zan.mathrix.number.Real;
import zan.mathrix.object.Mat;
import zan.mathrix.object.Polynom;
import zan.mathrix.object.Vec;

/** Math Utility */
public class MathUtil {

	/* Global variables */

	public static final double round = 1E8;
	public static final double error = 1E-8;

	/* ArrayList utility */

	@SafeVarargs
	public static <S> ArrayList<S> asList(S... data) {
		return new ArrayList<S>(Arrays.asList(data));
	}

	public static <S> ArrayList<S> subList(ArrayList<S> data, int fromIndex, int toIndex) {
		return new ArrayList<S>(data.subList(fromIndex, toIndex + 1));
	}

	public static <S> ArrayList<S> appendList(ArrayList<S> left, ArrayList<S> right) {
		ArrayList<S> result = new ArrayList<S>();
		result.addAll(left);
		result.addAll(right);
		return result;
	}

	/* Object creations */

	public static <S extends RingElem<S>> Vec<S> Vec(ArrayList<S> data) {
		return new Vec<S>(data);
	}

	public static Vec<Int> IntVec(int... data) {
		ArrayList<Int> result = new ArrayList<Int>();
		for (int i=0;i<data.length;i++) result.add(new Int(data[i]));
		return new Vec<Int>(result);
	}

	public static Vec<Ratio> RatioVec(int... data) {
		if (data.length % 2 == 0) {
			ArrayList<Ratio> result = new ArrayList<Ratio>();
			for (int i=0;i<data.length/2;i++) result.add(new Ratio(data[2*i], data[2*i+1]));
			return new Vec<Ratio>(result);
		}
		return null;
	}

	public static Vec<Real> RealVec(double... data) {
		ArrayList<Real> result = new ArrayList<Real>();
		for (int i=0;i<data.length;i++) result.add(new Real(data[i]));
		return new Vec<Real>(result);
	}

	public static Vec<Complex> ComplexVec(double... data) {
		if (data.length % 2 == 0) {
			ArrayList<Complex> result = new ArrayList<Complex>();
			for (int i=0;i<data.length/2;i++) result.add(new Complex(data[2*i], data[2*i+1]));
			return new Vec<Complex>(result);
		}
		return null;
	}

	public static Vec<ModInt> ModIntVec(int mod, int... data) {
		ArrayList<ModInt> result = new ArrayList<ModInt>();
		for (int i=0;i<data.length;i++) result.add(new ModInt(mod, data[i]));
		return new Vec<ModInt>(result);
	}

	public static <S extends RingElem<S>> Mat<S> Mat(int rows, int cols, ArrayList<S> data) {
		return Vec(data).toMat(rows, cols);
	}

	public static Mat<Int> IntMat(int rows, int cols, int... data) {
		return IntVec(data).toMat(rows, cols);
	}

	public static Mat<Ratio> RatioMat(int rows, int cols, int... data) {
		return RatioVec(data).toMat(rows, cols);
	}

	public static Mat<Real> RealMat(int rows, int cols, double... data) {
		return RealVec(data).toMat(rows, cols);
	}

	public static Mat<Complex> ComplexMat(int rows, int cols, double... data) {
		return ComplexVec(data).toMat(rows, cols);
	}

	public static Mat<ModInt> ModIntMat(int rows, int cols, int mod, int... data) {
		return ModIntVec(mod, data).toMat(rows, cols);
	}

	public static <S extends RingElem<S>> Polynom<S> Polynom(ArrayList<S> data) {
		return new Polynom<S>(Vec(data).toArrayList());
	}

	public static Polynom<Int> IntPolynom(int... data) {
		return Polynom(IntVec(data).toArrayList());
	}

	public static Polynom<Ratio> RatioPolynom(int... data) {
		return Polynom(RatioVec(data).toArrayList());
	}

	public static Polynom<Real> RealPolynom(double... data) {
		return Polynom(RealVec(data).toArrayList());
	}

	public static Polynom<Complex> ComplexPolynom(double... data) {
		return Polynom(ComplexVec(data).toArrayList());
	}

	public static Polynom<ModInt> ModIntPolynom(int mod, int... data) {
		return Polynom(ModIntVec(mod, data).toArrayList());
	}

	/* Math operations */

	public static <E extends Elem<E>> boolean isEqual(E a, E b) {
		return a.is(b);
	}

	public static <E extends OrderedElem<E>> boolean leq(E a, E b) {
		return a.leq(b);
	}

	public static <E extends OrderedElem<E>> boolean geq(E a, E b) {
		return a.geq(b);
	}

	public static <E extends OrderedElem<E>> E min(E a, E b) {
		return leq(a, b)?a:b;
	}

	public static <E extends OrderedElem<E>> E max(E a, E b) {
		return geq(a, b)?a:b;
	}

	public static <E extends SignedElem<E>> E abs(E a) {
		return a.abs();
	}

	public static <E extends SignedElem<E>> Int sgn(E a) {
		return a.sgn();
	}

	public static <E extends MetricElem<E>> Real dist(E a, E b) {
		return a.dist(b);
	}

	public static <E extends GroupElem<E>> E ops(E a, E b) {
		return a.add(b);
	}

	public static <E extends RingElem<E>> E add(E a, E b) {
		return a.add(b);
	}

	public static <E extends RingElem<E>> E sub(E a, E b) {
		return a.add(b.addInv());
	}

	public static <E extends RingElem<E>> E mul(E a, E b) {
		return a.mul(b);
	}

	public static <E extends RingElem<E>> E div(E a, E b) {
		return a.mul(b.mulInv());
	}

	public static <E extends EuclideanRingElem<E>> E quo(E a, E b) {
		return a.quo(b);
	}

	public static <E extends EuclideanRingElem<E>> E rem(E a, E b) {
		return a.rem(b);
	}

	public static <V extends ModuleElem<V, S>, S extends RingElem<S>> V scalar(S s, V v) {
		return v.scalar(s);
	}

	public static <E extends OrderedElem<E>> E min(ArrayList<E> tuple) {
		if (tuple.size() == 1) return tuple.get(0);
		else if (tuple.size() == 2) return min(tuple.get(0), tuple.get(1));
		else if (tuple.size() > 2) return min(appendList(asList(min(subList(tuple, 0, 1))), subList(tuple, 2, tuple.size()-1)));
		return null;
	}

	public static <E extends OrderedElem<E>> E max(ArrayList<E> tuple) {
		if (tuple.size() == 1) return tuple.get(0);
		else if (tuple.size() == 2) return max(tuple.get(0), tuple.get(1));
		else if (tuple.size() > 2) return max(appendList(asList(max(subList(tuple, 0, 1))), subList(tuple, 2, tuple.size()-1)));
		return null;
	}

	public static <E extends RingElem<E>> E sum(ArrayList<E> tuple) {
		if (tuple.size() == 1) return tuple.get(0);
		else if (tuple.size() == 2) return add(tuple.get(0), tuple.get(1));
		else if (tuple.size() > 2) return sum(appendList(asList(sum(subList(tuple, 0, 1))), subList(tuple, 2, tuple.size()-1)));
		return null;
	}

	public static <E extends RingElem<E>> E prd(ArrayList<E> tuple) {
		if (tuple.size() == 1) return tuple.get(0);
		else if (tuple.size() == 2) return mul(tuple.get(0), tuple.get(1));
		else if (tuple.size() > 2) return prd(appendList(asList(prd(subList(tuple, 0, 1))), subList(tuple, 2, tuple.size()-1)));
		return null;
	}

	/* Matrix & Vector operations */

	public static <S extends RingElem<S>> Vec<S> appendVec(Vec<S> left, Vec<S> right) {
		return Vec(appendList(left.toArrayList(), right.toArrayList()));
	}

	public static <S extends RingElem<S>> Mat<S> appendMatH(Mat<S> left, Mat<S> right) {
		if (left.rows() == right.rows()) {
			int appcols = left.cols() + right.cols();
			ArrayList<ArrayList<S>> result = new ArrayList<ArrayList<S>>();
			for (int i=0;i<left.rows();i++) {
				ArrayList<S> temp = new ArrayList<S>();
				for (int j=0;j<appcols;j++) {
					if (j < left.rows()) temp.add(left.get(i, j));
					else temp.add(right.get(i, j - left.cols()));
				}
				result.add(temp);
			}
			return new Mat<S>(left.rows(), appcols, result);
		}
		return null;
	}

	public static <S extends RingElem<S>> Mat<S> appendMatV(Mat<S> up, Mat<S> down) {
		if (up.cols() == down.cols()) {
			int approws = up.rows() + down.rows();
			ArrayList<ArrayList<S>> result = new ArrayList<ArrayList<S>>();
			for (int i=0;i<approws;i++) {
				ArrayList<S> temp = new ArrayList<S>();
				if (i < up.rows()) for (int j=0;j<up.cols();j++) temp.add(up.get(i, j));
				else for (int j=0;j<up.cols();j++) temp.add(down.get(i - up.rows(), j));
				result.add(temp);
			}
			return new Mat<S>(approws, up.cols(), result);
		}
		return null;
	}

	public static Vec<Real> cross(Vec<Real> a, Vec<Real> b) {
		if (a.length() == 3 && b.length() == 3) {
			ArrayList<Real> cross = new ArrayList<Real>();
			cross.add(sub(mul(a.get(1), b.get(2)), mul(a.get(2), b.get(1))));
			cross.add(sub(mul(a.get(2), b.get(0)), mul(a.get(0), b.get(2))));
			cross.add(sub(mul(a.get(0), b.get(1)), mul(a.get(1), b.get(0))));
			return Vec(cross);
		}
		return null;
	}

	public static Real dot(Vec<Real> a, Vec<Real> b) {	// TODO Structural implementation of Inner Product Spaces
		if (a.length() == b.length()) {
			Real dot = Real.zero;
			for (int i=0;i<a.length();i++) dot.add(a.get(i).mul(b.get(i)));
			return dot;
		}
		return null;
	}


	public static Real norm(Vec<Real> vector) {	// TODO Structural implementation of Normed Spaces
		return dot(vector, vector).sqrt();
	}

	public static Real dist(Vec<Real> a, Vec<Real> b) {	// TODO Structural implementation of Normed Spaces
		return norm(a.add(b.addInv()));
	}

	public static <S extends RingElem<S>> Mat<S> elemRowAdd(Mat<S> matrix, int addTo, int addFrom, S scalar) {
		ArrayList<ArrayList<S>> result = matrix.toArrayList();
		result.set(addTo, matrix.getRowVec(addTo).add(matrix.getRowVec(addFrom).scalar(scalar)).toArrayList());
		return new Mat<S>(matrix.rows(), matrix.cols(), result);
	}

	public static <S extends RingElem<S>> Mat<S> elemRowMul(Mat<S> matrix, int mulTo, S scalar) {
		ArrayList<ArrayList<S>> result = matrix.toArrayList();
		result.set(mulTo, matrix.getRowVec(mulTo).scalar(scalar).toArrayList());
		return new Mat<S>(matrix.rows(), matrix.cols(), result);
	}

	public static <S extends RingElem<S>> Mat<S> elemRowSwap(Mat<S> matrix, int swapA, int swapB) {
		if (swapA == swapB) return matrix;
		ArrayList<ArrayList<S>> result = matrix.toArrayList();
		result.set(swapA, matrix.getRowVec(swapB).toArrayList());
		result.set(swapB, matrix.getRowVec(swapA).toArrayList());
		return new Mat<S>(matrix.rows(), matrix.cols(), result);
	}

	public static Vec<Complex> conj(Vec<Complex> vector) {
		ArrayList<Complex> result = vector.toArrayList();
		for (int i=0;i<result.size();i++) result.set(i, result.get(i).conj());
		return Vec(result);
	}

	public static Mat<Complex> conj(Mat<Complex> matrix) {
		ArrayList<Complex> result = matrix.toVec().toArrayList();
		for (int i=0;i<result.size();i++) result.set(i, result.get(i).conj());
		return Mat(matrix.rows(), matrix.cols(), result);
	}

	public static <S extends RingElem<S>> Mat<S> transpose(Mat<S> matrix) {
		return matrix.transpose();
	}

	public static Mat<Complex> transjugate(Mat<Complex> matrix) {
		return conj(transpose(matrix));
	}

	public static <S extends RingElem<S>> Mat<S> adj(Mat<S> matrix) {
		return matrix.adj();
	}

	public static <S extends RingElem<S>> S trace(Mat<S> matrix) {
		return matrix.trace();
	}

	public static <S extends RingElem<S>> S det(Mat<S> matrix) {
		if (matrix.isSquare()) {
			S det = matrix.get(0, 0).addId();
			if (matrix.cols() > 1) for (int i=0;i<matrix.cols();i++) det = det.add(matrix.get(0, i).mul(matrix.cofactor(0, i)));
			else det = matrix.get(0, 0);
			return det;
		}
		return null;
	}

	public static <S extends RingElem<S>> Polynom<S> CP(Mat<S> matrix) {
		if (matrix.isSquare()) {
			ArrayList<S> data = matrix.toVec().toArrayList();
			ArrayList<Polynom<S>> polymat = new ArrayList<Polynom<S>>();
			for (int i=0;i<data.size();i++) polymat.add(Polynom(asList(data.get(i))));
			Mat<Polynom<S>> A = Mat(matrix.rows(), matrix.cols(), polymat);
			return det(A.mulId().scalar(Polynom(asList(data.get(0).addId(), data.get(0).mulId()))).add(A.addInv()));
		}
		return null;
	}

	public static <S extends RingElem<S>> Mat<S> RREF(Mat<S> matrix) {
		Mat<S> result = matrix;
		int r = 0, s = 0;
		while (r < result.rows() && s < result.cols()) {
			int k = 0;
			for (int i=r;i<result.rows();i++) {
				if (k == 0 && !result.get(i, s).is(result.get(i, s).addId())) {
					k = i;
				}
			}
			if (k != 0) {
				result = elemRowSwap(result, r, k);
				result = elemRowMul(result, r, result.get(r, s).mulInv());
				for (int i=0;i<result.rows();i++) {
					if (i != r) result = elemRowAdd(result, i, r, result.get(i, s).addInv());
				}
				r++;
			}
			s++;
		}
		return result;
	}

	public static <S extends RingElem<S>> Vec<S> solve(Mat<S> A, Vec<S> b) {
		return RREF(appendMatH(A, b.toColVec())).getColVec(A.cols());
	}

	/* Math calculations */

	public static <E extends EuclideanRingElem<E>> E GCD(E a, E b) {
		return (b.is(b.addId()))?a:GCD(b, a.rem(b));
	}

	public static <E extends EuclideanRingElem<E>> E GCD(ArrayList<E> tuple) {
		if (tuple.size() == 1) return tuple.get(0);
		else if (tuple.size() == 2) return GCD(tuple.get(0), tuple.get(1));
		else if (tuple.size() > 2) return GCD(appendList(asList(GCD(tuple.get(0), tuple.get(1))), subList(tuple, 2, tuple.size()-1)));
		return null;
	}

	public static <E extends EuclideanRingElem<E>> ArrayList<E> BezoutCoef(E a, E b) {
		E gcd = GCD(asList(a, b));
		if (a.is(gcd)) return asList(a.mulId(), b.addId());
		if (b.is(gcd)) return asList(a.addId(), b.mulId());

		ArrayList<E> c = new ArrayList<E>();
		ArrayList<E> d = new ArrayList<E>();
		E g = (max(a.efn(), b.efn()).is(a.efn()))?a:b;
		E l = (min(a.efn(), b.efn()).is(a.efn()))?a:b;
		d.add(g);

		while (!l.is(gcd) && !l.is(l.addId())) {
			E q = g.quo(l);
			E r = g.rem(l);
			c.add(q);
			d.add(l);
			g = l;
			l = r;
		}

		E x = c.get(c.size()-1).addInv();
		E y = x.mulId();
		for (int i=2;i<c.size()+1;i++) {
			E z = y;
			y = x;
			x = z.add(x.mul(c.get(c.size()-i).addInv()));
		}

		return (max(a.efn(), b.efn()).is(a.efn()))?asList(y, x):asList(x, y);
	}

}
