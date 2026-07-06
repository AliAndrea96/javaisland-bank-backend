# 🏦 JavaIsland Bank - Backend

Questo è il modulo backend del progetto JavaIsland_Bank sviluppato con **Spring Boot 3** e **Java 21**.
Il sistema gestisce le operazioni principali di correntisti, carte e richieste di prestito.

---

## 🧠 1. Decisioni Architetturali (Architectural Decisions)

Per mantenere il codice pulito, leggibile e scalabile, abbiamo adottato le seguenti scelte:

*   🏛️ **Screaming Architecture**: Il progetto è diviso per **funzionalità/dominio** (package `user`, `card`, `request`) e non per layer tecnici. Questo permette a chiunque di capire cosa fa l'applicazione semplicemente guardando le cartelle.
*   🗂️ **Tabelle di Lookup tramite Enum**: Gli stati dell'utente (`UserStatus`), i tipi di carta (`CardType`) e gli stati delle carte (`CardStatus`) sono stati implementati come **Enum Java** mappati nel database come stringhe (`@Enumerated(EnumType.STRING)`). Questo evita JOIN lenti sul DB e previene bug di battitura.
*   📝 **Configurazione Unica**: È stato rimosso `application.properties` in favore di un unico file **`application.yml`**, visivamente più ordinato e strutturato ad albero per evitare conflitti di configurazione.

---

## 🚀 2. Come Avviare il Progetto (Quick Start)

### 📌 Prerequisiti
*   Java 21 installato.
*   Docker (per il database PostgreSQL e Keycloak).

### ⚙️ Passi per l'avvio:

1.  **Avviare i container Docker** (PostgreSQL e Keycloak) usando il file `docker-compose.yml` nella radice del progetto:
    ```bash
    docker compose up -d
    ```
2.  **Verificare la configurazione**: Controllare che le credenziali nel file `src/main/resources/application.yml` corrispondano a quelle del proprio database locale.
3.  **Compilare il progetto**:
    ```bash
    ./mvnw clean compile
    ```
4.  **Avviare l'applicazione**:
    ```bash
    ./mvnw spring-boot:run
    ```

Il server partirà sulla porta `8080`.

---

## 🛠️ 3. Linee Guida per gli altri Sviluppatori (Onboarding Dev)

Se devi aggiungere una nuova funzionalità o modificare il codice esistente, segui queste regole per mantenere l'ordine:

1.  **Lavora per Dominio**: Se crei una nuova entità (es. `Transaction`), non sparpagliare i file. Crea un nuovo package `com.javaisland.bank_backend.transaction` e inserisci lì dentro Entity, Repository, Service e Controller.
2.  **Non usare ID numerici per gli Stati**: Se devi inserire nuovi stati fissi, crea un Enum dedicato. Nel database ricordati di mapparlo come Stringa usando l'annotazione:
    ```java
    @Enumerated(EnumType.STRING)
    ```
3.  **Non toccare la branch develop direttamente**: Crea sempre una branch di feature (es. `feature-nome-funzione`) partendo da `develop`, lavora lì e poi apri una Pull Request per la revisione.