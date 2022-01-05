package org.demo.objenesis;

import org.junit.Test;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

public class TestObjenesis {

    @Test
    public void one() {
        Objenesis o = new ObjenesisStd();
        NoneDefaultConstructor instance = o.newInstance(NoneDefaultConstructor.class);
        ObjectInstantiator<NoneDefaultConstructor> instantiatorOf = o.getInstantiatorOf(NoneDefaultConstructor.class);
        NoneDefaultConstructor instance1 = instantiatorOf.newInstance();

        System.out.println(instance);
        System.out.println(instance1);
    }

}
