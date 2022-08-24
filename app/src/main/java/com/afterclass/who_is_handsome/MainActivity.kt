package com.afterclass.who_is_handsome

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View



class MainActivity : AppCompatActivity() {
    companion object{
        val TAG = "Main Activity"
        val JOB_ID = 123
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun scheduleJob(view: View){

        val componentName=ComponentName(this,ToastJobService::class.java)

        val jobInfo = JobInfo.Builder(JOB_ID,componentName)
            .setPersisted(true)
            .setPeriodic(15*60*1000)
            .build()

        val scheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val resultCode = scheduler.schedule(jobInfo)
        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG,"Job Scheduled!")
        }
        else{
            Log.d(TAG,"Job Scheduling failed!")
        }
    }

    fun cancelJob(view:View){
        val scheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.cancel(JOB_ID)
        Log.d(TAG,"Job Cancelled!")
    }
}