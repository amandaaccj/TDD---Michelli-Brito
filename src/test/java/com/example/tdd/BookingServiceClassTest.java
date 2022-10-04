package com.example.tdd;

import com.example.tdd.model.BookingModel;
import com.example.tdd.repository.BookingRepository;
import com.example.tdd.service.BookingService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceClassTest {

    @TestConfiguration
    static class BookingServiceTestConfiguration{

        @Bean
        public BookingService bookingService(){
            return new BookingService();
        }
    }

    @Autowired
    BookingService bookingService;

    @MockBean
    BookingRepository bookingRepository;

    @Test
    public void bookingTestServiceDayCalculator(){
        String name = "Amanda";
        int days = bookingService.daysCalculatorWithDatabase(name);

        Assertions.assertEquals(days, 10);
    }

    @Before
    public void setup(){
        LocalDate checkIn = LocalDate.parse("2020-11-10");
        LocalDate checkOut = LocalDate.parse("2020-11-20");
        BookingModel bookingModel = new BookingModel("1", "Amanda", checkIn, checkOut, 2);

        Mockito.when(bookingRepository.findReserveName(bookingModel.getReserveName()))
            .thenReturn(java.util.Optional.of(bookingModel));
    }

}
