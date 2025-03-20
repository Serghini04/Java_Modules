INSERT INTO "User" (login,password)
VALUES
    ('Me1','****'),
    ('Me2', '*****'),
    ('Me3', '******'),
    ('Me4', '*******'),
    ('Me5', '********');

INSERT INTO "Chatroom" (name, ownerId)
VALUES
    ('Chat1', 1),
    ('Chat2', 2),
    ('Chat3', 3),
    ('Chat4', 4),
    ('Chat5', 5);

INSERT INTO "Message" (authorId, chatroomId, text)
VALUES
    (1, 1, 'He me1'),
    (2, 2, 'He me2'),
    (3, 3, 'He me3'),
    (4, 4, 'He me4'),
    (5, 5, 'He me5');


INSERT INTO "UserChatroom" (userId,chatroomId)
VALUES
    (1, 5),
    (2,4),
    (3,1),
    (4,3),
    (5,2);

