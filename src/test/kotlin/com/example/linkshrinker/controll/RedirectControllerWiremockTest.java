package com.example.linkshrinker.controll;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class RedirectControllerWiremockTest
{
    private final String PATH = "/api/aaAAbbBB";
    private final String BAD_PATH = "/api/zzZZxxXX";
    private final int REDIRECT_STATUS = 302;
    private final int NOT_FOUND = 404;
    private final String HEADER_NAME = "Location";
    private final String HEADER_VALUE = "http://www.eveonline.com";

    @LocalServerPort
    private int port;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(port).httpsPort(8443));

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void controller_WhenRequestSuccessfull_ShouldRedirectUs()
    {
        /*stubFor(get(urlEqualTo(PATH))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.valueOf(REDIRECT_STATUS).value())
                        .withHeader(HEADER_NAME, HEADER_VALUE)));

        ResponseEntity<Void> entity = restTemplate.exchange(PATH, HttpMethod.GET, null, Void.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.valueOf(REDIRECT_STATUS));*/

        //assertThat(entity.getHeaders().containsKey(HEADER_NAME)).isTrue();
        //assertThat(entity.getHeaders().containsValue(HEADER_VALUE)).isTrue();

        //verify(getRequestedFor(urlMatching("/api/resource")));

    }
}
