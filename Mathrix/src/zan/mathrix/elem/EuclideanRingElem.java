package zan.mathrix.elem;

import zan.mathrix.num.Int;

/** Euclidean Ring Element */
public interface EuclideanRingElem<E> extends RingElem<E> {

	public Int efn();
	public E quo(E elem);
	public E rem(E elem);

}
