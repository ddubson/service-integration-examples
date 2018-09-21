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
	private Repository<UserProfile, Long> userProfileRepository;

	@Test
	public void fetchAll_whenQueried_returnsAllUserProfiles() throws Exception {
		UserProfile userProfile1 = UserProfile.builder().userId(1L).firstName("John").lastName("Smith").build();
		UserProfile userProfile2 = UserProfile.builder().userId(2L).firstName("Jane").lastName("Adams").build();
		when(userProfileRepository.fetchAll()).thenReturn(asList(userProfile1, userProfile2));

		mockMvc.perform(get("/api/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].userId").value(1))
				.andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[0].lastName").value("Smith"));
	}

	@Test
	public void findById_whenProvidedValidUserId_returnsSingleResultWithUserInformation() throws Exception {
		UserProfile userProfile = UserProfile.builder().userId(2L).firstName("John").lastName("Smith").build();
		when(userProfileRepository.findById(2L)).thenReturn(userProfile);

		mockMvc.perform(get(String.format("/api/users/%d", 2)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userId").value(2))
				.andExpect(jsonPath("$.firstName").value("John"))
				.andExpect(jsonPath("$.lastName").value("Smith"));
	}

	@Test
	public void findById_whenProvidedAValueThatIsNotAcceptable_returnsBadRequest() throws Exception {
		mockMvc.perform(get("/api/users/BAD_VALUE")).andExpect(status().isBadRequest());
	}

	@Test
	public void test() throws Exception {
		mockMvc.perform(get("/api/kt/users/1")).andExpect(status().isOk());
	}
}