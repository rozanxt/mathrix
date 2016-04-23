package zan.mathrix.elem;

/** Ring Element */
public interface RingElem<E> extends GroupElem<E> {

	public E mul(E elem);
	public E mulId();
	public E mulInv();

}
