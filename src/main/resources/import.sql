-- 좌석 데이터
INSERT INTO seat (seat_id) VALUES (1);
INSERT INTO seat (seat_id) VALUES (2);
INSERT INTO seat (seat_id) VALUES (3);
INSERT INTO seat (seat_id) VALUES (4);
INSERT INTO seat (seat_id) VALUES (5);
INSERT INTO seat (seat_id) VALUES (6);
INSERT INTO seat (seat_id) VALUES (7);
INSERT INTO seat (seat_id) VALUES (8);
INSERT INTO seat (seat_id) VALUES (9);
INSERT INTO seat (seat_id) VALUES (10);
INSERT INTO seat (seat_id) VALUES (11);
INSERT INTO seat (seat_id) VALUES (12);
INSERT INTO seat (seat_id) VALUES (13);
INSERT INTO seat (seat_id) VALUES (14);
INSERT INTO seat (seat_id) VALUES (15);
INSERT INTO seat (seat_id) VALUES (16);
INSERT INTO seat (seat_id) VALUES (17);
INSERT INTO seat (seat_id) VALUES (18);
INSERT INTO seat (seat_id) VALUES (19);
INSERT INTO seat (seat_id) VALUES (20);
INSERT INTO seat (seat_id) VALUES (21);
INSERT INTO seat (seat_id) VALUES (22);
INSERT INTO seat (seat_id) VALUES (23);
INSERT INTO seat (seat_id) VALUES (24);
INSERT INTO seat (seat_id) VALUES (25);
INSERT INTO seat (seat_id) VALUES (26);
INSERT INTO seat (seat_id) VALUES (27);
INSERT INTO seat (seat_id) VALUES (28);
INSERT INTO seat (seat_id) VALUES (29);
INSERT INTO seat (seat_id) VALUES (30);
INSERT INTO seat (seat_id) VALUES (31);
INSERT INTO seat (seat_id) VALUES (32);
INSERT INTO seat (seat_id) VALUES (33);
INSERT INTO seat (seat_id) VALUES (34);
INSERT INTO seat (seat_id) VALUES (35);
INSERT INTO seat (seat_id) VALUES (36);
INSERT INTO seat (seat_id) VALUES (37);
INSERT INTO seat (seat_id) VALUES (38);
INSERT INTO seat (seat_id) VALUES (39);
INSERT INTO seat (seat_id) VALUES (40);
INSERT INTO seat (seat_id) VALUES (41);
INSERT INTO seat (seat_id) VALUES (42);
INSERT INTO seat (seat_id) VALUES (43);
INSERT INTO seat (seat_id) VALUES (44);
INSERT INTO seat (seat_id) VALUES (45);
INSERT INTO seat (seat_id) VALUES (46);
INSERT INTO seat (seat_id) VALUES (47);
INSERT INTO seat (seat_id) VALUES (48);
INSERT INTO seat (seat_id) VALUES (49);
INSERT INTO seat (seat_id) VALUES (50);

-- 콘서트 데이터
INSERT INTO concert (concert_id) VALUES (1);
INSERT INTO concert (concert_id) VALUES (2);
INSERT INTO concert (concert_id) VALUES (3);

-- 콘서트 옵션 데이터
INSERT INTO concert_option (concert_id, concert_name, location, open_dt) VALUES (1, '신촌 콘서트', '신촌', '2024-07-10 10:00:00.000');
INSERT INTO concert_option (concert_id, concert_name, location, open_dt) VALUES (2, '홍대 콘서트', '홍대', '2024-07-15 13:30:00.000');
INSERT INTO concert_option (concert_id, concert_name, location, open_dt) VALUES (3, '잠실 콘서트', '잠실', '2024-07-30 17:00:00.000');

-- 좌석 옵션 데이터
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (1, 1, 100000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (2, 1, 100000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (3, 1, 100000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (4, 1, 100000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (5, 1, 100000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (6, 1, 100000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (7, 1, 100000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (8, 1, 100000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (9, 1, 100000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (10, 1, 100000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (11, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (12, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (13, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (14, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (15, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (16, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (17, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (18, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (19, 1, 50000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (20, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (21, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (22, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (23, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (24, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (25, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (26, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (27, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (28, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (29, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (30, 1, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (31, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (32, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (33, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (34, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (35, 1, 30000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (36, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (37, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (38, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (39, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (40, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (41, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (42, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (43, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (44, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (45, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (46, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (47, 1, 30000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (48, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (49, 1, 30000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (50, 1, 30000, 'UNAVAILABLE');

INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (1, 2, 100000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (2, 2, 100000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (3, 2, 100000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (4, 2, 100000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (5, 2, 100000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (6, 2, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (7, 2, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (8, 2, 50000, 'UNAVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (9, 2, 50000, 'AVAILABLE');
INSERT INTO seat_option (seat_id, concert_option_id, price, status) VALUES (10, 2, 50000, 'AVAILABLE');

-- 고객 데이터
INSERT INTO customer (customer_name, amount) VALUES ('홍길동', 10000);
INSERT INTO customer (customer_name, amount) VALUES ('김아무개', 5000);
INSERT INTO customer (customer_name, amount) VALUES ('사람인', 100000);
INSERT INTO customer (customer_name, amount) VALUES ('포스트맨', 50000);