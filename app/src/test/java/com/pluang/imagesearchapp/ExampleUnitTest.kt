package com.pluang.imagesearchapp

import com.pluang.imagesearchapp.utils.Utils.isEmpty
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun emptyCheckingValidation()
    {
        assertEquals( isEmpty(""),true)
        assertEquals( isEmpty(" cat"),false)
    }
}
