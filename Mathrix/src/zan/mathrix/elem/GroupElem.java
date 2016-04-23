package zan.mathrix.elem;

/** Group Element */
public interface GroupElem<E> extends Elem<E> {

	public E add(E elem);
	public E addId();
	public E addInv();

}
