package com.bih.nic.bsphcl.trwjuc.data

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *Created by Chandan Singh on 2/6/2025.
 */
@Entity
class Circle {
    @PrimaryKey
    var circleId:String?=null
    var circleName:String?=null
}