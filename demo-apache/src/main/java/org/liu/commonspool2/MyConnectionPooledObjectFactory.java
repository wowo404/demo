package org.liu.commonspool2;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class MyConnectionPooledObjectFactory extends BasePooledObjectFactory<MyConnection> {
    @Override
    public MyConnection create() throws Exception {
        return new MyConnection();
    }

    @Override
    public PooledObject<MyConnection> wrap(MyConnection obj) {
        return new DefaultPooledObject<>(obj);
    }
}
