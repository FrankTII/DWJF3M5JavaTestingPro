package com.test.interviewer.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class CandidateTest {
    static String existingCandidateName = "First";
    static String existingCandidateLastName = "Candidate";
    static String existingCandidateEmail = "first@candidate.com";

    @BeforeEach
    public void setUp() throws Exception {
        Candidate.data = new ArrayList<>();

        // We insert a user for testing delete and save
        Candidate.data.add(new Candidate(
                existingCandidateName,
                existingCandidateLastName,
                existingCandidateEmail,
                true
        ));
    }

    @Test
    public void add() {
        Candidate interviewer = new Candidate(
                "Test",
                "User",
                "any@email.com",
                true
        );

        interviewer.add();

        int expectedId = Candidate.data.size();
        assertEquals(
                expectedId,
                interviewer.id,
                "Candidate ID should be the new List's size"
        );
    }


    @Test
    public void save() {
        int originalListSize = Candidate.data.size();
        String expectedLastName = "New";
        Candidate existingCandidate = Candidate.data.get(0);
        System.out.println(Candidate.data.size());
        existingCandidate.save("", expectedLastName, "", true);

        int newListSize = Candidate.data.size();
        System.out.println(Candidate.data.size());
        int lastCandidateIndex = newListSize - 1;
        Candidate latestCandidate = Candidate.data.get(lastCandidateIndex);

        assertEquals(
                originalListSize,
                newListSize,
                "List size should be the same"
        );
        assertEquals(
                expectedLastName,
                latestCandidate.lastName,
                "Last Name should have been updated"
        );
        assertEquals(
                existingCandidate.name,
                latestCandidate.name,
                "Name should have not been updated"
        );
    }

    @Test
    public void getByEmail() {
        Candidate result = Candidate.getByEmail(existingCandidateEmail);

        assertNotNull(result, "Candidate should be found");
        assertEquals(
                existingCandidateName,
                result.name,
                "Unexpected Candidate Name"
        );
        assertEquals(
                existingCandidateLastName,
                result.lastName,
                "Unexpected Candidate Last Name"
        );
    }

    @Test
    public void getByNonExistingEmail() {
        String nonExistingEmail = "nonexisting@email.com";

        Candidate result = Candidate.getByEmail(nonExistingEmail);

        assertNull(result, "Candidate should not be found");
    }

    @Test
    public void delete() {
        Candidate existingCandidate = Candidate.data.get(0);

        int expectedSize = Candidate.data.size() - 1;

        try {
            existingCandidate.delete();
        } catch (Exception e) {
            fail("Unexpected Exception received: " + e.getMessage());
        }

        int actualSize = Candidate.data.size();

        assertEquals(
                expectedSize,
                actualSize,
                "List should be smaller"
        );
    }
}