package zan.mathrix.elem;

/** Module Element */
public interface ModuleElem<V, S extends RingElem<S>> extends GroupElem<V> {

	public V scalar(S scalar);

}
