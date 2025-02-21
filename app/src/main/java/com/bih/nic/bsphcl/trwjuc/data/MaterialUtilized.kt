package com.bih.nic.bsphcl.trwjuc.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *Created by Chandan Singh on 2/21/2025.
 */
@Entity(primaryKeys = ["matId", "trwUniqueId"])
class MaterialUtilized (
    @NonNull
    var matId:String,
    @NonNull
    var trwUniqueId:String,
    var unit1:String?
    ,var unit2:String?)