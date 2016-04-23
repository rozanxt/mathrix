package zan.mathrix.elem;

import zan.mathrix.num.Int;

/** Signed Element */
public interface SignedElem<E> extends Elem<E> {

	public Int sgn();
	public E abs();

}
