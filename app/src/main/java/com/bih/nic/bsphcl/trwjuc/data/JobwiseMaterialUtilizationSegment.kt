package com.bih.nic.bsphcl.trwjuc.data

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *Created by Chandan Singh on 2/14/2025.
 */
@Entity
class JobwiseMaterialUtilizationSegment
{
    @PrimaryKey
    var id:Int?=null
    var materialName:String?=null
    var firstUnit:String?=null
    var secondUnit:String?=null
}