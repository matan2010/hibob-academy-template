package com.hibob.academy.resource

import com.hibob.academy.service.OwnerService
import jakarta.ws.rs.Path
import org.springframework.stereotype.Component

@Component
@Path("/owner")
class OwnerResource(private val service: OwnerService) {


}