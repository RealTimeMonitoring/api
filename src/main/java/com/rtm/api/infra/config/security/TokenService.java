package com.rtm.api.infra.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.rtm.api.domain.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService 
{
    @Value("${api.security.token_secret}")
    private String secret;
    
    public String generateToken( User user )
    {
        try 
        {
            Algorithm algorithm = Algorithm.HMAC256( secret );
            
            return JWT.create()
                      .withIssuer( "rtm-api" )
                      .withSubject( user.getEmail() )
                      .withExpiresAt( generateExpirationDate() )
                      .sign( algorithm );
        }
        
        catch ( JWTCreationException e )
        {
            throw new RuntimeException( "error generating token" );
        }
    }
    
    public String validateToken( String token )
    {
        try 
        {
            Algorithm algorithm = Algorithm.HMAC256( secret );
            
            return JWT.require( algorithm )
                      .withIssuer( "rtm-api" )
                      .build()
                      .verify( token )
                      .getSubject();
                
        }
        
        catch ( JWTVerificationException e )
        {
            return null;
        }
    }
    
    private Instant generateExpirationDate()
    {
        return LocalDateTime.now().plusMinutes( 5 ).toInstant( ZoneOffset.of( "-03:00" ) );
    }
}
