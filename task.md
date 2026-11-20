# Task: Containerize the Todo Application

Create a Docker setup that builds and runs the React frontend and Spring Boot backend together.

## Branch setup

1. Create a branch named after yourself, for example `your-name`.
2. From your personal branch, create a working branch named `feature/docker`.

```bash
git switch -c your-name
git switch -c feature/docker
```

## Requirements

- Add a separate `Dockerfile` inside the frontend and backend directories.
- Add a `compose.yaml` at the repository root to build and run both applications.
- Include PostgreSQL in the Compose setup and configure the backend's database connection.
- When containerized, set the backend's `DB_HOST` to `db` (the PostgreSQL service name in Compose) and `DB_PORT` to `5432`. Do not use `localhost` to reach a separate database container: inside a container, `localhost` refers to that container itself. See [Docker networking](https://docs.docker.com/compose/how-tos/networking/).
- Ensure the frontend can communicate with the backend.
- Persist database data using a Docker volume.
- Update the README with commands to build, start, and stop the application.

## Acceptance criteria

- `docker compose up --build` starts the complete application.
- Users can create, view, update, complete, and delete tasks through the frontend.
- Saved tasks remain available after restarting the containers.
- Commit your changes to `feature/docker`.

## Pull request submission

Once the task is complete:

1. Push your changes on the `feature/docker` branch.
2. Raise a pull request from `feature/docker` into your personal `your-name` branch.
   - Source (compare) branch: `feature/docker`
   - Target (base) branch: `your-name` (replace this with your actual personal branch name).
3. Request a review from the repository owner by adding them as a reviewer on the PR.
4. Include a brief summary of the changes and how you verified that the application runs successfully with Docker Compose.
