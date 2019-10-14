package org.liu.json;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class TLVRealtimeDomainWaveform implements Serializable {
    private String serialNum;//设备序列号
    private Integer tunnelCode;//通道号
    //同一次请求的时间戳是一样的，比如服务器端发出一次请求，设备端返回200个包的数据，则这200个包中的时间戳是一样的
    private String dateTime;//时间戳（7个字节，年两个字节，月，日，时，分，秒各一个，相对时间，目前不准确，分体的是准确的）
    private Integer packageNum;//包号（两个字节）
    private Integer[] sourceValueArray;//原始值，字节数据采用整型数占用2字节，int16_t
    private List<Integer> list;
    private Set<Integer> tunnelCodeList;
}
