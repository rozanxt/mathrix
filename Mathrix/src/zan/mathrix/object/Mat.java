package zan.mathrix.object;

import java.util.ArrayList;

import zan.mathrix.elem.AlgebraElem;
import zan.mathrix.elem.RingElem;
import zan.mathrix.util.MathUtil;

/** Matrix Element */
public final class Mat<S extends RingElem<S>> implements AlgebraElem<Mat<S>, S> {

	private final static boolean PRINT_NEWLINE = true;

	private final int rows, cols;
	private final ArrayList<ArrayList<S>> data;

	public Mat(int rows, int cols, ArrayList<ArrayList<S>> data) {
		this.rows = rows;
		this.cols = cols;
		this.data = data;
	}

	public int rows() {
		return rows;
	}

	public int cols() {
		return cols;
	}

	public int length() {
		return rows * cols;
	}

	public boolean isSquare() {
		return rows == cols;
	}

	public S get(int row, int col) {
		return data.get(row).get(col);
	}

	public Mat<S> get(int fromRow, int fromCol, int toRow, int toCol) {
		int subrows = (toRow - fromRow) + 1;
		int subcols = (toCol - fromCol) + 1;
		ArrayList<ArrayList<S>> result = new ArrayList<ArrayList<S>>();
		for (int i=0;i<subrows;i++) {
			ArrayList<S> temp = new ArrayList<S>();
			for (int j=0;j<subcols;j++) {
				temp.add(get(fromRow + i, fromCol + j));
			}
			result.add(temp);
		}
		return new Mat<S>(subrows, subcols, result);
	}

	public Mat<S> getSubMat(int row, int col) {
		int subrows = rows - 1;
		int subcols = cols - 1;
		ArrayList<ArrayList<S>> result = new ArrayList<ArrayList<S>>();
		for (int i=0;i<subrows;i++) {
			if (i < row) {
				ArrayList<S> temp = new ArrayList<S>();
				for (int j=0;j<subcols;j++) {
					if (j < col) temp.add(get(i, j));
					else temp.add(get(i, j + 1));
				}
				result.add(temp);
			} else {
				ArrayList<S> temp = new ArrayList<S>();
				for (int j=0;j<subcols;j++) {
					if (j < col) temp.add(get(i + 1, j));
					else temp.add(get(i + 1, j + 1));
				}
				result.add(temp);
			}
		}
		return new Mat<S>(subrows, subcols, result);
	}

	public Vec<S> getRowVec(int row) {
		return new Vec<S>(data.get(row));
	}

	public Vec<S> getColVec(int col) {
		ArrayList<S> result = new ArrayList<S>();
		for (int i=0;i<rows;i++) result.add(get(i, col));
		return new Vec<S>(result);
	}

	public ArrayList<ArrayList<S>> toArrayList() {
		ArrayList<ArrayList<S>> result = new ArrayList<ArrayList<S>>();
		for (int i=0;i<data.size();i++) result.add(new ArrayList<S>(data.get(i)));
		return result;
	}

	public Vec<S> toVec() {
		ArrayList<S> result = new ArrayList<S>();
		for (int i=0;i<rows;i++) {
			for (int j=0;j<cols;j++) {
				result.add(get(i, j));
			}
		}
		return new Vec<S>(result);
	}

	public Mat<S> transpose() {
		ArrayList<ArrayList<S>> result = new ArrayList<ArrayList<S>>();
		for (int i=0;i<cols;i++) {
			ArrayList<S> temp = new ArrayList<S>();
			for (int j=0;j<rows;j++) {
				temp.add(get(j, i));
			}
			result.add(temp);
		}
		return new Mat<S>(cols, rows, result);
	}

	public Mat<S> adj() {
		ArrayList<ArrayList<S>> result = new ArrayList<ArrayList<S>>();;
		for (int i=0;i<rows;i++) {
			ArrayList<S> temp = new ArrayList<S>();
			for (int j=0;j<cols;j++) temp.add(cofactor(j, i));
			result.add(temp);
		}
		return new Mat<S>(rows, cols, result);
	}

	public S trace() {
		if (isSquare()) {
			S result = get(0, 0).addId();
			for (int i=0;i<rows;i++) result = result.add(get(i, i));
			return result;
		}
		return null;
	}

	public S det() {
		return MathUtil.det(this);
	}

	public S minor(int row, int col) {
		return MathUtil.det(getSubMat(row, col));
	}

	public S cofactor(int row, int col) {
		return ((row+col)%2==0)?minor(row, col):minor(row, col).addInv();
	}

	public Polynom<S> CP() {
		return MathUtil.CP(this);
	}

	public Mat<S> RREF() {
		return MathUtil.RREF(this);
	}

	@Override
	public Mat<S> add(Mat<S> elem) {
		if (rows == elem.rows && cols == elem.cols) {
			ArrayList<ArrayList<S>> result = new ArrayList<ArrayList<S>>();
			for (int i=0;i<rows;i++) {
				ArrayList<S> temp = new ArrayList<S>();
				for (int j=0;j<cols;j++) {
					temp.add(get(i, j).add(elem.get(i, j)));
				}
				result.add(temp);
			}
			return new Mat<S>(rows, elem.cols, result);
		}
		return null;
	}

	@Override
	public Mat<S> addId() {
		ArrayList<ArrayList<S>> result = new ArrayList<ArrayList<S>>();
		for (int i=0;i<rows;i++) {
			ArrayList<S> temp = new ArrayList<S>();
			for (int j=0;j<cols;j++) {
				temp.add(get(i, j).addId());
			}
			result.add(temp);
		}
		return new Mat<S>(rows, cols, result);
	}

	@Override
	public Mat<S> addInv() {
		ArrayList<ArrayList<S>> result = new ArrayList<ArrayList<S>>();
		for (int i=0;i<rows;i++) {
			ArrayList<S> temp = new ArrayList<S>();
			for (int j=0;j<cols;j++) {
				temp.add(get(i, j).addInv());
			}
			result.add(temp);
		}
		return new Mat<S>(rows, cols, result);
	}

	@Override
	public Mat<S> scalar(S scalar) {
		ArrayList<ArrayList<S>> result = new ArrayList<ArrayList<S>>();
		for (int i=0;i<rows;i++) {
			ArrayList<S> temp = new ArrayList<S>();
			for (int j=0;j<cols;j++) {
				temp.add(scalar.mul(get(i, j)));
			}
			result.add(temp);
		}
		return new Mat<S>(rows, cols, result);
	}

	@Override
	public Mat<S> mul(Mat<S> elem) {
		if (cols == elem.rows) {
			ArrayList<ArrayList<S>> result = new ArrayList<ArrayList<S>>();
			for (int i=0;i<rows;i++) {
				ArrayList<S> temp = new ArrayList<S>();
				for (int j=0;j<elem.cols;j++) {
					S sum = get(i, 0).mul(elem.get(0, j));
					for (int k=1;k<cols;k++) {
						sum = sum.add(get(i, k).mul(elem.get(k, j)));
					}
					temp.add(sum);
				}
				result.add(temp);
			}
			return new Mat<S>(rows, elem.cols, result);
		}
		return null;
	}

	@Override
	public Mat<S> mulId() {
		if (isSquare()) {
			ArrayList<ArrayList<S>> result = new ArrayList<ArrayList<S>>();
			for (int i=0;i<rows;i++) {
				ArrayList<S> temp = new ArrayList<S>();
				for (int j=0;j<cols;j++) {
					if (i == j) temp.add(get(i, j).mulId());
					else temp.add(get(i, j).addId());
				}
				result.add(temp);
			}
			return new Mat<S>(rows, cols, result);
		}
		return null;
	}

	@Override
	public Mat<S> mulInv() {
		if (isSquare() && !get(0, 0).addId().is(det())) {
			return MathUtil.RREF(MathUtil.appendMatH(this, mulId())).get(0, cols, rows-1, 2*cols-1);
		}
		return null;
	}

	@Override
	public boolean is(Mat<S> elem) {
		return toVec().is(elem.toVec());
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("(");
		for (int i=0;i<rows;i++) {
			for (int j=0;j<cols;j++) {
				str.append(get(i, j));
				if (j<cols-1) str.append(",");
			}
			if (i<rows-1) {
				str.append(";");
				if (PRINT_NEWLINE) str.append("\n ");
			}
		}
		str.append(")");
		return str.toString();
	}

}
