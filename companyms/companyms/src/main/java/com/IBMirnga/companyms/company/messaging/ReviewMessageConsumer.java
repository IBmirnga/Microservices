package com.IBMirnga.companyms.company.messaging;

import com.IBMirnga.companyms.company.CompanyService;
import com.IBMirnga.companyms.company.dto.ReviewMessage;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReviewMessageConsumer {

    private final CompanyService companyService;

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage) {
        companyService.updateCompanyRating(reviewMessage);
    }
}
