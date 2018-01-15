package com.example.demo.Persons;

import static org.junit.Assert.assertNotNull;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.BackEndApplication;
import com.example.demo.repo.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackEndApplication.class)
@WebAppConfiguration
public class PersonRestControllerTest {

	  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
	            MediaType.APPLICATION_JSON.getSubtype(),
	            Charset.forName("utf8"));

	  private MockMvc mockMvc;
	  
	  private HttpMessageConverter mappingJackson2HttpMessageConverter;
	  
	  private PersonRepository personRepository;
	  
	  @Autowired
	  private WebApplicationContext webApplicationContext;
	
	  @Autowired
	  void setConverters(HttpMessageConverter<?>[] converters) {

	        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
	            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
	            .findAny()
	            .orElse(null);

	        assertNotNull("the JSON message converter must not be null",
	                this.mappingJackson2HttpMessageConverter);
	    }
	  
	  
	  @Before
	    public void setup() throws Exception {
	        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	        this.personRepository.deleteAllInBatch();
	    }
	
}
