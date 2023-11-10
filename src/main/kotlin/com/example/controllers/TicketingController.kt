package com.example.controllers

import com.example.config.TicketingConfig
import com.example.models.*
import com.example.queries.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TicketingController {
    fun postTicketData(
        userId: String?,
        category: String,
        subCategory: String,
        createdBy: String,
        reportedBy: String,
        additionalDetails: String?,
        floor: String,
        reportedVia: String,
        areaLocation: String,
        assignedTo: String
    )
    {
        TicketingConfig().connect()
            .use { con -> con.prepareStatement(postTicketData)
                .use {
                    it.setString(1,userId ?: "USR-001")
                    it.setString(2,category)
                    it.setString(3,subCategory)
                    it.setString(4,createdBy)
                    it.setString(5,reportedBy)
                    it.setString(6,additionalDetails)
                    it.setString(7,floor)
                    it.setString(8,reportedVia)
                    it.setString(9,areaLocation)
                    it.setString(10,assignedTo)

                    it.executeUpdate()
                }
            }
    }

    fun getTicketData(): MutableList<TicketGet> {
        val connect = TicketingConfig().connect()
        connect.use { connection ->
            val tickData = mutableListOf<TicketGet>()
            connect.prepareStatement(getTicketData).use {
                run {
                    it.executeQuery().use { result ->
                        while (result.next()) {
                            val record_id = result.getInt("record_id")
                            val userId = result.getString("user_id")
                            val category = result.getString("category")
                            val subCategory = result.getString("sub_category")
                            val createdBy = result.getString("created_by")
                            val reportedBy = result.getString("reported_by")
                            val additionalDetails = result.getString("additional_details")
                            val floor = result.getString("floor")
                            val reportedVia = result.getString("reported_via")
                            val areaLocation = result.getString("area_location")
                            val assignedTo = result.getString("assign_to")

                            val ticketPost = TicketPost(
                                userId,
                                category,
                                subCategory,
                                createdBy,
                                reportedBy,
                                additionalDetails,
                                floor,
                                reportedVia,
                                areaLocation,
                                assignedTo
                            )

                            tickData.add(TicketGet(record_id, ticketPost))
                        }
                        return tickData
                    }
                }
            }
        }
    }

    fun putTicketData(
        category: String,
        subCategory: String,
        createdBy: String,
        reportedBy: String,
        additionalDetails: String,
        floor: String,
        reportedVia: String,
        areaLocation: String,
        assignTo: String,
        record_id: String)
    {
        TicketingConfig().connect()
            .use { con -> con.prepareStatement(putTicketData)
                .use {
            it.setString(1,category)
            it.setString(2,subCategory)
            it.setString(3,createdBy)
            it.setString(4,reportedBy)
            it.setString(5,additionalDetails)
            it.setString(6,floor)
            it.setString(7,reportedVia)
            it.setString(8,areaLocation)
            it.setString(9,assignTo)
            it.setString(10,record_id)
            it.executeUpdate()
            }
        }
    }

    fun deleteTickeData(record_id: String)
    {
        TicketingConfig().connect()
            .use { con -> con. prepareStatement(deleteTicketData)
                .use {
                    it.setString(1,record_id)
                    it.executeQuery()
                }
            }
    }

    fun getCateg(): List<Categories> {
        val catagData = mutableListOf<Categories>()

        TicketingConfig().connect().use { connection ->
            connection.prepareStatement(getCatagData).use { query ->
                val result = query.executeQuery()

                while (result.next()) {
                    val category_id = result.getInt("category_id")
                    val category_name = result.getString("category_name")
                    catagData.add(Categories(category_id, category_name))
                }
            }
        }

        return catagData
    }

    fun getSubCatag(): List<SubCategories> {
        val catagData = mutableListOf<SubCategories>()

        TicketingConfig().connect().use { connection ->
            connection.prepareStatement(getSubCatagData).use { query ->
                query.executeQuery().use { result ->
                    while (result.next()) {
                        catagData += SubCategories(
                            result.getInt("category_id"),
                            result.getInt("sub_catag_id"),
                            result.getString("sub_catag_name")
                        )
                    }
                }
            }
        }

        return catagData
    }

    fun postDummyData(userId: String,
                      personnelAccountStatus: String,
                      systemAccountStatus: String,
                      Name: String,
                      companyEmail: String,
                      localNo: String,
                      companyName: String,
                      companyShortName: String,
                      departmentName: String,
                      imageName: String,
                      positionName: String,
                      sectionName: String) //: Int
    {
        TicketingConfig().connect().use {
            con -> con.prepareStatement(postDummyData).use {
            it.setString(1, userId)
            it.setString(2, personnelAccountStatus)
            it.setString(3, systemAccountStatus)
            it.setString(4, Name)
            it.setString(5, companyEmail)
            it.setString(6, localNo)
            it.setString(7, companyName)
            it.setString(8, companyShortName)
            it.setString(9, departmentName)
            it.setString(10, imageName)
            it.setString(11, positionName)
            it.setString(12, sectionName)

            it.executeUpdate()
            }
        }
    }

    fun getDummyData(): List<postDummyDataModel> {
        return TicketingConfig().connect().use { connection ->
            connection.prepareStatement(getDummyData).use { query ->
                val result = query.executeQuery()
                val dummyData = mutableListOf<postDummyDataModel>()

                while (result.next()) {
                    dummyData.add(
                        postDummyDataModel(
                            userId = result.getString("userId"),
                            personnelAccountStatus = result.getString("personnelAccountStatus"),
                            systemAccountStatus = result.getString("systemAccountStatus"),
                            Name = result.getString("Name"),
                            companyEmail = result.getString("companyEmail"),
                            localNo = result.getString("localNo"),
                            companyName = result.getString("companyName"),
                            companyShortName = result.getString("companyShortName"),
                            departmentName = result.getString("departmentName"),
                            imageName = result.getString("imageName"),
                            positionName = result.getString("positionName"),
                            sectionName = result.getString("sectionName")
                        )
                    )
                }
                dummyData
            }
        }
    }
}