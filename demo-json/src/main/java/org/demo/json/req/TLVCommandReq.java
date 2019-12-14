package org.demo.json.req;

import lombok.Getter;
import lombok.Setter;
import org.demo.json.tlv.TLVEquipmentModel;

@Getter
@Setter
public class TLVCommandReq extends TLVEquipmentModel {
    private String type;//命令类型
    private Boolean needRestart;//是否重新启动
    private String newSerialNum;//新的设备序列号
    private WaveformCommand waveformCommand;
    private UpgradeCommand upgradeCommand;
    //---------------时域波形-----------
    @Getter
    @Setter
    public class WaveformCommand {
        private Integer seconds;//请求时间?
        private String tunnelCodeArray;//通道号?
    }
    //---------------软件升级-----------
    @Getter
    @Setter
    public class UpgradeCommand {
        private String filePath;//软件升级包的文件路径
    }
}
