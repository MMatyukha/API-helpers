package api.helpers;

import static com.consol.citrus.dsl.JsonPathSupport.jsonPath;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

import pojo.CreateUserRequestDTO;
import pojo.CreateUserResponseDTO;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.message.MessageType;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;

import com.consol.citrus.testng.TestNGCitrusSupport;
import org.springframework.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RestHelper_Test extends TestNGCitrusSupport {

  @Test(description = "Получить данные о пользователях", dataProvider = "usersProvider")
  @CitrusTest
  public void getUsersTest(String id, String name, String lastname) {

    run(http()
            .client("restClientReqres")
            .send()
            .get("users/" + id));

    run(http()
            .client("restClientReqres")
            .receive()
            .response(HttpStatus.OK)
            .message()
            .type(MessageType.JSON)
            .validate(jsonPath()
                    .expression("$.data.id", id)
                    .expression("$.data.first_name", name)
                    .expression("$.data.last_name", lastname))
    );
  }

  @DataProvider(name = "usersProvider")
  public Object[][] getProvider() {
    return new Object[][]{
      new Object[]{"1","Blayne", "Ryce"},
      new Object[]{"2","Werner", "Pues"},
      new Object[]{"3","Alberik", "Kilgrew"},
      new Object[]{"4","Herman", "Jeyness"},
      new Object[]{"5","Ferguson", "Yeude"},
      new Object[]{"6","Dominique", "Yurikov"},
      new Object[]{"7","Elnar", "Sims"},
      new Object[]{"8","Justine", "McGarry"},
      new Object[]{"9","Ricard", "Greated"},
      new Object[]{"10","Alfons", "Maystone"},
      new Object[]{"11","Fred", "Mockler"},
      new Object[]{"12","Elysee", "Robinett"},
    };
  }

  @Test(description = "Создать пользователя")
  @CitrusTest
  public void createUserTest() {
    String name = "Chucho";
    String job = "Ksandra";

    run(http()
            .client("restClientReqres")
            .send()
            .post("users")
            .message()
            .type("application/json")
            .body(new ObjectMappingPayloadBuilder(getRequestData(name, job), "objectMapper"))
    );

    run(http()
            .client("restClientReqres")
            .receive()
            .response(HttpStatus.CREATED)
            .message()
            .type("application/json")
            .body(new ObjectMappingPayloadBuilder(getResponseData(name, job), "objectMapper"))
    );
  }

  private CreateUserResponseDTO getResponseData(String name, String job) {
    CreateUserResponseDTO createUserResponse = new CreateUserResponseDTO();
    createUserResponse.setName(name);
    createUserResponse.setJob(job);
    createUserResponse.setId("@isNumber()@");
    createUserResponse.setCreatedAt("@ignore()@");

    return createUserResponse;
  }

  private CreateUserRequestDTO getRequestData(String name, String job) {
    CreateUserRequestDTO createUserRequest = new CreateUserRequestDTO();
    createUserRequest.setName(name);
    createUserRequest.setJob(job);

    return createUserRequest;
  }
}