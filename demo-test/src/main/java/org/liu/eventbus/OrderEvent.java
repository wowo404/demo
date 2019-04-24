package org.liu.eventbus;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by liuzhsh on 2017/11/21.
 */
@Data
@AllArgsConstructor
public class OrderEvent {
    private Long orderId;
    private Double amount;
}
