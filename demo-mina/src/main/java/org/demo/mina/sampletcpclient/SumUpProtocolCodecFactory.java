package org.demo.mina.sampletcpclient;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class SumUpProtocolCodecFactory implements ProtocolCodecFactory {

    private boolean tag;

    public SumUpProtocolCodecFactory(boolean tag) {
        this.tag = tag;
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return null;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return null;
    }
}
