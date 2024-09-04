package com.assessment.controller;

import com.assessment.dto.CheckOutInputDTO;
import com.assessment.service.CheckoutService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class CheckoutControllerTest {

    @InjectMocks
    CheckoutController checkoutController;
    @Mock
    CheckoutService checkoutService;
    @Test
    public void testCreateContractRentalDaysLessThanOne() {
        var checkOutInputDto = CheckOutInputDTO
                .builder()
                .rentalDayCount(0)
                .discountPercent(0)
                .toolCode("JAKD")
                .checkoutDate(LocalDate.now())
                .build();
        var checkOutOutputDto = checkoutController.createContract(checkOutInputDto);
        assert checkOutOutputDto
                .getStatusCode()
                .equals(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testCreateContractDiscountPercentMoreThanHundred() {
        var checkOutInputDto = CheckOutInputDTO
                .builder()
                .rentalDayCount(1)
                .discountPercent(101)
                .toolCode("JAKD")
                .checkoutDate(LocalDate.now())
                .build();
        var checkOutOutputDto = checkoutController.createContract(checkOutInputDto);
        assert checkOutOutputDto
                .getStatusCode()
                .equals(HttpStatus.BAD_REQUEST);
    }

}
