package io.nkh.hibernate.repository;

import io.nkh.hibernate.domain.CreditCard;
import io.nkh.hibernate.service.EncryptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCardRepositoryTest {
    final String CREDIT_CARD = "80734502893758";

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    EncryptionService encryptionService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void saveAndStoreCreditCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(CREDIT_CARD);
        creditCard.setCvv("312");
        creditCard.setExpirationDate("04/08/2023");

        System.out.println("Before save and flush");

        CreditCard savedCC = creditCardRepository.saveAndFlush(creditCard);

        System.out.println("Getting CC from DB: " + creditCard.getCreditCardNumber());

        System.out.println("CC at rest");

        System.out.println("CC encrypted: " + encryptionService.encrypt(CREDIT_CARD));

        Map<String, Object> dbRow = jdbcTemplate.queryForMap("SELECT * FROM credit_card WHERE id = " +
                savedCC.getId());

        String dbCardValue = (String) dbRow.get("credit_card_number");

        assertNotEquals(savedCC.getCreditCardNumber(), dbCardValue);
        assertEquals(dbCardValue, encryptionService.encrypt(CREDIT_CARD));

        CreditCard fetchedCC = creditCardRepository.findById(savedCC.getId()).get();

        assertEquals(savedCC.getCreditCardNumber(), fetchedCC.getCreditCardNumber());
    }


}