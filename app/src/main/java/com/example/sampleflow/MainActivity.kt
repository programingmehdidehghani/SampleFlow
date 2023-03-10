package com.example.sampleflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sampleflow.ui.theme.SampleFlowTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* collectLatestLifecycleFlow(viewModel.stateFlow){

        }*/
        setContent {
            SampleFlowTheme {
                val viewModel = viewModel<MainViewModel>()
                val count = viewModel.countDownFlow.collectAsState(initial = 0)
               Box(modifier = Modifier.fillMaxSize()){
                   Button(onClick = {  }) {
                       Text(text = "count: ${count.value}")
                   }
               }
            }
        }
    }
}

/*fun ComponentActivity.collectLatestLifecycleFlow(flow: Flow<T>,collect: suspend (T) -> Unit) {
     lifecycleScope.launch {
         repeatOnLifecycle(Lifecycle.State.STARTED){
               flow.collectLatest(collect)
         }
     }
}*/

