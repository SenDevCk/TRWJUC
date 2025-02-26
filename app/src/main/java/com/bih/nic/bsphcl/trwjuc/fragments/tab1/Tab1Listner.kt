package com.bih.nic.bsphcl.trwjuc.fragments.tab1

import com.bih.nic.bsphcl.trwjuc.dao.JointInspectionReportDao
import com.bih.nic.bsphcl.trwjuc.data.JointInspectionReport


/**
 *Created by Chandan Singh on 1/30/2025.
 */
interface Tab1Listner {
   fun  onSuccess(trwid: String)
    fun  onFailure(string: String)
}