package com.cleverlance.airlabs;

import com.cleverlance.airlabs.entity.airlabs.Airport;
import com.cleverlance.airlabs.repository.AirportRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AirportRepositoryIntegrationTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AirportRepository airportRepository;

    @Test
    public void whenFindByCode_thenReturnAirport() {
        // given
        Airport hanoi = new Airport("HNN", "Hanoi");
        entityManager.persistAndFlush(hanoi);

        // when
        Airport found = airportRepository.findByCode(hanoi.getCode());

        // then
        assertThat(found.getCode())
                .isEqualTo(hanoi.getCode());
    }
}
