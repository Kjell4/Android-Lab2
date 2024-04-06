package com.example.lab2.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab2.adapter.CelebrityAdapter
import com.example.lab2.databinding.FragmentCelebrityBinding
import com.example.lab2.model.entity.Celebrity
import com.example.lab2.model.network.ApiClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CelebrityFragment : Fragment() {

    private var _binding: FragmentCelebrityBinding? = null
    private val binding get() = _binding!!

    private val adapter: CelebrityAdapter by lazy {
        CelebrityAdapter()
    }

    companion object {
        fun newInstance(): CelebrityFragment {
            return CelebrityFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCelebrityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()

        ApiClient.instance.fetchCelebrityList().enqueue(object : Callback<List<Celebrity>> {
            override fun onResponse(call: Call<List<Celebrity>>, response: Response<List<Celebrity>>) {
                if (response.isSuccessful) {
                    val celebrities = response.body()
                    celebrities?.let {
                        Log.d("API_DEBUG", "Получены данные о знаменитостях: $celebrities")
                        adapter.submitList(it)
                        adapter.setOriginalList(it)

                        val gson = Gson()
                        val jsonString = gson.toJson(celebrities)
                        val celebrityArray = gson.fromJson(jsonString, Array<Celebrity>::class.java)
                        val celebrity = celebrityArray[0]

                        Log.d("API_DEBUG", "Name: ${celebrity.name}")
                        Log.d("API_DEBUG", "Nationality: ${celebrity.nationality}")
                        Log.d("API_DEBUG", "Height: ${celebrity.height}")
                        Log.d("API_DEBUG", "Birthday: ${celebrity.birthday}")
                        Log.d("API_DEBUG", "Occupation: ${celebrity.occupation}")

                    }
                } else {
                    Log.e("API_DEBUG", "Ошибка при получении данных: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Celebrity>>, t: Throwable) {
                Log.e("API_DEBUG", "Ошибка при сетевом запросе: ${t.message}")
                t.printStackTrace()
            }
        })

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

    }

    private fun setupUI() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CelebrityFragment.adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
