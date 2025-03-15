package cn.seaway.word2007;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzs
 * @Date 2024/11/14 17:14
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlockCodeIntroducesResp {
    private String name;
    private String code;
    private Integer nature;
    private List<Introduce> introduces = new ArrayList<>();

    @Data
    public static class Introduce {
        private List<String> imgUrl = new ArrayList<>();
        private List<String> content = new ArrayList<>();
    }
}
