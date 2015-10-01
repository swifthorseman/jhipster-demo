package com.swifthorseman.oldcountry.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.swifthorseman.oldcountry.domain.CardProductType;
import com.swifthorseman.oldcountry.repository.CardProductTypeRepository;
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
 * REST controller for managing CardProductType.
 */
@RestController
@RequestMapping("/api")
public class CardProductTypeResource {

    private final Logger log = LoggerFactory.getLogger(CardProductTypeResource.class);

    @Inject
    private CardProductTypeRepository cardProductTypeRepository;

    /**
     * POST  /cardProductTypes -> Create a new cardProductType.
     */
    @RequestMapping(value = "/cardProductTypes",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CardProductType> createCardProductType(@Valid @RequestBody CardProductType cardProductType) throws URISyntaxException {
        log.debug("REST request to save CardProductType : {}", cardProductType);
        if (cardProductType.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new cardProductType cannot already have an ID").body(null);
        }
        CardProductType result = cardProductTypeRepository.save(cardProductType);
        return ResponseEntity.created(new URI("/api/cardProductTypes/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("cardProductType", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /cardProductTypes -> Updates an existing cardProductType.
     */
    @RequestMapping(value = "/cardProductTypes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CardProductType> updateCardProductType(@Valid @RequestBody CardProductType cardProductType) throws URISyntaxException {
        log.debug("REST request to update CardProductType : {}", cardProductType);
        if (cardProductType.getId() == null) {
            return createCardProductType(cardProductType);
        }
        CardProductType result = cardProductTypeRepository.save(cardProductType);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("cardProductType", cardProductType.getId().toString()))
                .body(result);
    }

    /**
     * GET  /cardProductTypes -> get all the cardProductTypes.
     */
    @RequestMapping(value = "/cardProductTypes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<CardProductType> getAllCardProductTypes() {
        log.debug("REST request to get all CardProductTypes");
        return cardProductTypeRepository.findAll();
    }

    /**
     * GET  /cardProductTypes/:id -> get the "id" cardProductType.
     */
    @RequestMapping(value = "/cardProductTypes/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CardProductType> getCardProductType(@PathVariable Long id) {
        log.debug("REST request to get CardProductType : {}", id);
        return Optional.ofNullable(cardProductTypeRepository.findOne(id))
            .map(cardProductType -> new ResponseEntity<>(
                cardProductType,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /cardProductTypes/:id -> delete the "id" cardProductType.
     */
    @RequestMapping(value = "/cardProductTypes/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCardProductType(@PathVariable Long id) {
        log.debug("REST request to delete CardProductType : {}", id);
        cardProductTypeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("cardProductType", id.toString())).build();
    }
}
