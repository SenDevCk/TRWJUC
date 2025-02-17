package com.bih.nic.bsphcl.trwjuc.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *Created by Chandan Singh on 2/14/2025.
 */
@Entity
data class JobwiseMaterialUtilizationSegment(
    @PrimaryKey
    @NonNull
    var id:Int,
    var materialName:String,
    var firstUnit:String,
    var secondUnit:String?
)