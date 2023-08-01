package com.test.interviewer.stepdefinitions;

import com.test.interviewer.exceptions.TooShortInputDataException;
import com.test.interviewer.exceptions.InvalidEmailException;
import com.test.interviewer.models.Interviewer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class NewInterviewerStepDefinitions {

    private Interviewer interviewer;
    private Exception exception;

    @Given("un usuario administrador")
    public void unUsuarioAdministrador() {
        // Agregar lógica de validacion deAdmin hasta hacer el microservicio que genere Tokens por Login /signUp.
    }

    @When("ingresa los datos del entrevistador with name {string}, last_name {string}, and email {string}")
    public void ingresaLosDatosDelEntrevistador(String name, String lastName, String email) {
        try {
            interviewer = new Interviewer(name, lastName, email, true, true);
        } catch (TooShortInputDataException | InvalidEmailException e) {
            exception = e;
        }
    }

    @Then("se crea su registro en la aplicación y se devuelve el ID del nuevo entrevistador")
    public void seCreaSuRegistroEnLaAplicacionYSeDevuelveElIDDelNuevoEntrevistador() {
        assertNotNull(interviewer);
        assertTrue(interviewer.getId()> 0);
    }

    @Then("se lanza una excepción TooShortInputDataException")
    public void seLanzaUnaExcepcionTooShortInputDataException() {
        assertNotNull(exception);
        assertTrue(exception instanceof TooShortInputDataException);
    }

    @Then("se lanza una excepción InvalidEmailException")
    public void seLanzaUnaExcepcionInvalidEmailException() {
        assertNotNull(exception);
        assertTrue(exception instanceof InvalidEmailException);
    }
}