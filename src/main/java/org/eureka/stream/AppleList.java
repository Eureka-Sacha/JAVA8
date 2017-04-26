package org.eureka.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/** 
* @author Eureka_Sacha E-mail: 928858519@qq.com
* @version ����ʱ�䣺2017��3��15�� ����10:20:59 
* ��˵�� 
*/
public interface AppleList<E> extends Collection<E>{
	default int defaultTest(){
		return 0;
	}
}
