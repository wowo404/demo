package org.demo.json;

import lombok.Data;

@Data
public class OpenApiRequest {

    private String appId;
    private String serviceName;
    private String timestamp;
    private String reqData;
    private String sign;

}
