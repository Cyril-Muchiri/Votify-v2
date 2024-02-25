# VOTIFY APP (v2)
```mermaid
%%{init: {"theme": "default", "themeVariables": { "fontSize": 50 }}}%%
graph TD;
style graph width:600px, graph height:400px;
    A[Create Environments] --> B[Set up Java Dependencies];
    B --> C[Configure Wildfly];
    B --> D[Install TestNG];
    B --> E[Set up Datasources];
    E --> F[Configure MySQL DB];
    A --> G[Deploy Locally to Minikube];
    G --> H[Prepare Docker Image];
    G --> I[Deploy to Minikube];
    A --> J[Deploy to GCP];
    J --> K[Prepare Docker Compose File];
    J --> L[Configure Ansible];
    L --> M[Execute Ansible Playbook];
    L --> N[Copy Docker Compose File];
    J --> O[Set up DataDog];
    O --> P[Monitor VM];
    O --> Q[Monitor Containers];
    P --> R[Install DataDog Agent on VM];
    Q --> S[Install DataDog Agent on Containers];

