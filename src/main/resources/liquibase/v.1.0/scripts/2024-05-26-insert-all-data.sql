-- Вставка данных в таблицу account
INSERT INTO account (user_id, active, bio, email, image_name, link, login, password, role, username)
VALUES
    (1, true, 'Bio for user 1', 'user1@example.com', 'default.png', 'user1123', 'user1', md5('1'), 'USER', 'User1'),
    (2, true, 'Bio for user 2', 'user2@example.com', 'default.png', 'user2123', 'user2', md5('1'), 'ADMIN', 'User2'),
    (3, true, 'Bio for user 3', 'user3@example.com', 'default.png', 'user3123', 'user3', md5('1'), 'USER', 'User3'),
    (4, true, 'Bio for user 4', 'user4@example.com', 'default.png', 'user4123', 'user4', md5('1'), 'USER', 'User4'),
    (5, true, 'Bio for user 5', 'user5@example.com', 'default.png', 'user5123', 'user5', md5('1'), 'ADMIN', 'User5');

-- Вставка данных в таблицу followers
INSERT INTO followers (follower_id, following_id)
VALUES
    (1, 2),
    (1, 3),
    (1, 4),
    (2, 3),
    (2, 4),
    (2, 5),
    (3, 4),
    (3, 5),
    (3, 1),
    (4, 5),
    (4, 1),
    (4, 2),
    (5, 1),
    (5, 2),
    (5, 3);

-- Вставка данных в таблицу post
INSERT INTO post (post_id, published, read_count, user_id, creator_comment, description, image_path, status, title, universe, web_link)
VALUES
    (1, true, 0, 1, 'Comment by creator 1', 'Description 1', 'default.png', 'PUBLIC', 'Title 1', 'Universe 1', 'post1111'),
    (2, true, 0, 1, 'Comment by creator 2', 'Description 2', 'default.png', 'PRIVATE', 'Title 2', 'Universe 2', 'post2222'),
    (3, true, 0, 2, 'Comment by creator 3', 'Description 3', 'default.png', 'PUBLIC_WITH_LIMITS', 'Title 3', 'Universe 3', 'post33'),
    (4, true, 0, 3, 'Comment by creator 4', 'Description 4', 'default.png', 'PUBLIC', 'Title 4', 'Universe 4', 'post114'),
    (5, true, 0, 3, 'Comment by creator 5', 'Description 5', 'default.png', 'PRIVATE', 'Title 5', 'Universe 5', 'post532');

-- Вставка данных в таблицу branch
INSERT INTO branch (branch_id, published, post_id, user_id, description, link, name, parent_branch_id)
VALUES
    (1, true, 1, 1, 'Description for branch 1', 'branch12', 'Branch 1', null),
    (2, true, 1, 2, 'Description for branch 2', 'branch23', 'Branch 2', 1),
    (3, true, 1, 3, 'Description for branch 3', 'branch34', 'Branch 3', 2),
    (4, true, 3, 3, 'Description for branch 4', 'branch45', 'Branch 4', null),
    (5, true, 3, 4, 'Description for branch 5', 'branch56', 'Branch 5', 4);

-- Вставка данных в таблицу branch_comment
INSERT INTO branch_comment (id, date, branch_id, user_id, comment)
VALUES
    (1, '2023-05-26', 1, 1, 'Comment 1 on branch 1'),
    (2, '2023-05-26', 1, 2, 'Comment 2 on branch 1'),
    (3, '2023-05-26', 2, 3, 'Comment 3 on branch 2'),
    (4, '2023-05-26', 2, 4, 'Comment 4 on branch 2'),
    (5, '2023-05-26', 3, 5, 'Comment 5 on branch 3'),
    (6, '2023-05-26', 3, 1, 'Comment 6 on branch 3'),
    (7, '2023-05-26', 4, 2, 'Comment 7 on branch 4'),
    (8, '2023-05-26', 4, 3, 'Comment 8 on branch 4'),
    (9, '2023-05-26', 5, 4, 'Comment 9 on branch 5'),
    (10, '2023-05-26', 5, 5, 'Comment 10 on branch 5');

-- Вставка данных в таблицу branch_rate
INSERT INTO branch_rate (id, rating, branch_id, user_id)
VALUES
    (1, 5, 1, 1),
    (2, 4, 1, 2),
    (3, 3, 2, 3),
    (4, 2, 2, 4),
    (5, 1, 3, 5),
    (6, 5, 3, 1),
    (7, 4, 4, 2),
    (8, 3, 4, 3),
    (9, 2, 5, 4),
    (10, 1, 5, 5);

-- Вставка данных в таблицу chapter
INSERT INTO chapter (id, number, branch_id, link, title)
VALUES
    (1, 1, 1, 'chapter15553535', 'Chapter'),
    (2, 2, 1, 'chapter25553535', 'Chapter'),
    (3, 3, 1, 'chapter35553535', 'Chapter'),
    (4, 2, 2, 'chapter45553535', 'Chapter'),
    (5, 3, 2, 'chapter55553535', 'Chapter'),
    (6, 4, 2, 'chapter65553535', 'Chapter'),
    (7, 4, 3, 'chapter75553535', 'Chapter'),
    (8, 5, 3, 'chapter85553535', 'Chapter'),
    (9, 6, 3, 'chapter95553535', 'Chapter'),
    (10, 7, 3, 'chapter10124', 'Chapter');

-- Вставка данных в таблицу post_comment
INSERT INTO post_comment (id, date, post_id, user_id, comment)
VALUES
    (1, '2023-05-26', 1, 1, 'Comment 1 on post 1'),
    (2, '2023-05-26', 1, 2, 'Comment 2 on post 1'),
    (3, '2023-05-26', 2, 3, 'Comment 3 on post 2'),
    (4, '2023-05-26', 2, 4, 'Comment 4 on post 2'),
    (5, '2023-05-26', 3, 5, 'Comment 5 on post 3'),
    (6, '2023-05-26', 3, 1, 'Comment 6 on post 3'),
    (7, '2023-05-26', 4, 2, 'Comment 7 on post 4'),
    (8, '2023-05-26', 4, 3, 'Comment 8 on post 4'),
    (9, '2023-05-26', 5, 4, 'Comment 9 on post 5'),
    (10, '2023-05-26', 5, 5, 'Comment 10 on post 5');

-- Вставка данных в таблицу post_editor
INSERT INTO post_editor (account_id, post_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (1, 2),
    (2, 3),
    (3, 4),
    (4, 5),
    (5, 1);

-- Вставка данных в таблицу post_rate
INSERT INTO post_rate (id, rating, post_id, user_id)
VALUES
    (1, 5, 1, 1),
    (2, 4, 2, 2),
    (3, 3, 3, 3),
    (4, 2, 4, 4),
    (5, 1, 5, 5),
    (6, 5, 1, 2),
    (7, 4, 2, 3),
    (8, 3, 3, 4),
    (9, 2, 4, 5),
    (10, 1, 5, 1);

-- Вставка данных в таблицу user_post_read
INSERT INTO user_post_read (account_id, post_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (1, 2),
    (2, 3),
    (3, 4),
    (4, 5),
    (5, 1);
