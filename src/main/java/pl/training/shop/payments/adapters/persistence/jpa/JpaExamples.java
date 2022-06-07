package pl.training.shop.payments.adapters.persistence.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import pl.training.shop.commons.jpa.Operator;
import pl.training.shop.commons.jpa.SearchCriteria;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import static pl.training.shop.commons.jpa.Operator.EQUAL;
import static pl.training.shop.commons.jpa.Operator.MATCH_START;

@EnableAsync
@Transactional
// @Component
@RequiredArgsConstructor
@Log
public class JpaExamples implements ApplicationRunner {

    private final JpaPaymentRepository paymentRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var id = UUID.randomUUID().toString();
        var payment = new PaymentEntity();
        payment.setId(id);
        payment.setStatus("CONFIRMED");
        payment.setTimestamp(Instant.now());
        payment.setValue(BigDecimal.valueOf(200));
        payment.setCurrency("PLN");
        paymentRepository.save(payment);

        /*paymentRepository.getPaymentProjectionById(id)
                .ifPresent(result -> log.info("Result: " + result.getDescription()));*/

        /*paymentRepository.byId(id);*/

        /*var examplePayment = new PaymentEntity();
        examplePayment.setStatus("CONFIRMED");
        var matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues();
        var result = paymentRepository.findAll(Example.of(examplePayment, matcher));
        log.info("Result: " + result);*/

        /*paymentRepository.getAll()
                .forEach(paymentEntity -> log.info(paymentEntity.toString()));*/

        /*var result = paymentRepository.getAllAsync();
        log.info("Is done: " + result.isDone());
        log.info("Result: " + result.get());*/

        /*var specification = new PaymentsSpecification(Set.of(
             new SearchCriteria("status", "CON", MATCH_START),
             new SearchCriteria("value", BigDecimal.valueOf(200), EQUAL)
        ));
        var result = paymentRepository.findAll(specification);
        log.info("Result: " + result);*/
    }

}