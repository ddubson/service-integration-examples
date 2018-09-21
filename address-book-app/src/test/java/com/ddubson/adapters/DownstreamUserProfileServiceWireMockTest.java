package com.ddubson.adapters;

import com.ddubson.AddressBookApp;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.http.UniformDistribution;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.ResourceAccessException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.hamcrest.Matchers.containsString;

@RunWith(MockitoJUnitRunner.class)
public class DownstreamUserProfileServiceWireMockTest {
	@Rule
	public WireMockRule userProfileServiceMock = new WireMockRule(Options.DYNAMIC_PORT);
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	private final UniformDistribution betweenThreeToFourSeconds = new UniformDistribution(3000, 4000);
	private UserProfileServiceAdapter adapter;

	@Before
	public void setUp() {
		AddressBookApp app = new AddressBookApp();
		String userProfileServiceUrl = String.format("http://localhost:%d", userProfileServiceMock.port());
		adapter = app.userProfileServiceAdapter(app.restTemplate(), userProfileServiceUrl);
	}

	@Test
	public void findAll_whenDownstreamServiceIsNotRespondingWithinTimeLimit_throwsAReadTimeOutException() {
		expectedException.expect(ResourceAccessException.class);
		expectedException.expectMessage(containsString("Read timed out"));

		stubFor(get("/api/users").willReturn(aResponse().withRandomDelay(betweenThreeToFourSeconds)));

		adapter.findAll();
	}

	@Test
	public void findAll_whenDownstreamServiceIsNotAvailable_throwsAReadTimeOutException() {
		expectedException.expect(ResourceAccessException.class);
		expectedException.expectMessage(containsString("Connection refused"));

		userProfileServiceMock.stop();

		adapter.findAll();
	}
}
