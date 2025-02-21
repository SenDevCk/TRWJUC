package com.bih.nic.bsphcl.trwjuc.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *Created by Chandan Singh on 2/6/2025.
 */
@Entity
class Circle (
    @PrimaryKey
    @NonNull // ✅ Explicitly mark as NonNull for Room
    var circleId:String,
    var cirName:String?)