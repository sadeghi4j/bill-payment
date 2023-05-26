package com.sadeghi.billpayment.controller;

import com.sadeghi.billpayment.config.JwtTokenUtil;
import com.sadeghi.billpayment.model.JwtRequest;
import com.sadeghi.billpayment.model.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author Ali Sadeghi
 * Created at 5/18/23 - 8:09 PM
 */

@Tag(name = "Authentication Controller", description = "کنترلر مخصوص احراز هویت")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtAuthenticationController {

    final AuthenticationManager authenticationManager;
    final JwtTokenUtil jwtTokenUtil;

    @Operation(summary = "احراز هویت",
            responses = {
                    @ApiResponse(responseCode = "200", description = "JWT Token"),
                    @ApiResponse(responseCode = "400", description = "INVALID_CREDENTIALS")
            })
    @PostMapping(value = "/authenticate", produces = "application/json", consumes = "application/json")
    public ResponseEntity<JwtResponse> generateAuthenticationToken(

            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Username and Password", required = true,
                    content = @Content(schema = @Schema(implementation = JwtRequest.class)))

            @RequestBody JwtRequest authenticationRequest) {
        Authentication authentication = authenticate(authenticationRequest.username(), authenticationRequest.password());
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private Authentication authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new RuntimeException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("INVALID_CREDENTIALS", e);
        }
    }
}
