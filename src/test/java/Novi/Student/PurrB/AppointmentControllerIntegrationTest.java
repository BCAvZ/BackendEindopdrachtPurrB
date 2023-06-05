//package Novi.Student.PurrB;
//
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
//
//@SpringBootTest
//@AutoConfigureMockMvc(addFilters = false)
//@ActiveProfiles("test")
//public class AppointmentControllerIntegrationTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    @WithMockUser(username="testuser", roles="USER")
//    void shouldCreateCorrectAppointment() throws Exception {
//
//        String authHeader = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIZW5rIiwiaWF0IjoxNjg1NDY3MDY4LCJleHAiOjE2ODYzMzEwNjh9.3UrkmdCHmqmxCgq-9AkbSQEhEZA1vz4smeBd84Zce48";
//
//        String requestJson = """
//                {
//                    "startDate" : "1 january 2023",
//                    "endDate" : "1 january 2033",
//                    "time" : "twaalf uur",
//                    "notes" : "kat is incontinent"
//                }
//        """;
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post("/appointments").header("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIZW5rIiwiaWF0IjoxNjg1NDY3MDY4LCJleHAiOjE2ODYzMzEwNjh9.3UrkmdCHmqmxCgq-9AkbSQEhEZA1vz4smeBd84Zce48", authHeader)
//                        .contentType(APPLICATION_JSON_UTF8)
//                        .content(requestJson))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isCreated());
//    }
//
//
//}
