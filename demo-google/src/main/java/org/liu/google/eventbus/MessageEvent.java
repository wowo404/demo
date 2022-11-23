package org.liu.google.eventbus;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by liuzhsh on 2017/11/21.
 */
@Data
@AllArgsConstructor
public class MessageEvent {
    private String title;
    private String content;
}
