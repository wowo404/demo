package org.liu.json;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.liu.ActivitiApplication;
import org.liu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.BasicJsonTester;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JsonTest
@SpringBootTest(classes = {ActivitiApplication.class})
public class MyJsonTest {
	
	@Autowired
	private JacksonTester<User> jackson;
	@Autowired
	private GsonTester<User> gson;
	@Autowired
	private BasicJsonTester basicJson;
	
	@Test
	public void testJacksonSerialize() throws IOException{
		User user = new User();
		user.setUserId(1L);
		user.setUserName("liu");
		user.setEmail("346487291@qq.com");
		// Assert against a `.json` file in the same package as the test
		assertThat(this.jackson.write(user)).isEqualToJson("liu.json");
		// Or use JSON path based assertions
        assertThat(this.jackson.write(user)).hasJsonPathStringValue("@.userName");
        assertThat(this.jackson.write(user)).extractingJsonPathStringValue("@.userName")
                .isEqualTo("Honda");
	}
	
	@Test
    public void testJacksonDeserialize() throws Exception {
		User user = new User();
		user.setUserId(1L);
		user.setUserName("liu");
		user.setEmail("346487291@qq.com");
        String content = "{\"userId\":1,\"userName\":\"liu\",\"email\":\"346487291@qq.com\"}";
        assertThat(this.jackson.parse(content)).isEqualTo(user);
        assertThat(this.jackson.parseObject(content).getUserName()).isEqualTo("liu");
    }
	
	@Test
	public void testGsonSerialize() throws IOException{
		User user = new User();
		user.setUserId(1L);
		user.setUserName("liu");
		user.setEmail("346487291@qq.com");
		this.gson.write(user);
	}
	
	@Test
    public void testWriteJson() throws IOException {
        assertThat(this.basicJson.from("liu.json")).extractingJsonPathStringValue("@.userName")
				.isEqualTo("liu");
    }


}
