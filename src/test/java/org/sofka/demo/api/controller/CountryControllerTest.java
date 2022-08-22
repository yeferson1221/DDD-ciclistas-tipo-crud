package org.sofka.demo.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sofka.demo.repository.CountryRepository;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CountryControllerTest {
    @Mock
    private CountryRepository countryRepository;

    @BeforeEach
    public void setUp(){

    }
}