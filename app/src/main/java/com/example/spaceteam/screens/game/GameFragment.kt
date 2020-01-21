package com.example.spaceteam.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.spaceteam.Config
import com.example.spaceteam.R
import com.example.spaceteam.databinding.GameFragmentBinding
import com.example.spaceteam.serviceWeb.WebSocketConnection

/**
 * Fragment of game
 */
class GameFragment: Fragment() {

    private lateinit var viewModel: GameViewModel

    /**
     * called on the creation of fragment
     *
     * @param inflater:LayoutInflater
     * @param container: ViewGroup?
     * @param savedInstanceState:Bundle?
     *
     * @return View?
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<GameFragmentBinding>(
            inflater,
            R.layout.game_fragment, container, false
        )

        binding.finishButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_gameFragment_to_scoreFragment)
        }

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        viewModel.getLastEventReceived().observe(this, Observer { event ->
            Log.d(Config.TAG, "---$event")
        })

        return binding.root
    }

}