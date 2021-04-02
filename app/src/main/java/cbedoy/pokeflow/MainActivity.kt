package cbedoy.pokeflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import cbedoy.pokeflow.databinding.ActivityMainBinding
import cbedoy.pokeflow.ui.view.PokePagerFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val pokePagerFragment by inject<PokePagerFragment>()

    private val binding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, pokePagerFragment)
            .commit()
    }
}