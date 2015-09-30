package com.swifthorseman.oldcountry.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.swifthorseman.oldcountry.domain.Country;
import com.swifthorseman.oldcountry.repository.CountryRepository;
import com.swifthorseman.oldcountry.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Country.
 */
@RestController
@RequestMapping("/api")
public class CountryResource {

    private final Logger log = LoggerFactory.getLogger(CountryResource.class);

    @Inject
    private CountryRepository countryRepository;

    /**
     * POST  /countrys -> Create a new country.
     */
    @RequestMapping(value = "/countrys",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Country> createCountry(@Valid @RequestBody Country country) throws URISyntaxException {
        log.debug("REST request to save Country : {}", country);
        if (country.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new country cannot already have an ID").body(null);
        }
        Country result = countryRepository.save(country);
        return ResponseEntity.created(new URI("/api/countrys/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("country", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /countrys -> Updates an existing country.
     */
    @RequestMapping(value = "/countrys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Country> updateCountry(@Valid @RequestBody Country country) throws URISyntaxException {
        log.debug("REST request to update Country : {}", country);
        if (country.getId() == null) {
            return createCountry(country);
        }
        Country result = countryRepository.save(country);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("country", country.getId().toString()))
                .body(result);
    }

    /**
     * GET  /countrys -> get all the countrys.
     */
    @RequestMapping(value = "/countrys",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Country> getAllCountrys() {
        log.debug("REST request to get all Countrys");
        return countryRepository.findAll();
    }

    /**
     * GET  /countrys/:id -> get the "id" country.
     */
    @RequestMapping(value = "/countrys/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Country> getCountry(@PathVariable Long id) {
        log.debug("REST request to get Country : {}", id);
        return Optional.ofNullable(countryRepository.findOne(id))
            .map(country -> new ResponseEntity<>(
                country,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /countrys/:id -> delete the "id" country.
     */
    @RequestMapping(value = "/countrys/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        log.debug("REST request to delete Country : {}", id);
        countryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("country", id.toString())).build();
    }
}
