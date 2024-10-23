-- Добавляем администратора
INSERT INTO users (username, password)
VALUES ('admin', '$2a$10$oCJGzkBuul/GVxpEVfmKCuWoNZ8fBvE3wQQiOcdfnGkskyUxV.IO6') RETURNING id;

-- Получаем id только что добавленного администратора
-- Вставляем роль ADMIN для этого пользователя
INSERT INTO user_roles (user_id, role)
VALUES (currval(pg_get_serial_sequence('users', 'id')), 'ADMIN');

-- Добавляем обычного пользователя
INSERT INTO users (username, password)
VALUES ('user', '$2a$10$GqQxERcnJbFoW7ZGvch.feQfpfM9tW4kMJCQff6lLgrZyrvFyTe5G') RETURNING id;

-- Получаем id только что добавленного пользователя
-- Вставляем роль USER для этого пользователя
INSERT INTO user_roles (user_id, role)
VALUES (currval(pg_get_serial_sequence('users', 'id')), 'USER');
