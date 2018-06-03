package com.example.benlee.playground.playgroundnotifications

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.activity_launch.*
import kotlinx.android.synthetic.main.launcher.view.*

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        switchToLegendButton.setOnClickListener { switcher.showNext() }
        switchToLaunchers.setOnClickListener { switcher.showPrevious() }

        panel.layoutManager = LinearLayoutManager(this)
        panel.adapter = NotificationLauncherAdapter(
                this,
                listOf(
                        "Simple: Default" to ::launch_Simple_Default,
                        "Simple: High" to ::launch_Simple_High,
                        "Simple: Low" to ::launch_Simple_Low
                )
        )
    }
}

class NotificationLauncherAdapter(private val context: Context, private val launchers: List<Pair<String, (Context) -> Unit>>) : RecyclerView.Adapter<NotificationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder =
            NotificationViewHolder(LayoutInflater.from(context).inflate(R.layout.launcher, parent, false))

    override fun getItemCount(): Int = launchers.size

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.button.text = launchers[position].first
        holder.button.setOnClickListener { launchers[position].second(context) }
    }
}

class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val button: Button = view.launchButton
}
