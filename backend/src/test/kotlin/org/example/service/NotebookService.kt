package org.example.service

//@RunWith(MockitoJUnitRunner::class)
//class NotebookService {
//    @InjectMocks
//    lateinit var helloController: HelloController
//    @Mock
//    lateinit var helloService: HelloService
//    @Test
//    fun testHelloController() {
//        val result = helloController.helloString()
//        assertNotNull(result)
//        assertEquals("Hello string!", result)
//    }
//    @Test
//    fun testHelloService() {
//        doReturn("Hello service!").`when`(helloService).getHello()
//        val result = helloController.helloService()
//        assertNotNull(result)
//        assertEquals("Hello service!", result)
//    }
//    @Test
//    fun testHelloDto() {
//        val result = helloController.helloData()
//        assertNotNull(result)
//        assertEquals(Hello("Hello data!"), result)
//    }
//}