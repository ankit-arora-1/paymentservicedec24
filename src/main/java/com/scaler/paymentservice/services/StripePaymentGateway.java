package com.scaler.paymentservice.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentGateway implements PaymentGateway {
    private String apiKey = "sk_test_51OmDwvSCqKXtl1vtfQnyLxpJOWZaXRXGqz6TqI98LduyoiDWjUqwcxdI9n6STbPsUJzXbNaovYRf0VCKAmg8yR6z00Y60AQJ4R";
    @Override
    public String generatePaymentLink() {
        try {
            Stripe.apiKey = this.apiKey;

            Price price = getPrice();

            PaymentLinkCreateParams params =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(price.getId())
                                            .setQuantity(1L)
                                            .build()
                            ).setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                                    .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                    .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                            .setUrl("https://google.com/?trx_id=" + "abc123").build()).build())
                            .build();
            PaymentLink paymentLink = PaymentLink.create(params);
            return paymentLink.getUrl();
        }catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private Price getPrice() {
        try {
            PriceCreateParams params =
                    PriceCreateParams.builder()
                            .setCurrency("inr")
                            .setUnitAmount(1000L)
                            .setProductData(
                                    PriceCreateParams
                                            .ProductData
                                            .builder()
                                            .setName("iPhone")
                                            .build()
                            )
                            .build();
            Price price = Price.create(params);
            return price;
        }catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
