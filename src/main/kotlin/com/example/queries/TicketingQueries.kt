package com.example.queries

const val getTicketData = "SELECT * FROM ticketdata"

const val postTicketData = "INSERT INTO ticketdata(user_id, category, sub_category, created_by, reported_by, additional_details, floor, reported_via, area_location, assign_to) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"

const val putTicketData = "UPDATE ticketdata SET category=?, sub_category=?, created_by=?, reported_by=?, additional_details=?, floor=?, reported_via=?, area_location=?, assign_to=? WHERE record_id=CAST(? as int)"

const val deleteTicketData = "DELETE FROM ticketdata WHERE record_id=cast(? as int)"

const val getCatagData = "SELECT * FROM test_catag"

const val getSubCatagData = "SELECT * FROM test_sub_catag"

const val postDummyData =  "INSERT INTO dummy_data(userid, personnelaccountstatus, systemaccountstatus, name, companyemail,localno,companyname,companyshortname,departmentname,imagename,positionname,sectionname) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)"

const val getDummyData = "SELECT * FROM dummy_data"

