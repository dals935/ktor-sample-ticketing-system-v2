package com.example.routes

import com.example.controllers.TicketingController
import com.example.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.lang.Exception

fun Route.ticketRouting(){
    route("/Ticket")
    {
        post {
            try{
////                val catagData = TicketingController().getCategAndSubCat()
//                call.respond(catagData)
                val ticketStorage = call.receive<TicketPost>()
                val (userId,
                    category,
                    subCategory,
                    createdBy,
                    reportedBy,
                    additionalDetails,
                    floor,
                    reportedVia,
                    areaLocation,
                    assignTo) = ticketStorage

                /*if ( userId == "" )
                    return@post call.respond(ResponseModel(417, "Input User ID"))*/
                if ( category == "" )
                    return@post call.respond(ResponseModel(417, "Input Category"))
                if ( subCategory == "" )
                    return@post call.respond(ResponseModel(417, "Input Sub-Category"))
                if ( createdBy == "" )
                    return@post call.respond(ResponseModel(417, "Input Creator"))
                if ( reportedBy == "" )
                    return@post call.respond(ResponseModel(417, "Input Reporter"))
                /*if ( additionalDetails == "" )
                    return@post call.respond(ResponseModel(417, "Input Additional Details"))*/
                if ( floor == "" )
                    return@post call.respond(ResponseModel(417, "Input Floor"))
                if ( reportedVia == "" )
                    return@post call.respond(ResponseModel(417, "Input Reported Via"))
                if ( areaLocation == "" )
                    return@post call.respond(ResponseModel(417, "Input Area / Location"))
                if ( assignTo == "" )
                    return@post call.respond(ResponseModel(417, "Input Assign To"))

                TicketingController().postTicketData(userId,
                    category,
                    subCategory,
                    createdBy,
                    reportedBy,
                    additionalDetails,
                    floor, reportedVia,
                    areaLocation,
                    assignTo)
                return@post call.respond(ResponseModel(201, "Ticket Created"))

            } catch (e: Exception) {
                return@post call.respond(ResponseModel(500, "Server error: $e"))
            }
        }
    }

    route("/get_All_Ticket_Data")
    {
        get{
            try {

                val ticketData = TicketingController().getTicketData()
                call.respond(ticketData)

            } catch (e: Exception) {
                return@get call.respond(ResponseModel(500, "Server error: $e"))
            }
        }
    }

    route("/get_Ticket_Data/{record_id}")
    {
        get{
            try{

                val ticketData = TicketingController().getTicketData()
                val record_id = (call.parameters["record_id"] ?: return@get call.respond(HttpStatusCode.BadRequest))
                val ticket = ticketData.find { it.recordId.toString() == record_id } ?: return@get call.respond(HttpStatusCode.NotFound, (ResponseModel(404, "Ticked Record ID #$record_id is Not Found")))
                call.respond(HttpStatusCode.Found, (ticket))
                return@get

            } catch (e: Exception) {
                return@get call.respond(HttpStatusCode.BadRequest, (ResponseModel(500, "Server error: $e")))
            }
        }
    }

    route("/put_Ticket_Data/{record_id}")
    {
        put{
            try{
                val ticketData = call.receive<TicketPut>()
                val (category,
                    subCategory,
                    createdBy,
                    reportedBy,
                    additionalDetails,
                    floor,
                    reportedVia,
                    areaLocation,
                    assignTo, ) = ticketData

                val ticketStorage = TicketingController().getTicketData()

                val recordid = (call.parameters["record_id"] ?: return@put call.respond(HttpStatusCode.BadRequest, "Please Input Correct ID Number"))

                if(ticketStorage.removeIf { it.recordId.toString() == recordid })
                {
                    TicketingController().putTicketData(
                        category,
                        subCategory,
                        createdBy,
                        reportedBy,
                        additionalDetails,
                        floor,
                        reportedVia,
                        areaLocation,
                        assignTo,
                        recordid
                    )
                    return@put call.respond(ResponseModel(201, "Ticket with Record ID #$recordid is Updated"))

                } else

                    return@put call.respond(ResponseModel(404, "Ticket with Record ID #$recordid is Not Found"))

            } catch (e: Exception) {
                return@put call.respond(ResponseModel(500, "Server error: $e"))
            }
        }
    }

    route("/delete_Ticket_data/{record_id}")
    {
        delete {
            val ticketData = TicketingController().getTicketData()
            val recordid = (call.parameters["record_id"] ?: return@delete call.respond(HttpStatusCode.BadRequest))
            ticketData.find { it.recordId.toString() == recordid } ?: return@delete call.respond(ResponseModel(404, "Ticket with Record ID #$recordid is Not Found"))
            TicketingController().deleteTickeData(recordid)
            return@delete call.respond(ResponseModel(201, "Ticket with Record ID #$recordid is Deleted"))
        }
    }

    route("/get_Catag_data")
    {
        get {
            try {
                val catagData = TicketingController().getCateg()
                call.respond(catagData)

            } catch (e: Exception) {
                return@get call.respond(ResponseModel(500, "Server error: $e"))
            }
        }
    }

    route("/get_Sub_Catag_data")
    {
        get {
            try {
                val catagData = TicketingController().getSubCatag()
                call.respond(catagData)

            } catch (e: Exception) {
                return@get call.respond(ResponseModel(500, "Server error: $e"))
            }
        }
    }

    route("/post_Dummy_Data")
    {

        get{
            try {
                val dummyData = TicketingController().getDummyData()
                call.respond(dummyData)
            }catch (e: Exception) {
                return@get call.respond(ResponseModel(500, "Server error: $e"))
            }
        }
        post {
            try {
                val dummyStorage = call.receive<postDummyDataModel>()
                val (userId,
                    personnelAccountStatus,
                    systemAccountStatus,
                    Name,
                    companyEmail,
                    localNo,
                    companyName,
                    companyShortName,
                    departmentName,
                    imageName,
                    positionName,
                    sectionName) = dummyStorage

                TicketingController().postDummyData(userId,
                    personnelAccountStatus,
                    systemAccountStatus,
                    Name,
                    companyEmail,
                    localNo,
                    companyName,
                    companyShortName,
                    departmentName,
                    imageName,
                    positionName,
                    sectionName)

                return@post call.respond(ResponseModel(201, "Dummy Data Created"))

            } catch (e: Exception) {
                return@post call.respond(ResponseModel(500, "Server error: $e"))
            }
        }
    }
}