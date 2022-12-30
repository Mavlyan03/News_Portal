INSERT INTO users(id, name, surname, nickname, password, photo, role)
VALUES (1, 'Mavlyan', 'Sadirov', 'Mav', '$2a$12$5f6qb7Tz.vXoMQpw4rMAFuaM8zfXGTRsm1xoCGb6TXzWYzeJoKmaK', 'mav.com',
        'USER'),
       (2, 'Islam', 'Ashirov', 'Islam', '$2a$12$.L5MDTyqWb7mxqvLhl4hsuQYUTyNCb.ZqPVAf1EIEY3VF3FO2H0O2', 'twitter.com',
        'USER');

INSERT INTO news(id, header, news_cover, short_description, text_news, publication_date, category, publisher_id)
VALUES (1, 'World Championship', 'cw.com', 'About world of championship', 'Argentina won championship', '2022/12/31',
        'SPORT', 1),
       (2, 'UFC', 'ufc.com', 'Mahachev champion', 'Mahachev against Oliveira', '2022/12/21', 'ART', 1);

INSERT INTO comments(id, comment, date_of_comment, news_id)
VALUES (1, 'The best news', '2022/11/21', 1),
       (2, 'I dont like this news', '2022/12/29', 2);

