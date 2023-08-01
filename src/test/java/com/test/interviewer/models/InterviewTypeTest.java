package com.test.interviewer.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class InterviewTypeTest {
    static String existingInterviewTypeName = "First_InterviewType";
    static String existingInterviewTypeSlug = "Slug-IDInterviewType";
    static String existingInterviewTypeDescription = "InterviewType Description";

    @BeforeEach
    public void setUp() throws Exception {
        InterviewType.data = new ArrayList<>();

        // We insert a user for testing delete and save
        InterviewType.data.add(new InterviewType(
                existingInterviewTypeName,
                existingInterviewTypeSlug,
                existingInterviewTypeDescription
        ));
    }

    @Test
    public void add() {
        InterviewType interviewer = new InterviewType(
                "Test-InterviewType",
                "Slug-Id-InterviewType",
                "any InterviewType Description"
        );

        interviewer.add();

        int expectedId = InterviewType.data.size();
        assertEquals(
                expectedId,
                interviewer.id,
                "InterviewType ID should be the new List's size"
        );
    }


    @Test
    public void save() {
        int originalListSize = InterviewType.data.size();
        String expectedSlug = "New-InterviewType";
        InterviewType existingInterviewType = InterviewType.data.get(0);
        System.out.println(InterviewType.data.size());
        existingInterviewType.save("", expectedSlug, "");

        int newListSize = InterviewType.data.size();
        System.out.println(InterviewType.data.size());
        int lastInterviewTypeIndex = newListSize - 1;
        InterviewType latestInterviewType = InterviewType.data.get(lastInterviewTypeIndex);

        assertEquals(
                originalListSize,
                newListSize,
                "List size should be the same"
        );
        assertEquals(
                expectedSlug,
                latestInterviewType.slug,
                "Slug should have been updated"
        );
        assertEquals(
                existingInterviewType.name,
                latestInterviewType.name,
                "Name should have not been updated"
        );
    }

    @Test
    public void getByDescription() {
        InterviewType result = InterviewType.getByDescription(existingInterviewTypeDescription);

        assertNotNull(result, "InterviewType should be found");
        assertEquals(
                existingInterviewTypeName,
                result.name,
                "Unexpected InterviewType Name"
        );
        assertEquals(
                existingInterviewTypeSlug,
                result.slug,
                "Unexpected InterviewType Slug"
        );
    }

    @Test
    public void getByNonExistingDescription() {
        String nonExistingDescription = "nonexisting Description";

        InterviewType result = InterviewType.getByDescription(nonExistingDescription);

        assertNull(result, "InterviewType should not be found");
    }

    @Test
    public void delete() {
        InterviewType existingInterviewType = InterviewType.data.get(0);

        int expectedSize = InterviewType.data.size() - 1;

        try {
            existingInterviewType.delete();
        } catch (Exception e) {
            fail("Unexpected Exception received: " + e.getMessage());
        }

        int actualSize = InterviewType.data.size();

        assertEquals(
                expectedSize,
                actualSize,
                "List should be smaller"
        );
    }
}