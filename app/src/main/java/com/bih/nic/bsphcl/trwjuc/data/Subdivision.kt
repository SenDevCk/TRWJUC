package com.bih.nic.bsphcl.trwjuc.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *Created by Chandan Singh on 2/6/2025.
 */
@Entity
data class Subdivision (
    @PrimaryKey
    @NonNull
    var subDivId: String,
    var subDivName: String,
    var divId: String,
    var circleId: String
)