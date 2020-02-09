package ankur.com.samplelifecycledemoproject

import android.app.Application
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class TimerToast(application: Application, lifecycleOwner: LifecycleOwner) : LifecycleObserver {

    private var started = false;
    private val lifecycle = lifecycleOwner.lifecycle

    init {
        lifecycle.addObserver(this)
    }

    private val timer = object : CountDownTimer(60000, 3000) {
        override fun onFinish() {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                Toast.makeText(application, "Finsihed", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onTick(p0: Long) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                Toast.makeText(application, "$p0", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        if(!started) {
            started = true
            timer.start()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stop() {
        timer.cancel()
    }
}