INSERT INTO tb_category (name, code)
VALUES ('상의', '100');

INSERT INTO tb_category (name, code)
VALUES ('상의>후드티', '100>100');

INSERT INTO tb_category (name, code)
VALUES ('상의>반팔', '100>101');

INSERT INTO tb_category (name, code)
VALUES ('하의', '101');

INSERT INTO tb_category (name, code)
VALUES ('하의>청바지', '101>100');

INSERT INTO tb_category (name, code)
VALUES ('하의>츄리닝', '101>101');

--

INSERT INTO tb_rec_category (id, name, parent_id)
VALUES (1, '상의', 0);

INSERT INTO tb_rec_category (id, name, parent_id)
VALUES (2, '하의', 0);

INSERT INTO tb_rec_category (id, name, parent_id)
VALUES (3, '후드티', 1);

INSERT INTO tb_rec_category (id, name, parent_id)
VALUES (4, '반팔', 1);

INSERT INTO tb_rec_category (id, name, parent_id)
VALUES (5, '청바지', 2);

INSERT INTO tb_rec_category (id, name, parent_id)
VALUES (6, '츄리닝', 2);