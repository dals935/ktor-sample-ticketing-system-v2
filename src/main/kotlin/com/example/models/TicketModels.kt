package com.example.models

import kotlinx.serialization.Serializable
import javax.security.auth.Destroyable

@Serializable
data class TicketGet(
    val recordId: Int,
    val ticketPost: TicketPost
): Destroyable

@Serializable
data class TicketPost(
    val inputUserID: String? = null,
    val inputCategory: String,
    val inputSubcategory: String,
    val inputCreatedby: String,
    val inputReportedby: String,
    val inputDetails: String?,
    val inputFloor: String,
    val inputReportedvia: String,
    val inputArealoc: String,
    val inputAssignto: String
)

@Serializable
data class TicketPut(
    val category: String,
    val subCategory: String,
    val createdBy: String,
    val reportedBy: String,
    val additionalDetails: String,
    val floor: String,
    val reportedVia: String,
    val areaLocation: String,
    val assignTo: String
)

@Serializable
data class ResponseModel
    (
    var code: Int,
    var status: String
)

@Serializable
data class Categories
    (
    val category_id: Int,
    val catergory_name: String
)

@Serializable
data class SubCategories(
    val category_id: Int,
    val sub_catag_id: Int,
    val sub_catag_name: String
)

@Serializable
data class postDummyDataModel(
    val userId: String,
    val personnelAccountStatus: String,
    val systemAccountStatus: String,
    val Name: String,
    val companyEmail: String,
    val localNo: String,
    val companyName: String,
    val companyShortName: String,
    val departmentName: String,
    val imageName: String,
    val positionName: String,
    val sectionName: String,
)