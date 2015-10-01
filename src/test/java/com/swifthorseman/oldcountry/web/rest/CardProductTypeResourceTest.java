package com.swifthorseman.oldcountry.web.rest;

import com.swifthorseman.oldcountry.Application;
import com.swifthorseman.oldcountry.domain.CardProductType;
import com.swifthorseman.oldcountry.repository.CardProductTypeRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.joda.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the CardProductTypeResource REST controller.
 *
 * @see CardProductTypeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CardProductTypeResourceTest {

    private static final String DEFAULT_CARD_BAND = "SAMPLE_TEXT";
    private static final String UPDATED_CARD_BAND = "UPDATED_TEXT";
    private static final String DEFAULT_CARD_PRODUCT_TYPE_CODE = "SAMPLE_TEXT";
    private static final String UPDATED_CARD_PRODUCT_TYPE_CODE = "UPDATED_TEXT";
    private static final String DEFAULT_CARD_PRODUCT_TYPE_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_CARD_PRODUCT_TYPE_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_CARD_SCHEME_ID = "SAMPLE_TEXT";
    private static final String UPDATED_CARD_SCHEME_ID = "UPDATED_TEXT";
    private static final String DEFAULT_CARD_SCHEME_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_CARD_SCHEME_NAME = "UPDATED_TEXT";

    private static final LocalDate DEFAULT_LOADING_DATE = new LocalDate(0L);
    private static final LocalDate UPDATED_LOADING_DATE = new LocalDate();
    private static final String DEFAULT_SCHEME = "SAMPLE_TEXT";
    private static final String UPDATED_SCHEME = "UPDATED_TEXT";
    private static final String DEFAULT_DEBIT_CREDIT = "SAMPLE_TEXT";
    private static final String UPDATED_DEBIT_CREDIT = "UPDATED_TEXT";
    private static final String DEFAULT_DISPLAY_FLAG = "SAMPLE_TEXT";
    private static final String UPDATED_DISPLAY_FLAG = "UPDATED_TEXT";

    @Inject
    private CardProductTypeRepository cardProductTypeRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCardProductTypeMockMvc;

    private CardProductType cardProductType;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CardProductTypeResource cardProductTypeResource = new CardProductTypeResource();
        ReflectionTestUtils.setField(cardProductTypeResource, "cardProductTypeRepository", cardProductTypeRepository);
        this.restCardProductTypeMockMvc = MockMvcBuilders.standaloneSetup(cardProductTypeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        cardProductType = new CardProductType();
        cardProductType.setCard_band(DEFAULT_CARD_BAND);
        cardProductType.setCard_product_type_code(DEFAULT_CARD_PRODUCT_TYPE_CODE);
        cardProductType.setCard_product_type_name(DEFAULT_CARD_PRODUCT_TYPE_NAME);
        cardProductType.setCard_scheme_id(DEFAULT_CARD_SCHEME_ID);
        cardProductType.setCard_scheme_name(DEFAULT_CARD_SCHEME_NAME);
        cardProductType.setLoading_date(DEFAULT_LOADING_DATE);
        cardProductType.setScheme(DEFAULT_SCHEME);
        cardProductType.setDebit_credit(DEFAULT_DEBIT_CREDIT);
        cardProductType.setDisplay_flag(DEFAULT_DISPLAY_FLAG);
    }

    @Test
    @Transactional
    public void createCardProductType() throws Exception {
        int databaseSizeBeforeCreate = cardProductTypeRepository.findAll().size();

        // Create the CardProductType

        restCardProductTypeMockMvc.perform(post("/api/cardProductTypes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(cardProductType)))
                .andExpect(status().isCreated());

        // Validate the CardProductType in the database
        List<CardProductType> cardProductTypes = cardProductTypeRepository.findAll();
        assertThat(cardProductTypes).hasSize(databaseSizeBeforeCreate + 1);
        CardProductType testCardProductType = cardProductTypes.get(cardProductTypes.size() - 1);
        assertThat(testCardProductType.getCard_band()).isEqualTo(DEFAULT_CARD_BAND);
        assertThat(testCardProductType.getCard_product_type_code()).isEqualTo(DEFAULT_CARD_PRODUCT_TYPE_CODE);
        assertThat(testCardProductType.getCard_product_type_name()).isEqualTo(DEFAULT_CARD_PRODUCT_TYPE_NAME);
        assertThat(testCardProductType.getCard_scheme_id()).isEqualTo(DEFAULT_CARD_SCHEME_ID);
        assertThat(testCardProductType.getCard_scheme_name()).isEqualTo(DEFAULT_CARD_SCHEME_NAME);
        assertThat(testCardProductType.getLoading_date()).isEqualTo(DEFAULT_LOADING_DATE);
        assertThat(testCardProductType.getScheme()).isEqualTo(DEFAULT_SCHEME);
        assertThat(testCardProductType.getDebit_credit()).isEqualTo(DEFAULT_DEBIT_CREDIT);
        assertThat(testCardProductType.getDisplay_flag()).isEqualTo(DEFAULT_DISPLAY_FLAG);
    }

    @Test
    @Transactional
    public void getAllCardProductTypes() throws Exception {
        // Initialize the database
        cardProductTypeRepository.saveAndFlush(cardProductType);

        // Get all the cardProductTypes
        restCardProductTypeMockMvc.perform(get("/api/cardProductTypes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(cardProductType.getId().intValue())))
                .andExpect(jsonPath("$.[*].card_band").value(hasItem(DEFAULT_CARD_BAND.toString())))
                .andExpect(jsonPath("$.[*].card_product_type_code").value(hasItem(DEFAULT_CARD_PRODUCT_TYPE_CODE.toString())))
                .andExpect(jsonPath("$.[*].card_product_type_name").value(hasItem(DEFAULT_CARD_PRODUCT_TYPE_NAME.toString())))
                .andExpect(jsonPath("$.[*].card_scheme_id").value(hasItem(DEFAULT_CARD_SCHEME_ID.toString())))
                .andExpect(jsonPath("$.[*].card_scheme_name").value(hasItem(DEFAULT_CARD_SCHEME_NAME.toString())))
                .andExpect(jsonPath("$.[*].loading_date").value(hasItem(DEFAULT_LOADING_DATE.toString())))
                .andExpect(jsonPath("$.[*].scheme").value(hasItem(DEFAULT_SCHEME.toString())))
                .andExpect(jsonPath("$.[*].debit_credit").value(hasItem(DEFAULT_DEBIT_CREDIT.toString())))
                .andExpect(jsonPath("$.[*].display_flag").value(hasItem(DEFAULT_DISPLAY_FLAG.toString())));
    }

    @Test
    @Transactional
    public void getCardProductType() throws Exception {
        // Initialize the database
        cardProductTypeRepository.saveAndFlush(cardProductType);

        // Get the cardProductType
        restCardProductTypeMockMvc.perform(get("/api/cardProductTypes/{id}", cardProductType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(cardProductType.getId().intValue()))
            .andExpect(jsonPath("$.card_band").value(DEFAULT_CARD_BAND.toString()))
            .andExpect(jsonPath("$.card_product_type_code").value(DEFAULT_CARD_PRODUCT_TYPE_CODE.toString()))
            .andExpect(jsonPath("$.card_product_type_name").value(DEFAULT_CARD_PRODUCT_TYPE_NAME.toString()))
            .andExpect(jsonPath("$.card_scheme_id").value(DEFAULT_CARD_SCHEME_ID.toString()))
            .andExpect(jsonPath("$.card_scheme_name").value(DEFAULT_CARD_SCHEME_NAME.toString()))
            .andExpect(jsonPath("$.loading_date").value(DEFAULT_LOADING_DATE.toString()))
            .andExpect(jsonPath("$.scheme").value(DEFAULT_SCHEME.toString()))
            .andExpect(jsonPath("$.debit_credit").value(DEFAULT_DEBIT_CREDIT.toString()))
            .andExpect(jsonPath("$.display_flag").value(DEFAULT_DISPLAY_FLAG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCardProductType() throws Exception {
        // Get the cardProductType
        restCardProductTypeMockMvc.perform(get("/api/cardProductTypes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCardProductType() throws Exception {
        // Initialize the database
        cardProductTypeRepository.saveAndFlush(cardProductType);

		int databaseSizeBeforeUpdate = cardProductTypeRepository.findAll().size();

        // Update the cardProductType
        cardProductType.setCard_band(UPDATED_CARD_BAND);
        cardProductType.setCard_product_type_code(UPDATED_CARD_PRODUCT_TYPE_CODE);
        cardProductType.setCard_product_type_name(UPDATED_CARD_PRODUCT_TYPE_NAME);
        cardProductType.setCard_scheme_id(UPDATED_CARD_SCHEME_ID);
        cardProductType.setCard_scheme_name(UPDATED_CARD_SCHEME_NAME);
        cardProductType.setLoading_date(UPDATED_LOADING_DATE);
        cardProductType.setScheme(UPDATED_SCHEME);
        cardProductType.setDebit_credit(UPDATED_DEBIT_CREDIT);
        cardProductType.setDisplay_flag(UPDATED_DISPLAY_FLAG);
        

        restCardProductTypeMockMvc.perform(put("/api/cardProductTypes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(cardProductType)))
                .andExpect(status().isOk());

        // Validate the CardProductType in the database
        List<CardProductType> cardProductTypes = cardProductTypeRepository.findAll();
        assertThat(cardProductTypes).hasSize(databaseSizeBeforeUpdate);
        CardProductType testCardProductType = cardProductTypes.get(cardProductTypes.size() - 1);
        assertThat(testCardProductType.getCard_band()).isEqualTo(UPDATED_CARD_BAND);
        assertThat(testCardProductType.getCard_product_type_code()).isEqualTo(UPDATED_CARD_PRODUCT_TYPE_CODE);
        assertThat(testCardProductType.getCard_product_type_name()).isEqualTo(UPDATED_CARD_PRODUCT_TYPE_NAME);
        assertThat(testCardProductType.getCard_scheme_id()).isEqualTo(UPDATED_CARD_SCHEME_ID);
        assertThat(testCardProductType.getCard_scheme_name()).isEqualTo(UPDATED_CARD_SCHEME_NAME);
        assertThat(testCardProductType.getLoading_date()).isEqualTo(UPDATED_LOADING_DATE);
        assertThat(testCardProductType.getScheme()).isEqualTo(UPDATED_SCHEME);
        assertThat(testCardProductType.getDebit_credit()).isEqualTo(UPDATED_DEBIT_CREDIT);
        assertThat(testCardProductType.getDisplay_flag()).isEqualTo(UPDATED_DISPLAY_FLAG);
    }

    @Test
    @Transactional
    public void deleteCardProductType() throws Exception {
        // Initialize the database
        cardProductTypeRepository.saveAndFlush(cardProductType);

		int databaseSizeBeforeDelete = cardProductTypeRepository.findAll().size();

        // Get the cardProductType
        restCardProductTypeMockMvc.perform(delete("/api/cardProductTypes/{id}", cardProductType.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<CardProductType> cardProductTypes = cardProductTypeRepository.findAll();
        assertThat(cardProductTypes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
