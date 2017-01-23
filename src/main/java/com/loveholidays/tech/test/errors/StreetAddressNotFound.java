package com.loveholidays.tech.test.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by chaitanyanaidu on 1/23/17.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="ADDRESS NOT FOUND")
public class StreetAddressNotFound extends RuntimeException {
}
