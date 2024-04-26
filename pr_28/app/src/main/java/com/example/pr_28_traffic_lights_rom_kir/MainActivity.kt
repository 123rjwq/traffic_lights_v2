package com.example.pr_28_traffic_lights_rom_kir

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Объявление переменных для хранения ссылок на элементы интерфейса
    private lateinit var redLight: ImageView
    private lateinit var yellowLight: ImageView
    private lateinit var greenLight: ImageView
    private lateinit var changeLightButton: Button
    private lateinit var autoButton: Button
    private lateinit var stopButton: Button

    // Переменная для отслеживания текущего состояния светофора
    // 0 - красный, 1 - желтый, 2 - зеленый
    private var currentLight = 0
    // Флаг для управления автоматической сменой состояний светофора
    private var isAuto = false
    // Создание Handler для управления потоками
    private val handler = Handler(Looper.getMainLooper())
    // Runnable для автоматической смены состояний светофора
    private val runnable = object : Runnable {
        override fun run() {
            // Вызов метода для смены состояния светофора
            changeLight()
            // Проверка флага isAuto и повторное запуск Runnable через 1 секунду, если флаг установлен
            if (isAuto) {
                handler.postDelayed(this, 1000) // Смена состояния каждую секунду
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Вызов метода onCreate суперкласса
        setContentView(R.layout.activity_main) // Установка макета для активности

        // Инициализация переменных для работы с элементами интерфейса
        redLight = findViewById(R.id.redLight)
        yellowLight = findViewById(R.id.yellowLight)
        greenLight = findViewById(R.id.greenLight)
        changeLightButton = findViewById(R.id.changeLightButton)
        autoButton = findViewById(R.id.autoButton)
        stopButton = findViewById(R.id.stopButton)

        // Обработчик нажатия на кнопку смены светофора
        changeLightButton.setOnClickListener {
            changeLight() // Вызов функции смены светофора
        }

        // Обработчик нажатия на кнопку "Авто" для автоматической смены светофора
        autoButton.setOnClickListener {
            isAuto = true // Включение автоматической смены
            handler.post(runnable) // Запуск автоматической смены светофора
        }

        // Обработчик нажатия на кнопку "Стоп" для остановки автоматической смены светофора
        stopButton.setOnClickListener {
            isAuto = false // Выключение автоматической смены
            handler.removeCallbacks(runnable) // Остановка автоматической смены светофора
        }
    }

    private fun changeLight() {
        when (currentLight) {
            0 -> {
                redLight.setImageResource(R.drawable.grey_circle)
                yellowLight.setImageResource(R.drawable.yellow_circle)
                greenLight.setImageResource(R.drawable.grey_circle)
                currentLight = 1
            }
            1 -> {
                redLight.setImageResource(R.drawable.grey_circle)
                yellowLight.setImageResource(R.drawable.grey_circle)
                greenLight.setImageResource(R.drawable.green_circle)
                currentLight = 2
            }
            2 -> {
                redLight.setImageResource(R.drawable.grey_circle)
                yellowLight.setImageResource(R.drawable.yellow_circle)
                greenLight.setImageResource(R.drawable.grey_circle)
                currentLight = 3
            }
            3 -> {
                redLight.setImageResource(R.drawable.red_circle)
                yellowLight.setImageResource(R.drawable.grey_circle)
                greenLight.setImageResource(R.drawable.grey_circle)
                currentLight = 0
            }
        }
    }
}

