package br.edu.ifce.engcomp.francis.radarpolitico.helpers

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * Created by francisco on 19/04/16.
 */
public object VolleySharedQueue {
    private var queue: RequestQueue? = null

    fun getQueue(context:Context): RequestQueue? {
        if (queue == null) {
            queue = Volley.newRequestQueue(context);
        }

        return queue
    }
}