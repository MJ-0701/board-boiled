# 더미 데이터 생성
DELIMITER $$
DROP PROCEDURE IF EXISTS boardInsert$$

CREATE PROCEDURE boardInsert()
BEGIN
    DECLARE i INT DEFAULT 1;

    WHILE i <= 500 DO
            INSERT INTO blind_date.board(title , contents, user_id , created_at, updated_at, like_count, view_count, gender)
            VALUES(concat('제목',i), concat('내용',i), concat("유저id", i), now(), now(), 0, 0, 0);
            SET i = i + 1;
        END WHILE;
END$$
DELIMITER $$


DELIMITER $$
DROP PROCEDURE IF EXISTS commentInsert$$

CREATE PROCEDURE commentInsert()
BEGIN
    DECLARE i INT DEFAULT 1;

    WHILE i <= 500 DO
            INSERT INTO blind_date.comment(created_at, updated_at, contents, is_removed, nick_name, password, board_id, parent_id)
            VALUES(now(), now(), concat('댓글', i), 0, concat('닉네임', i), "1234", 1, null);
            SET i = i + 1;
        END WHILE;
END$$
DELIMITER $$



DELIMITER $$
DROP PROCEDURE IF EXISTS reCommentInsert$$

CREATE PROCEDURE reCommentInsert()
BEGIN
    DECLARE i INT DEFAULT 1;

    WHILE i <= 500 DO
            INSERT INTO blind_date.re_comment(contents, is_removed, nick_name, password, comment_id)
            VALUES(concat('대댓글', i), 0, concat('닉네임', i), "1234", concat(i));
            SET i = i + 1;
        END WHILE;
END$$
DELIMITER $$

CALL boardInsert;

CALL commentInsert;

CALL reCommentInsert;