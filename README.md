# lundegaard_assignment

## System requirments
 - Docker Compose 1.29.2 - latest 
 
## Installation
Clone or download the git repository:

```bash
git clone https://github.com/GMSkypi/lundegaard_assignment.git
```

Go to the folder with the monitoring system:
```bash
cd lundegaard_assignment
```

Build and run the monitoring system:
```bash
docker-compose up -d 
```

By default, the web UI is located at address <strong>http://0.0.0.0:80</strong>. 
Data is stored in H2 inmemory database accessible at address <strong>http://localhost:8080/h2-console</strong>

The default credentials for login are:
 *   Driver Class - org.h2.Driver
 *   JDBC URL - jdbc:h2:~/test
 *   User Name - sa
 *   Password - password
