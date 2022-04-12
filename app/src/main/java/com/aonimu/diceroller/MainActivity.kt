package com.aonimu.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

/**
 * このアクティビティにより、ユーザーはサイコロを振って結果を画面に表示できます。
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Button オブジェクトへの参照を rollButton という変数に保存
        val rollButton: Button = findViewById(R.id.button)
        //rollButtonオブジェクト上でsetOnClickListenerを呼び出す
        rollButton.setOnClickListener {

            //rollDiceメソッドを実行
            rollDice()

            /*//TextViewを更新(id=textView)
            val resultTextView: TextView = findViewById(R.id.textView)
            resultTextView.text = "6"*/
            /*//トーストを表示
            val toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT)
            toast.show()
            ↓上記をまとめたコード↓
            Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT).show()*/
        }
        //アプリ起動時に1度ダイスを振る
        rollDice()
    }

    /**
     * サイコロを振って、結果で画面を更新します。
     */
    private fun rollDice() {
        //Diceクラスを使ってn面のダイスオブジェクトを作成
        val dice = Dice(6)
        //diceオブジェクトのroll()メソッドを使ってダイスの目を求める
        val diceRoll = dice.roll()
        //ImageViewを更新(id=imageView)
        val diceImage: ImageView = findViewById(R.id.imageView)
        /*when(diceRoll){
            //R.drawable.*は画像のリソースID
            1 -> diceImage.setImageResource(R.drawable.dice_1)
            2 -> diceImage.setImageResource(R.drawable.dice_2)
            3 -> diceImage.setImageResource(R.drawable.dice_3)
            4 -> diceImage.setImageResource(R.drawable.dice_4)
            5 -> diceImage.setImageResource(R.drawable.dice_5)
            6 -> diceImage.setImageResource(R.drawable.dice_6)
        }*/
        //when(diceRoll)...の書き換え
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        //画像の更新
        diceImage.setImageResource(drawableResource)
        //ImageView に適切なコンテンツの説明を設定する(音声読み上げが必要な場合)
        diceImage.contentDescription = diceRoll.toString()

        /*//TextViewを更新(id=textView)
        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = diceRoll.toString()*/
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}