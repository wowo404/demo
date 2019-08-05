package org.liu.echoserver;

import org.apache.commons.pool.PoolableObjectFactory;

public class EchoServerPoolableData extends EchoServerData implements PoolableObjectFactory {
    @Override
    public Object makeObject() throws Exception {
        return new EchoServerPoolableData();
    }

    @Override
    public void destroyObject(Object o) throws Exception {
        if (o == null) return;
        passivateObject(o);
        o = null;
    }

    @Override
    public boolean validateObject(Object o) {
        if (o == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void activateObject(Object o) throws Exception {

    }

    @Override
    public void passivateObject(Object o) throws Exception {
        EchoServerPoolableData data = (EchoServerPoolableData)o;
        data.setHelloCount(0);
        data.setUsername(null);
    }
}
