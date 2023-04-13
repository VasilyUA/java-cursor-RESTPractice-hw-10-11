package com.example.demo.integration;

import com.example.demo.dtos.course.input.CreateCourseDto;
import com.example.demo.dtos.course.output.CourseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CoursesControllerIntegrationTest {

    static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.25"))
            .withDatabaseName("test")
            .withUsername("testuser")
            .withPassword("testpass");

    static {
        mySQLContainer.start();
    }

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testCreateCourse() throws Exception {
        CreateCourseDto createCourseDto = new CreateCourseDto();
        createCourseDto.setName("Test Course");
        createCourseDto.setDescription("Test Course Description");
        createCourseDto.setCategory("Test Category");
        createCourseDto.setPrice(100.00);

        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCourseDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Test Course"))
                .andExpect(jsonPath("$.description").value("Test Course Description"));
    }

    @Test
    public void testGetAllCourses() throws Exception {
        mockMvc.perform(get("/api/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetCourseById() throws Exception {
        CreateCourseDto createCourseDto = new CreateCourseDto();
        createCourseDto.setName("Test Course 2");
        createCourseDto.setDescription("Test Course Description 2");
        createCourseDto.setCategory("Test Category 2");
        createCourseDto.setPrice(200.00);

        String courseJson = mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCourseDto)))
                .andReturn().getResponse().getContentAsString();

        CourseDto createdCourse = objectMapper.readValue(courseJson, CourseDto.class);
        Long courseId = createdCourse.getId();

        mockMvc.perform(get("/api/courses/{id}", courseId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(courseId))
                .andExpect(jsonPath("$.name").value("Test Course 2"))
                .andExpect(jsonPath("$.description").value("Test Course Description 2"));
    }

    @Test
    public void testUpdateCourse() throws Exception {
        CreateCourseDto createCourseDto = new CreateCourseDto();
        createCourseDto.setName("Test Course 3");
        createCourseDto.setDescription("Test Course Description 3");
        createCourseDto.setCategory("Test Category 3");
        createCourseDto.setPrice(300.00);

        String courseJson = mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCourseDto)))
                .andReturn().getResponse().getContentAsString();

        CourseDto createdCourse = objectMapper.readValue(courseJson, CourseDto.class);
        Long courseId = createdCourse.getId();

        CreateCourseDto updateCourseDto = new CreateCourseDto();
        updateCourseDto.setName("Updated Test Course");
        updateCourseDto.setDescription("Updated Test Course Description");
        updateCourseDto.setCategory("Updated Test Category");
        updateCourseDto.setPrice(300.00);

        mockMvc.perform(put("/api/courses/{id}", courseId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateCourseDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(courseId))
                .andExpect(jsonPath("$.name").value("Updated Test Course"))
                .andExpect(jsonPath("$.description").value("Updated Test Course Description"));
    }

    @Test
    public void testDeleteCourse() throws Exception {
        CreateCourseDto createCourseDto = new CreateCourseDto();
        createCourseDto.setName("Test Course 4");
        createCourseDto.setDescription("Test Course Description 4");
        createCourseDto.setCategory("Test Category 4");
        createCourseDto.setPrice(400.00);

        String courseJson = mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCourseDto)))
                .andReturn().getResponse().getContentAsString();

        CourseDto createdCourse = objectMapper.readValue(courseJson, CourseDto.class);
        Long courseId = createdCourse.getId();

        mockMvc.perform(delete("/api/courses/{id}", courseId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/courses/{id}", courseId))
                .andExpect(status().isNotFound());
    }
}

