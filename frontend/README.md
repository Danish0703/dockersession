# Frontend

React + TypeScript + Vite. See the repository root README for setup.

- npm.cmd ci: install dependencies
- npm.cmd run dev: start the frontend on http://localhost:5173
- npm.cmd run build: typecheck and build for production
- npm.cmd run lint: run ESLint

The browser calls http://localhost:8080/api/todos directly. The backend allows the frontend origin http://localhost:5173 through CORS. Tasks are stored in PostgreSQL through the API, not in browser local storage.
