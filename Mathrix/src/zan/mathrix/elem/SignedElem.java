package zan.mathrix.elem;

import zan.mathrix.number.Int;

/** Signed Element */
public interface SignedElem<E> extends Elem<E> {

	public Int sgn();
	public E abs();

}
