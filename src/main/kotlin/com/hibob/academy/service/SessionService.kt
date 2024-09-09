package com.hibob.academy.service
import com.hibob.academy.resource.AuthenticationResource
//import com.hibob.academy.resource.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*

@Component
class SessionService {
    companion object {

        const val SECRET_KEY = "seccghdhtfgh78798olkhgdhjkjhgfnhjkkopjoiret"
    }
    val date= Date()
    fun creatJwrToken(user: AuthenticationResource.User):String{
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .claim("email", user.email)
            .claim("username",user.name)
            .claim("isAdmin",user.isAdmin)
            .setExpiration(Date(date.time + 1000 * 60 * 60 * 24))
            .signWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray()), SignatureAlgorithm.HS256)
            .compact()
    }
}