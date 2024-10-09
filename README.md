# Log-It

AI Automated Changelog generator built for developers

### To create a change log generator


Languages and framework used

- Java with Spring Boot
- Meta Llama Vision Free
- thymeleaf with html


Workflow diagram

![Screenshot from 2024-10-07 10-34-22](https://github.com/user-attachments/assets/03b64f33-73bc-4bbc-a0e7-44729839b533)


## Steps how this works
- Pulls the commit history from github api
- feeds the data to free opensource LLM
- displays the changelog in ui

## How to Run

- Download the jar file and run using java -jar jarname
- Open localhost8080 and enter the details
- Give your github username example PrethamMuthappa
- Enter you Repo name (public repo for testing purpose)
- Enter no of commit history ( enter 1, as the free llm usually dont allow multiple requests)
- Results will be displayed

## Example

![image](https://github.com/user-attachments/assets/65bb4b9b-8f9f-4ef7-94e4-4fcea58155c6)


Result change logs

![image](https://github.com/user-attachments/assets/4ea5f348-f28c-4b4b-844e-ae74343649b0)
