package zan.mathrix.elem;

import zan.mathrix.number.Real;

/** Element of Metric Space */
public interface MetricElem<E> extends Elem<E> {

	public Real dist(E elem);

}
