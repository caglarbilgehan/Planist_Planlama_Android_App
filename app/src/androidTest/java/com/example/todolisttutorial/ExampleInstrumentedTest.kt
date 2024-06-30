package com.example.todolisttutorial

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Android cihazında çalışacak enstrümantasyon testi.
 *
 * [test belgelerine](http://d.android.com/tools/testing) bakın.
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Test edilen uygulamanın bağlamı.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.todolisttutorial", appContext.packageName)
    }
}
