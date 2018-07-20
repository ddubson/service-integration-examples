package com.ddubson.service.user.profile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserProfileController.class)
public class UserProfileControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserProfileRepository userProfileRepository;

	@Test
	public void fetchAll_whenQueried_returnsAllUserProfiles() throws Exception {
		UserProfile userProfile1 = UserProfile.builder().firstName("John").lastName("Smith").build();
		UserProfile userProfile2 = UserProfile.builder().firstName("Jane").lastName("Adams").build();
		when(userProfileRepository.fetchAll()).thenReturn(asList(userProfile1, userProfile2));

		mockMvc.perform(get("/api/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[0].lastName").value("Smith"));
	}
}