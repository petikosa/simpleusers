package petikosa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import petikosa.dtos.UserDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvcTester mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @WithAnonymousUser
    @Test
    public void getAllUsersWithoutLogin() throws Exception {
        // when
        MvcTestResult result = mockMvc.perform(MockMvcRequestBuilders.get("/public/all"));

        // then
        String json = result.getResponse().getContentAsString();
        List<UserDto> users = objectMapper.readValue(json, new TypeReference<>(){});
        assertThat(users).hasSizeGreaterThan(2);
    }

    @WithAnonymousUser
    @Test
    public void getSingleUsersWithoutLogin() throws Exception {
        // given
        String id = "1";

        // when
        MvcTestResult result = mockMvc.perform(MockMvcRequestBuilders.get("/public/single").param("id", id));

        // then
        String json = result.getResponse().getContentAsString();
        UserDto user = objectMapper.readValue(json, UserDto.class);
        assertThat(user.name).isEqualTo("John");
    }
}