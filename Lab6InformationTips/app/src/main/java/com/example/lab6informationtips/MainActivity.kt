package com.example.lab6informationtips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 將變數與XML元件綁定
        val btn_toast = findViewById<Button>(R.id.btn_toast)
        val btn_custom = findViewById<Button>(R.id.btn_custom)
        val btn_snackbar = findViewById<Button>(R.id.btn_snackbar)
        val btn_dialog1 = findViewById<Button>(R.id.btn_dialog1)
        val btn_dialog2 = findViewById<Button>(R.id.btn_dialog2)
        val btn_dialog3 = findViewById<Button>(R.id.btn_dialog3)
        // 建立要顯示在列表上的字串
        val item = arrayOf("選項1", "選項2", "選項3", "選項4", "選項5")
        // Button點擊事件
        btn_toast.setOnClickListener {
            showToast("預設Toast")
        }
        btn_custom.setOnClickListener {
            // 宣告Toast
            val toast = Toast(this)
            // Toast在畫面中顯示位置
            toast.setGravity(Gravity.TOP, 0, 50)
            // Toast顯示持續時間
            toast.duration = Toast.LENGTH_SHORT
            // 放入自定義的畫面
            toast.view = layoutInflater.inflate(R.layout.custom_toast, null)
            // 顯示於螢幕
            toast.show()
        }
        btn_snackbar.setOnClickListener {
            // 建立Snackbar物件
            Snackbar.make(it, "按鈕式Snackbar", Snackbar.LENGTH_SHORT).setAction("按鈕"){
                showToast("已回應")
            }.show()
        }
        btn_dialog1.setOnClickListener {
            // 建立AlertDialog物件
            AlertDialog.Builder(this).setTitle("按鈕式AlertDialog").setMessage("AlertDialog內容")
                .setNeutralButton("左按鈕") { dialog, which ->
                    showToast("左按鈕")
                }
                .setNegativeButton("中按鈕") { dialog, which ->
                    showToast("中按鈕")
                }
                .setPositiveButton("右按鈕") { dialog, which ->
                    showToast("右按鈕")
                }.show()
        }
        btn_dialog2.setOnClickListener {
            // 建立AlertDialog物件
            AlertDialog.Builder(this).setTitle("列表式AlertDialog").setItems(item) { dialogInterface, i ->
                showToast("你選的是${item[i]}")
            }.show()
        }
        btn_dialog3.setOnClickListener {
            var position = 0
            // 建立AlertDialog物件
            AlertDialog.Builder(this).setTitle("單選式AlertDialog")
                .setSingleChoiceItems(item, 0) { dialogInterface, i ->
                    position = i
                }.setPositiveButton("確定") { dialog, which ->
                    showToast("你選的是${item[position]}")
                }.show()
        }
    }
    // 建立showToast方法顯示Toast訊息
    private fun showToast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}