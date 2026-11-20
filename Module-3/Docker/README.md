# JWT demo with Docker Compose

Run these commands from this folder in PowerShell with Docker Desktop running Linux containers.

For a fresh checkout, create an ignored `.env` file with unique local credentials. Keep the same file on subsequent starts, since MySQL sets its credentials when it first initializes the named volume.

```powershell
if (-not (Test-Path .env)) {
  @(
    "JWTDEMO_DB_PASSWORD=$([guid]::NewGuid().ToString('N'))"
    "JWTDEMO_DB_ROOT_PASSWORD=$([guid]::NewGuid().ToString('N'))"
    "JWTDEMO_JWT_SECRET=$([guid]::NewGuid().ToString('N'))$([guid]::NewGuid().ToString('N'))"
  ) | Set-Content .env -Encoding ascii
}
docker compose -p module3-jwtdemo up --build -d --wait
```

Open [the demo](http://localhost:8088). Register a demo user, sign in, and create a post. Nginx forwards `/api/v1/` to Spring Boot inside Docker. MySQL is available at `127.0.0.1:3307` for local database clients and persists its data in a named volume.

If a port is in use, set `JWTDEMO_HTTP_PORT` or `JWTDEMO_MYSQL_PORT` in `.env` before starting. The separate `docker-compose.dev.yml` is for running only MySQL while developing the backend on the host. Both configurations use port 3307 by default.

```powershell
docker compose -p module3-jwtdemo ps
docker compose -p module3-jwtdemo logs backend
docker compose -p module3-jwtdemo down
```

`down` stops the stack and keeps MySQL data.
