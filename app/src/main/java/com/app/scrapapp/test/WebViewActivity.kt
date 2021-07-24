package com.app.scrapapp.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.scrapapp.R
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.activity_webview_recyclerview.*
import kotlinx.android.synthetic.main.adapter_web.view.*

class WebViewActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_recyclerview)

        rv.adapter = WebAdapter()
        rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


    }

}

class WebAdapter : RecyclerView.Adapter<WebViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_web,parent,false)
        return WebViewHolder(view)
    }
    override fun getItemCount(): Int {
        return 10
    }
    override fun onBindViewHolder(holder: WebViewHolder, position: Int) {
        val cache : Boolean? = holder.itemView.getTag(R.string.tag_cached) as Boolean?
        if(cache==null||cache==false) {
            var data = "<html>\n" +
                    "  <title>My Website</title>\n" +
                    "<body>\n" +
                    "  <script async defer src=\"https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v3.2\"></script>  \n" +
                    "  <div class=\"fb-post\" \n" +
                    "      data-href=\"https://www.facebook.com/TheBhangarwale/posts/156439772871537\"\n" +
                    "      ></div>\n" +
                    "</body>\n" +
                    "</html>"
            val encodedHtml = Base64.encodeToString(data.toByteArray(), Base64.NO_PADDING)
            holder.itemView.webView.settings.javaScriptEnabled = true
            holder.itemView.webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
            holder.itemView.webView.loadData(encodedHtml, "text/html", "base64")
            holder.itemView.setTag(R.string.tag_cached, true)
        }
    }
}

class WebViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)