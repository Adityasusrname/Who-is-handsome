package com.afterclass.who_is_handsome

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Looper
import android.util.Log
import android.widget.Toast

class ToastJobService:JobService() {
    companion object{
        val TAG="ToastJobService"
    }
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG,"Job Started!")
        doBackgroundWork(params)
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG,"Job cancelled before completion!")
        return true
    }
    fun doBackgroundWork(params: JobParameters?){
        Thread(Runnable {
            Looper.prepare()
            Toast.makeText(this,"You are handsome!",Toast.LENGTH_LONG).show()
            Log.d(TAG,"Job Finished!")
            jobFinished(params,false)

        }).start()
    }
}