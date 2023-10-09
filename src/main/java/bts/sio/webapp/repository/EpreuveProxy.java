package bts.sio.webapp.repository;


import bts.sio.webapp.CustomProperties;
import bts.sio.webapp.model.Epreuve;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class EpreuveProxy {
    @Autowired
    private CustomProperties props;

    /**
     * Get all epreuves
     * @return An iterable of all epreuve
     */
    public Iterable<Epreuve> getEpreuves() {

        String baseApiUrl = props.getApiUrl();
        String getEpreuvesUrl = baseApiUrl + "/epreuves";
        System.out.println("url=" + getEpreuvesUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Epreuve>> response = restTemplate.exchange(
                getEpreuvesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Epreuve>>() {}
        );

        log.debug("Get Athletes call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Get an epreuve by the id
     * @param id The id of the epreuve
     * @return The epreuve which matches the id
     */
    public Epreuve getEpreuve(int id) {
        String baseApiUrl = props.getApiUrl();
        String getEmployeeUrl = baseApiUrl + "/epreuve/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Epreuve> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                Epreuve.class
        );

        log.debug("Get Athlete call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Add a new epreuve
     * @param a A new epreuve (without an id)
     * @return The epreuve full filled (with an id)
     */
    public Epreuve createEpreuve(Epreuve a) {

        String baseApiUrl = props.getApiUrl();
        String createEpreuveUrl = baseApiUrl + "/epreuve";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Epreuve> request = new HttpEntity<Epreuve>(a);
        ResponseEntity<Epreuve> response = restTemplate.exchange(
                createEpreuveUrl,
                HttpMethod.POST,
                request,
                Epreuve.class);

        log.debug("Create Epreuve call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Update an athlete - using the PUT HTTP Method.
     * @param e Existing athlete to update
     */
    public Epreuve updateEpreuve(Epreuve e) {
        String baseApiUrl = props.getApiUrl();
        String updateEpreuveUrl = baseApiUrl + "/epreuve/" + e.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Epreuve> request = new HttpEntity<Epreuve>(e);
        ResponseEntity<Epreuve> response = restTemplate.exchange(
                updateEpreuveUrl,
                HttpMethod.PUT,
                request,
                Epreuve.class);

        log.debug("Update Epreuve call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /*
     * Delete an epreuve using exchange method of RestTemplate
     * instead of delete method in order to log the response status code.
     * @param e The epreuve to delete
     */
    public void deleteEpreuve(int id) {
        String baseApiUrl = props.getApiUrl();
        String deleteEpreuveUrl = baseApiUrl + "/epreuve/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteEpreuveUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Athlete call " + response.getStatusCode().toString());
    }

    /**
     * Get epreuves by sport ID
     * @param sport_id The ID of the sport
     * @return An iterable of epreuves associated with the sport
     */
    public Iterable<Epreuve> getEpreuvesBySport_id(Long sport_id) {
        String baseApiUrl = props.getApiUrl();
        String getEpreuvesBySportUrl = baseApiUrl + "/epreuves/sport/" + sport_id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Epreuve>> response = restTemplate.exchange(
                getEpreuvesBySportUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Epreuve>>() {}
        );

        log.debug("Get Epreuves by Sport call " + response.getStatusCode().toString());

        return response.getBody();
    }
}