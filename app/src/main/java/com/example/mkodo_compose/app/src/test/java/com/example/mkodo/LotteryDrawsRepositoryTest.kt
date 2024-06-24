package com.example.mkodo_compose.app.src.test.java.com.example.mkodo

import com.example.mkodo_compose.app.src.main.data.mockdata.LotteryDrawsData
import com.example.mkodo_compose.app.src.main.repository.LotteryDrawsRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.json.JSONException
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
@ConscryptMode(ConscryptMode.Mode.OFF)
class LotteryDrawsRepositoryTest {

    private val repository = LotteryDrawsRepositoryImpl()

    @Test
    fun testGetLotteryDraws_handlesEmptyData() = runBlocking {
        // Replace LotteryDrawsData.jsonData with an empty JSON object for this test
        val emptyJsonObject = JSONObject()
        val originalJsonData = LotteryDrawsData.jsonData
        LotteryDrawsData.jsonData = emptyJsonObject.toString()

        val draws = repository.getLotteryDraws()

        assertEquals(0, draws.size) // Verify that an empty list is returned

        // Restore the original JSON data
        LotteryDrawsData.jsonData = originalJsonData
    }

    @Test
    fun testGetLotteryDraws_handlesNullDraws() = runBlocking {
        // Replace LotteryDrawsData.jsonData with a JSON object containing null draws
        val nullDrawsJsonObject = JSONObject().put("draws", JSONObject.NULL)
        val originalJsonData = LotteryDrawsData.jsonData
        LotteryDrawsData.jsonData = nullDrawsJsonObject.toString()

        val draws = repository.getLotteryDraws()

        assertEquals(0, draws.size) // Verify that an empty list is returned

        // Restore the original JSON data
        LotteryDrawsData.jsonData = originalJsonData
    }

    @Test
    fun testGetLotteryDraws_handlesMalformedJson() = runBlocking {
        val malformedJson = "{"
        val originalJsonData = LotteryDrawsData.jsonData
        LotteryDrawsData.jsonData = malformedJson

        try {
            repository.getLotteryDraws()
            fail("Expected JSONException was not thrown")
        } catch (e: Exception) {
            assertTrue(e is JSONException)
        } finally {
            // Restore the original JSON data
            LotteryDrawsData.jsonData = originalJsonData
        }
    }

    @Test
    fun testGetLotteryDraws_handlesMissingFields() = runBlocking {
        val jsonWithMissingFields = """
    {
        "draws": [
            {
                "id": "1",
                "drawDate": "2023-08-10"
                // Other fields are missing
            }
        ]
    }
    """.trimIndent()
        val originalJsonData = LotteryDrawsData.jsonData
        LotteryDrawsData.jsonData = jsonWithMissingFields

        val draws = repository.getLotteryDraws()
        assertEquals(1, draws.size)
        val draw = draws[0]
        assertEquals("1", draw.id)
        assertEquals("2023-08-10", draw.drawDate)
        assertEquals("", draw.number1)
        assertEquals("", draw.number2)
        assertEquals("", draw.number3)
        assertEquals("", draw.number4)
        assertEquals("", draw.number5)
        assertEquals("", draw.number6)
        assertEquals("", draw.bonusBall)
        assertEquals(0, draw.topPrize)

        // Restore the original JSON data
        LotteryDrawsData.jsonData = originalJsonData
    }
}