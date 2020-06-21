package org.example.unit

import org.example.domain.request.NotebookRequest
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertFalse

class DynamicTest {

    @TestFactory
    fun `Dynamic notebook test`(): List<DynamicTest> {
        return listOf(NotebookRequest("2", "2"),
                      NotebookRequest("3", "2")).map {
            dynamicTest("Check notebook $it on unnecessary information") {
                assertFalse { it.equals() }
            }
        }.toList()
    }
}