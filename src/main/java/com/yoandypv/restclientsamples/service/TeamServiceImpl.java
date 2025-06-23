package com.yoandypv.restclientsamples.service;

import com.yoandypv.restclientsamples.service.dto.team.TeamResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class TeamServiceImpl extends AbstractClient implements TeamService {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TeamServiceImpl.class);

    public TeamServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
        
    }
    @Override
    public TeamResponse findAll(String token) {
        String uri = baseUrl + "/team";
        HttpEntity<Void> requestEntity = new HttpEntity<>(this.buildAuthToken(token));
        ResponseEntity<TeamResponse> response = restTemplate.exchange(
                uri, HttpMethod.GET, requestEntity , TeamResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
 
        	String status = response.getBody().status;
            log.info("Successfully user creation: {}", status);
            return response.getBody();
        }
        log.error("Error in user creation - httpStatus was: {}", response.getStatusCode().toString());
        throw new RuntimeException("Error");
    }


}
