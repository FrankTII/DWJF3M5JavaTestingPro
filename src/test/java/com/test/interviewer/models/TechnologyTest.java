package com.test.interviewer.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class TechnologyTest {
    static String existingTechnologyName = "First_Technology";
    static String existingTechnologySlug = "Slug-IDTecnology";
    static String existingTechnologyDescription = "Technology Description";

    @BeforeEach
    public void setUp() throws Exception {
        Technology.data = new ArrayList<>();

        // We insert a user for testing delete and save
        Technology.data.add(new Technology(
                existingTechnologyName,
                existingTechnologySlug,
                existingTechnologyDescription
        ));
    }

    @Test
    public void add() {
        Technology interviewer = new Technology(
                "Test-Technology",
                "Slug-Id-Technology",
                "any Technology Description"
        );

        interviewer.add();

        int expectedId = Technology.data.size();
        assertEquals(
                expectedId,
                interviewer.id,
                "Technology ID should be the new List's size"
        );
    }


    @Test
    public void save() {
        int originalListSize = Technology.data.size();
        String expectedSlug = "New-Technology";
        Technology existingTechnology = Technology.data.get(0);
        System.out.println(Technology.data.size());
        existingTechnology.save("", expectedSlug, "");

        int newListSize = Technology.data.size();
        System.out.println(Technology.data.size());
        int lastTechnologyIndex = newListSize - 1;
        Technology latestTechnology = Technology.data.get(lastTechnologyIndex);

        assertEquals(
                originalListSize,
                newListSize,
                "List size should be the same"
        );
        assertEquals(
                expectedSlug,
                latestTechnology.slug,
                "Slug should have been updated"
        );
        assertEquals(
                existingTechnology.name,
                latestTechnology.name,
                "Name should have not been updated"
        );
    }

    @Test
    public void getByDescription() {
        Technology result = Technology.getByDescription(existingTechnologyDescription);

        assertNotNull(result, "Technology should be found");
        assertEquals(
                existingTechnologyName,
                result.name,
                "Unexpected Technology Name"
        );
        assertEquals(
                existingTechnologySlug,
                result.slug,
                "Unexpected Technology Slug"
        );
    }

    @Test
    public void getByNonExistingDescription() {
        String nonExistingDescription = "nonexisting Technology";

        Technology result = Technology.getByDescription(nonExistingDescription);

        assertNull(result, "Technology should not be found");
    }

    @Test
    public void delete() {
        Technology existingTechnology = Technology.data.get(0);

        int expectedSize = Technology.data.size() - 1;

        try {
            existingTechnology.delete();
        } catch (Exception e) {
            fail("Unexpected Exception received: " + e.getMessage());
        }

        int actualSize = Technology.data.size();

        assertEquals(
                expectedSize,
                actualSize,
                "List should be smaller"
        );
    }
}