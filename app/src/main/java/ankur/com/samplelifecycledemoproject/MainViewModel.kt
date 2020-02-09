package ankur.com.samplelifecycledemoproject

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val timerData = MutableLiveData<String>()

    private val timer = object : CountDownTimer(60000, 3000) {
        override fun onFinish() {
            timerData.postValue("Finish")
        }

        override fun onTick(p0: Long) {
//                Toast.makeText(application, "$p0", Toast.LENGTH_SHORT).show()
            timerData.postValue(p0.toString())
        }
    }

    fun start() {
        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

}
