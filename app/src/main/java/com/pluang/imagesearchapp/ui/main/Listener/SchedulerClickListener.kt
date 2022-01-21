package com.pluang.imagesearchapp.ui.main.Listener

import com.pluang.imagesearchapp.data.database.entities.Schedule

interface SchedulerClickListener {
    fun show(user: Schedule)
}