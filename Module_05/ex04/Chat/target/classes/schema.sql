CREATE TABLE "User" (
    userId SERIAL PRIMARY KEY,
    login VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE "Chatroom" (
    chatroomId SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    ownerId INT NOT NULL,
    FOREIGN KEY (ownerId) REFERENCES "User" (userId) ON DELETE CASCADE
);

CREATE TABLE "Message" (
    messageId SERIAL PRIMARY KEY,
    authorId INT NOT NULL,
    chatroomId INT NOT NULL,
    text TEXT NOT NULL,
    dateTime TIMESTAMP(0) DEFAULT NOW(),
    FOREIGN KEY (authorId) REFERENCES "User" (userId) ON DELETE CASCADE,
    FOREIGN KEY (chatroomId) REFERENCES "Chatroom" (chatroomId) ON DELETE CASCADE
);

CREATE TABLE "UserChatroom" (
    userId INT NOT NULL,
    chatroomId INT NOT NULL,
    PRIMARY KEY (userId, chatroomId),
    FOREIGN KEY (userId) REFERENCES "User" (userId) ON DELETE CASCADE,
    FOREIGN KEY (chatroomId) REFERENCES "Chatroom" (chatroomId) ON DELETE CASCADE
);
