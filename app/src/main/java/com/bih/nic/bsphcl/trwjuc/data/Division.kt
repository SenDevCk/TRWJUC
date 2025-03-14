package com.bih.nic.bsphcl.trwjuc.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *Created by Chandan Singh on 2/6/2025.
 */
@Entity
class Division (
    @PrimaryKey
    @NonNull
    var divId:String,
    var divName:String?,
    var circleId:String
)