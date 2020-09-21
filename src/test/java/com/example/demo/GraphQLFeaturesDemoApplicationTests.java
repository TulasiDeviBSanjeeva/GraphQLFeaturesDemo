package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class GraphQLFeaturesDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    /*
    @Test
    public void testCustomers() throws IOException, JSONException {

        GraphQLResponse response = perform("customer.graphqls", null);

        assertTrue(response.isOk());

        String json = response.getRawResponse().getBody();
        System.out.println("Body: " + json);

        JSONArray jC = new JSONObject(json).getJSONObject("data").getJSONArray("customers");
        System.out.println(jC);

        ObjectMapper mapper = new ObjectMapper();

        List<Customer> Customers = mapper.readValue(jC.toString(), new TypeReference<List<Customer>>(){});

        Customers.forEach(c -> System.out.println(c.getName()));
    }*/

}
