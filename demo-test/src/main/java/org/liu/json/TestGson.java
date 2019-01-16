package org.liu.json;

import java.time.LocalDate;

import org.liu.enums.ServiceName;
import org.liu.enums.TransType;
import org.liu.gsonextension.GsonEnumTypeAdapter;
import org.liu.gsonextension.LocalDateTypeAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestGson {

	public static void main(String[] args) {
		TestGson t = new TestGson();
		t.testGson();
	}
	
	public void testGson() {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(TransType.class, new GsonEnumTypeAdapter<>(TransType.flag_017))
                .create();

        BaseReq req = new BaseReq();
		req.setId("aa");
		req.setServiceName(ServiceName.directSignClient);
		req.setTransType(TransType.flag_017);
		req.setBirthday(LocalDate.of(2018, 03, 20));
        String jsonText = gson.toJson(req);
        System.out.println("将 req 转换成 json 字符串：\n" + jsonText);

        System.out.println("-------------------");

        BaseReq p2 = gson.fromJson(jsonText, BaseReq.class);
        assert p2 != req;
        System.out.println("根据 json 字符串生成 person 实例：\n" + p2);

    }
	
}
