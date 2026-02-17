# Client-Server Messaging App

## Overview

The **Client-Server Messaging App** is a distributed messaging system built using **Java RMI (Remote Method Invocation)**.  
It follows a client-server architecture where:

- The **Server** manages user accounts and message storage.
- The **Client** connects remotely to perform actions such as creating accounts, sending messages, and viewing inbox messages.

All communication between client and server is handled via Java RMI.

---

## Project Structure


├── Account.java

├── Client.java

├── Message.java

├── MessagingService.java

├── MessagingServiceApplication.java

└── Server.java


### File Descriptions

#### `Account.java`
Represents a user account.

- Stores:
  - `username`
  - `authToken`
  - `messageBox` (ArrayList of Message objects)
- Provides access to:
  - User information
  - Stored messages

---

#### `Message.java`
Represents a message sent between users.

- Fields:
  - `id` (auto-incremented)
  - `sender`
  - `receiver`
  - `body`
  - `isRead`
- Each message gets a unique ID automatically.

---

#### `MessagingService.java`
Remote interface that defines the methods available to clients.

Includes methods such as:

- `createAccount(String username)`
- `showAccounts(int token)`
- `sendMessage(int token, String recipient, String messageBody)`
- `showInbox(int token)`
- (other related message/account operations)

All methods throw `RemoteException`.

---

#### `MessagingServiceApplication.java`
Implements the `MessagingService` interface.

Responsibilities:

- Manages all accounts (stored in memory using `ArrayList`)
- Generates authentication tokens using `Random`
- Handles:
  - Account creation
  - Sending messages
  - Viewing inbox
  - Account listing

Extends:
UnicastRemoteObject


---

#### `Server.java`
Starts the RMI server.

- Creates an RMI registry on a specified port
- Instantiates `MessagingServiceApplication`
- Binds it under the name `"messenger"`

---

#### `Client.java`
Client application that:

- Connects to the RMI registry
- Looks up `"messenger"`
- Executes actions based on command-line arguments

---

## Features

- Create user accounts
- Token-based authentication
- Send messages to other users
- View inbox messages
- List existing accounts
- Unique message IDs
- In-memory data storage

---

## Technologies Used

- Java
- Java RMI (Remote Method Invocation)
- Java Collections (`ArrayList`)
- Java Networking (RMI Registry)

---

## How It Works

1. The **Server** starts and creates an RMI registry.
2. The messaging service is registered under the name `"messenger"`.
3. The **Client** connects to the registry using:
   - Host
   - Port
4. The client invokes remote methods on the server.
5. The server processes the request and returns a response.

All account and message data are stored in memory while the server is running.

---

## Requirements

- Java JDK 8 or higher
- Terminal / Command Prompt

---

## Compilation

Compile all Java files:

```bash
javac *.java

