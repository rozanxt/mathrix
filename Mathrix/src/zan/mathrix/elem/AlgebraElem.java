package zan.mathrix.elem;

/** Algebra Element */
public interface AlgebraElem<V, S extends RingElem<S>> extends ModuleElem<V, S>, RingElem<V> {	// TODO technically Algebra is not a Ring

}
