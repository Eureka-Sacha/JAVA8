package org.eureka.stream;

import java.util.Collection;

/**
 * The interface Apple list.
 *
 * @param <E> the type parameter
 */
public interface AppleList<E> extends Collection<E>{
	/**
	 * Default test int.
	 *
	 * @return the int
	 */
	default int defaultTest(){
		return 0;
	}
}
