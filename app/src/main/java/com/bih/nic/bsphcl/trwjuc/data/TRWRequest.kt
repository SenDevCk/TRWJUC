package com.bih.nic.bsphcl.trwjuc.data


/**
 *Created by Chandan Singh on 2/27/2025.
 */
data class TRWRequest(
   val trwId: String,
val circle: String,
val division: String,
val subDivision : String,
val section: String,
val place: String,
val dateOfBirth: String,
val capacity : String,
val year: String,
val make: String,
val oilCapacity: String,
val oilFound: String,
val dtrBodyFound: String,
val htStudFound: String,
val ltStudFound : String,
val htBushingFound: String,
val ltBushingStudFound: String,
val remarks1: String,
val dateOfReceiving: String,
val dateOfTesting: String,
val svrNo: String,
val dateOfSvr: String,
val dateOfIssue: String,
val gatePassNo: String,
val sivCdtNo: String,
val issuedTo: String,
val remarks2 : String,
   val materials: List<Material>
)