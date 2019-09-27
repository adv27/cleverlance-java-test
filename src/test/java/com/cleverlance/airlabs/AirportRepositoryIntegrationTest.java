package com.cleverlance.airlabs;

import com.cleverlance.airlabs.dao.AirportDAO;
import com.cleverlance.airlabs.entity.airlabs.Airport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AirportRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AirportDAO airportDAO;

    @Test
    public void whenFindByCode_thenReturnAirport() {
        // given
        Airport hanoi = new Airport("HNN", "Hanoi");
        entityManager.persist(hanoi);
        entityManager.flush();

        // when
        Airport found = airportDAO.findByCode(hanoi.getCode());

        // then
        assertThat(found.getCode())
                .isEqualTo(hanoi.getCode());
    }
}
