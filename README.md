Arrête un application qui passe par le port 8080 :
Dans le terminal CMD : `netstat -ano | findstr :8080`

Noter le PID (exemple : 00000)
Note le PID, puis :
`taskkill /PID <PID> /F`
`taskkill /PID 00000 /F`