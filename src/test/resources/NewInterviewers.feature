Feature: Creación de Entrevistador por el Usuario Administrador

    Scenario: Crear un nuevo registro de Entrevistador con datos válidos
        Given un usuario administrador
        When ingresa los datos del entrevistador with name "John", last_name "Doe", and email "john.doe@example.com"
        Then se crea su registro en la aplicación y se devuelve el ID del nuevo entrevistador

    Scenario: Intentar crear un nuevo registro de Entrevistador con name y last_name menores a 3 caracteres
        Given un usuario administrador
        When ingresa los datos del entrevistador with name "Jo", last_name "Do", and email "john.doe@example.com"
        Then se lanza una excepción TooShortInputDataException

    Scenario: Intentar crear un nuevo registro de Entrevistador con un email inválido
        Given un usuario administrador
        When ingresa los datos del entrevistador with name "John", last_name "Doe", and email "invalid_email"
        Then se lanza una excepción InvalidEmailException
