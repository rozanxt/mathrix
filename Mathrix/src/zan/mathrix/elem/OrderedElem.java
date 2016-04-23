package zan.mathrix.elem;

/** Ordered Element */
public interface OrderedElem<E> extends Elem<E> {

	public boolean leq(E elem);
	public boolean geq(E elem);

}
