package com.spe.calculator;

import com.spe.calculator.controller.CalculatorController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAdd() throws Exception {
        mockMvc.perform(get("/api/v1/add?a=5&b=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(8.0));
    }

    @Test
    public void testSubtract() throws Exception {
        mockMvc.perform(get("/api/v1/subtract?a=5&b=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(2.0));
    }

    @Test
    public void testMultiply() throws Exception {
        mockMvc.perform(get("/api/v1/multiply?a=5&b=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(15.0));
    }

    @Test
    public void testDivide() throws Exception {
        mockMvc.perform(get("/api/v1/divide?a=6&b=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(3.0));
    }

    @Test
    public void testDivideByZero() throws Exception {
        mockMvc.perform(get("/api/v1/divide?a=6&b=0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }
}