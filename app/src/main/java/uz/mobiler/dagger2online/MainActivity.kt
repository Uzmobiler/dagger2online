package uz.mobiler.dagger2online

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.mobiler.dagger2online.databinding.ActivityMainBinding
import uz.mobiler.dagger2online.utils.UserResource
import uz.mobiler.dagger2online.viewmodels.UserViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    @Inject
    lateinit var userViewModel: UserViewModel

    private val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launch {
            userViewModel.getStateFlow().collect {
                when (it) {
                    is UserResource.Loading -> {

                    }
                    is UserResource.Error -> {

                    }
                    is UserResource.Success -> {
                        Log.d(TAG, "onCreate: ${it.list}")
                    }
                }
            }
        }

    }

    override val coroutineContext: CoroutineContext
        get() = Job()
}