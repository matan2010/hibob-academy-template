package com.hibob.academy.resource

import com.hibob.academy.service.OwnerService
import com.hibob.academy.service.PetService
import jakarta.ws.rs.Path
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@Path("/pet")
class PetResource (private val petService: PetService) {
}